package br.edu.utfpr.espolios.service;

import br.edu.utfpr.espolios.models.Inventario;
import br.edu.utfpr.espolios.models.Loot;
import br.edu.utfpr.espolios.models.enums.Rarity;
import br.edu.utfpr.espolios.service.CRUD.InventarioService;
import br.edu.utfpr.espolios.service.CRUD.LootService;
import br.edu.utfpr.espolios.utils.MathUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

@Service
@Slf4j
public record ReSortearService(LootService lootService, InventarioService inventarioService) {

    public Inventario resortear(Inventario inventario, Loot loot, Loot loot2, Loot loot3) {
        log.info("Re-sorteando loots {} {} {}",
                inventario.getLoots().get(0).getId(),
                inventario.getLoots().get(1).getId(),
                inventario.getLoots().get(2).getId());

        log.info("Validando se o inventario possui os loots");
        if (validaSeOLootPertenceAoInventario(inventario, loot) &&
                validaSeOLootPertenceAoInventario(inventario, loot2) &&
                validaSeOLootPertenceAoInventario(inventario, loot3)) {
            log.info("Inventario possui os loots");

            double gastoTotal = loot.getValorMonetario() + loot2.getValorMonetario() + loot3.getValorMonetario();

            log.info("Removendo os loots do inventario");
            lootService.getRepository().deleteLootById(loot.getId());
            lootService.getRepository().deleteLootById(loot2.getId());
            lootService.getRepository().deleteLootById(loot3.getId());

            log.info("Gerando Rarity");
            double raridade = MathUtils.random(0, 100);
            Rarity rarity = null;
            if (raridade <= 10) {
                log.info("PERDEU TUDO");
            }
            if (raridade > 10 && raridade <= 30) {
                log.info("Rarity: Common");
                rarity = Rarity.COMMON;
            }
            if (raridade > 30 && raridade <= 60) {
                log.info("Rarity: Rare");
                rarity = Rarity.RARE;
            }
            if (raridade > 60 && raridade <= 80) {
                log.info("Rarity: Epic");
                rarity = Rarity.EPIC;
            }
            if (raridade > 80 && raridade <= 100) {
                log.info("Rarity: Legendary");
                rarity = Rarity.LEGENDARY;
            }

            if (rarity != null) {
                double premio = MathUtils.random(1, 3 * raridade);
                log.info("Voc?? sorteou R$ " + String.format("%.2f", gastoTotal) + " e recebeu R$ " + String.format("%.2f", premio));
                lootService.getRepository().save(new Loot(rarity, premio, inventario));
            } else {
                log.info("Teve azar, perdeu os 3 loots");
            }
            return inventarioService.getRepository().findById(inventario.getId()).get();
        } else {
            log.info("Inventario n??o possui os loots");
            if (inventario.getId() != null) {
                return inventarioService.getRepository().findById(inventario.getId()).get();
            } else {
                return inventario;
            }
        }
    }

    private boolean validaSeOLootPertenceAoInventario(Inventario inventario, Loot loot) {
        return lootService().getRepository().findAllByInventarioId(inventario.getId()).stream()
                .anyMatch(it -> it.getId() != null && loot.getId() != null && it.getId().equals(loot.getId()));
    }

}