package br.edu.utfpr.espolios.service.CRUD;

import br.edu.utfpr.espolios.models.Bau;
import br.edu.utfpr.espolios.models.enums.Rarity;
import br.edu.utfpr.espolios.repository.BauRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@Slf4j
public record BauService(BauRepository repository) {

    public void save(Bau bau) {
        log.info("Salvando Bau {}", bau.getRarity());
        repository.saveAndFlush(bau);
        log.info("Bau Salva Tipo {}", bau.getRarity());
    }

    public void increment(Rarity rarity, Integer quantity) {
        log.info("Incrementando bau {}", rarity);
        Bau bau = repository.findByRarity(rarity);
        bau.setQuantidade(bau.getQuantidade() + quantity);
        repository.saveAndFlush(bau);
        log.info("Incrementou a bau {}", rarity);
    }

    public void decrement(Rarity rarity, Integer quantity) {
        log.info("Decrementando bau {}", rarity);
        Bau bau = repository.findByRarity(rarity);
        bau.setQuantidade(bau.getQuantidade() - quantity);
        repository.saveAndFlush(bau);
        log.info("Decrementou a bau {}", rarity);
    }

    public void log() {
        List<Bau> baus = repository.findAll();
        baus.sort(Comparator.comparing(Bau::getRarity));
        baus.forEach(bau -> log.info(bau.getQuantidade() + "x - Tipo: " + bau.getRarity()));
    }

}
