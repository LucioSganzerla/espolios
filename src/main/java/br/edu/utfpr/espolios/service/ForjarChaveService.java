package br.edu.utfpr.espolios.service;

import br.edu.utfpr.espolios.Constants;
import br.edu.utfpr.espolios.models.Chave;
import br.edu.utfpr.espolios.models.enums.Rarity;
import br.edu.utfpr.espolios.service.CRUD.ChaveService;
import br.edu.utfpr.espolios.service.CRUD.FragmentoChaveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public record ForjarChaveService(FragmentoChaveService fragmentoChaveService,
                                 ChaveService chaveService) {

    public void forjarChave(Rarity rarity, Integer quantidade) {
        if (this.validaQuantidadeFragmentos(rarity, quantidade)) {
            log.info("Forjando chave");
            fragmentoChaveService.decrement(rarity, quantidade * Constants.FRAGMENTOS_POR_CHAVE);

            Chave chave = chaveService.repository().findByRarity(rarity);
            if (chave == null) {
                log.info("A chave não possui registro, Registrando...");
                chaveService.save(new Chave(0, rarity));
                log.info("Chave registrada");
            }

            chaveService.increment(rarity, quantidade);
            log.info("Foram forjadas {}x Chaves", quantidade);
        }
    }

    private boolean validaQuantidadeFragmentos(Rarity rarity, Integer quantidade) {
        log.info("Validando quantidade de fragmentos, para verificar se é possivel forjar");
        boolean resut = fragmentoChaveService.repository().findByRarity(rarity).getQuantidade() >= (quantidade * Constants.FRAGMENTOS_POR_CHAVE);
        if (resut) {
            log.info("É possível forjar mais chaves");
        } else {
            log.info("Não é possível forjar mais chaves");
        }
        return resut;
    }

    public void forjarTodasChaves(Rarity rarity) {
        Integer quantidade = fragmentoChaveService.repository().findByRarity(rarity).getQuantidade() / Constants.FRAGMENTOS_POR_CHAVE;
        log.info("Forjando {} chaves do tipo {}", quantidade, rarity);
        forjarChave(rarity, quantidade);
    }
}