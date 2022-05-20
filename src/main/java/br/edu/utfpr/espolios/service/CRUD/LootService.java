package br.edu.utfpr.espolios.service.CRUD;

import br.edu.utfpr.espolios.models.FragmentoChave;
import br.edu.utfpr.espolios.models.Loot;
import br.edu.utfpr.espolios.models.enums.Rarity;
import br.edu.utfpr.espolios.repository.LootRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public record LootService(LootRepository repository) {

    public void save(Loot loot) {
        log.info("Salvando loot {}", loot.getRarity());
        repository.saveAndFlush(loot);
        log.info("Loot Salvo Tipo {}", loot.getRarity());
    }

    public void logAdquiridos(Rarity rarity) {
        log.info("Todos os loots adquiridos da rariedade {}", rarity);
        repository.findAllByRarityAndIsDropable(rarity, false).forEach(loot -> log.info("{}", loot));
        log.info("Fim dos loots");
    }

    public void logDisponiveis(Rarity rarity) {
        log.info("Todos os loots disponiveis da raridade {}", rarity);
        repository.findAllByRarityAndIsDropable(rarity, true).forEach(loot -> log.info("{}", loot));
        log.info("Fim dos loots");
    }

}
