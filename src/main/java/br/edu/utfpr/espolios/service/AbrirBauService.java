package br.edu.utfpr.espolios.service;

import br.edu.utfpr.espolios.models.enums.Rarity;
import br.edu.utfpr.espolios.service.CRUD.BauService;
import br.edu.utfpr.espolios.service.CRUD.ChaveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public record AbrirBauService(BauService bauService,
                              ChaveService chaveService) {

    private boolean validaQuantidadeChaves(Rarity rarity, Integer quantidade) {
        log.info("Validando quantidade de chaves, para verificar se é possivel abrir o bau");
        boolean resut = chaveService.getRepository().findByRarity(rarity).getQuantidade() >= (quantidade);
        if (resut) {
            log.info("É possível abrir " + quantidade + " baus do tipo " + rarity);
        } else {
            log.info("Não é possível abrir " + quantidade + " baus do tipo " + rarity);
        }
        return resut;
    }

    private boolean validaQuantidadeBaus(Rarity rarity, Integer quantidade) {
        log.info("Validando quantidade de baus");
        boolean resut = chaveService.getRepository().findByRarity(rarity).getQuantidade() >= (quantidade);
        if (resut) {
            log.info("Possui " + quantidade + " baus do tipo " + rarity);
        } else {
            log.info("Não possui " + quantidade + " baus do tipo " + rarity);
        }
        return resut;
    }

    private boolean validar(Rarity rarity, Integer quantidade) {
        return validaQuantidadeChaves(rarity, quantidade) && validaQuantidadeBaus(rarity, quantidade);
    }

    public void abrirBau(Rarity rarity, Integer quantidade) {
        log.info("Abrindo bau do tipo " + rarity + " com " + quantidade + " chaves");
        if (validar(rarity, quantidade)) {

            chaveService.decrement(rarity, quantidade);
            bauService.decrement(rarity, quantidade);

            // Todo: Criar os drops e atribuir aqui

        } else {
            log.info("Não é possível abrir " + quantidade + " baus do tipo " + rarity);
        }
    }
}