package br.edu.utfpr.espolios.service;

import br.edu.utfpr.espolios.models.Bau;
import br.edu.utfpr.espolios.models.Inventario;
import br.edu.utfpr.espolios.models.Loot;
import br.edu.utfpr.espolios.models.enums.Rarity;
import br.edu.utfpr.espolios.service.CRUD.BauService;
import br.edu.utfpr.espolios.service.CRUD.ChaveService;
import br.edu.utfpr.espolios.service.CRUD.InventarioService;
import br.edu.utfpr.espolios.service.CRUD.LootService;
import br.edu.utfpr.espolios.utils.MathUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public record AbrirBauService(BauService bauService,
                              ChaveService chaveService,
                              LootService lootService,
                              InventarioService inventarioService) {

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
        boolean resut = bauService.getRepository().findByRarity(rarity).getQuantidade() >= (quantidade);
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

    public void abrirPossiveis(Inventario inventario, Rarity rarity) {
        Bau baus = bauService.getRepository().findByRarity(rarity);
        abrirBau(inventario, rarity, baus.getQuantidade());
    }

    public void abrirBau(Inventario inventario, Rarity rarity, Integer quantidade) {
        log.info("Abrindo bau do tipo " + rarity + " com " + quantidade + " chaves");
        if (validar(rarity, quantidade)) {

            chaveService.decrement(rarity, quantidade);
            bauService.decrement(rarity, quantidade);

            switch (rarity) {
                case COMMON:
                    log.info("Abrindo bau comum");
                    lootService.getRepository().save(new Loot(Rarity.COMMON, MathUtils.random(0, 10), inventario));
                    break;
                case RARE:
                    log.info("Abrindo bau raro");
                    lootService.getRepository().save(new Loot(Rarity.RARE, MathUtils.random(10.01, 20), inventario));
                    break;
                case EPIC:
                    log.info("Abrindo bau epico");
                    lootService.getRepository().save(new Loot(Rarity.EPIC, MathUtils.random(20.01, 30), inventario));
                    break;
                case LEGENDARY:
                    log.info("Abrindo bau lendário");
                    lootService.getRepository().save(new Loot(Rarity.LEGENDARY, MathUtils.random(30.01, 200), inventario));
                    break;
            }

            log.info("Bau aberto com sucesso");
        } else {
            log.info("Não é possível abrir " + quantidade + " baus do tipo " + rarity);
        }
    }


}