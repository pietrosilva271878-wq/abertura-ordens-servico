
package br.com.ordensservico.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.ordensservico.dto.OrdemServicoRequest;
import br.com.ordensservico.model.Equipamentos;
import br.com.ordensservico.model.OrdemServico;
import br.com.ordensservico.repository.EquipamentosRepository;
import br.com.ordensservico.repository.OrdemServicoRepository;

@Service
public class OrdemServicoService {

    private final OrdemServicoRepository ordemServicoRepository;
    private final EquipamentosRepository equipamentosRepository;

    private static final ZoneId FUSO_BRASILIA =
            ZoneId.of("America/Sao_Paulo");

    public OrdemServicoService(
            OrdemServicoRepository ordemServicoRepository,
            EquipamentosRepository equipamentosRepository) {

        this.ordemServicoRepository = ordemServicoRepository;
        this.equipamentosRepository = equipamentosRepository;
    }

    // ==========================
    // CADASTRAR
    // ==========================
    public OrdemServico cadastrar(OrdemServicoRequest dados) {

        Equipamentos equipamento =
                buscarEquipamento(dados.getEquipamentoId());

        OrdemServico ordem = new OrdemServico();

        ordem.setDescricao(dados.getDescricao());
        ordem.setEquipamento(equipamento);
        ordem.setStatus("ABERTA");

        // Data de abertura no horário de Brasília
        ordem.setDataAbertura(
                LocalDateTime.now(FUSO_BRASILIA)
        );

        // Ainda não foi encerrada
        ordem.setDataEncerramento(null);

        return ordemServicoRepository.save(ordem);
    }

    // ==========================
    // LISTAR
    // ==========================
    public List<OrdemServico> listar() {
        return ordemServicoRepository.findAll();
    }

    // ==========================
    // BUSCAR POR ID
    // ==========================
    public Optional<OrdemServico> buscarPorId(Integer id) {
        return ordemServicoRepository.findById(id);
    }

    // ==========================
    // ATUALIZAR
    // ==========================
    public Optional<OrdemServico> atualizar(
            Integer id,
            OrdemServicoRequest dados) {

        Optional<OrdemServico> encontrado =
                ordemServicoRepository.findById(id);

        if (encontrado.isEmpty()) {
            return Optional.empty();
        }

        OrdemServico ordem = encontrado.get();

        if ("ENCERRADA".equals(ordem.getStatus())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Não é possível alterar uma ordem encerrada"
            );
        }

        Equipamentos equipamento =
                buscarEquipamento(dados.getEquipamentoId());

        ordem.setDescricao(dados.getDescricao());
        ordem.setEquipamento(equipamento);

        return Optional.of(
                ordemServicoRepository.save(ordem)
        );
    }

    // ==========================
    // DELETAR
    // ==========================
    public boolean deletar(Integer id) {

        if (!ordemServicoRepository.existsById(id)) {
            return false;
        }

        ordemServicoRepository.deleteById(id);

        return true;
    }

    // ==========================
    // BUSCAR EQUIPAMENTO
    // ==========================
    private Equipamentos buscarEquipamento(Integer id) {

        if (id == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "O ID do equipamento é obrigatório"
            );
        }

        return equipamentosRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Equipamento não encontrado"
                        )
                );
    }

    // ==========================
    // ENCERRAR ORDEM DE SERVIÇO
    // ==========================
    public OrdemServico encerrarOrdemServico(
            Integer id,
            String solucao) {

        OrdemServico ordem = ordemServicoRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Ordem de serviço não encontrada"
                        )
                );

        if ("ENCERRADA".equals(ordem.getStatus())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Esta ordem já está encerrada"
            );
        }

        if (solucao == null || solucao.isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "A solução é obrigatória"
            );
        }

        ordem.setSolucao(solucao);
        ordem.setStatus("ENCERRADA");

        // Data e hora automáticas de Brasília
        ordem.setDataEncerramento(
                LocalDateTime.now(FUSO_BRASILIA)
        );

        return ordemServicoRepository.save(ordem);
    }

    // ==========================
    // ATUALIZAR STATUS
    // ==========================
    public OrdemServico atualizarStatus(
            Integer id,
            String status) {

        OrdemServico ordem = ordemServicoRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Ordem de serviço não encontrada"
                        )
                );

        if ("ENCERRADA".equals(ordem.getStatus())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Não é possível alterar uma ordem encerrada"
            );
        }

        if (status == null ||
                (!status.equals("ABERTA") &&
                 !status.equals("EM_ANDAMENTO"))) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Status inválido"
            );
        }

        ordem.setStatus(status);

        return ordemServicoRepository.save(ordem);
    }

    // ==========================
    // LISTAR ORDENS NÃO ENCERRADAS
    // ==========================
    public List<OrdemServico> listarOrdensAbertas() {
        return ordemServicoRepository.findByDataEncerramentoIsNull();
    }
}

