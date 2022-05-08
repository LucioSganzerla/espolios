package br.edu.utfpr.espolios.service;

import br.edu.utfpr.espolios.Constants;
import br.edu.utfpr.espolios.models.Chave;
import br.edu.utfpr.espolios.models.enums.ShardType;
import br.edu.utfpr.espolios.service.CRUD.ChaveService;
import br.edu.utfpr.espolios.service.CRUD.FragmentoChaveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public record ForjarChaveService(FragmentoChaveService fragmentoChaveService, ChaveService chaveService) {

    public void forjarChave(ShardType shardType, Integer quantidade) {
        if (this.validaQuantidadeFragmentos(shardType, quantidade)) {
            log.info("Forjando chave");
            fragmentoChaveService.decrement(shardType, quantidade * Constants.FRAGMENTOS_POR_CHAVE);

            Chave chave = chaveService.repository().findChaveByType(shardType);
            if (chave == null) {
                log.info("A chave não possui registro, Registrando...");
                chaveService.save(new Chave(0, shardType));
                log.info("Chave registrada");
            }

            chaveService.increment(shardType, quantidade);
            log.info("Foram forjadas {}x Chaves", quantidade);
        }
    }

    private boolean validaQuantidadeFragmentos(ShardType shardType, Integer quantidade) {
        log.info("Validando quantidade de fragmentos, para verificar se é possivel forjar");
        boolean resut = fragmentoChaveService.repository().findByShardType(shardType).getQuantidade() >= (quantidade * Constants.FRAGMENTOS_POR_CHAVE);
        if (resut) {
            log.info("É possível forjar mais chaves");
        } else {
            log.info("Não é possível forjar mais chaves");
        }
        return resut;
    }

    public void forjarTodasChaves(ShardType shardType) {
        Integer quantidade = fragmentoChaveService.repository().findByShardType(shardType).getQuantidade() / Constants.FRAGMENTOS_POR_CHAVE;
        log.info("Forjando {} chaves do tipo {}", quantidade, shardType);
        forjarChave(shardType, quantidade);
    }
}