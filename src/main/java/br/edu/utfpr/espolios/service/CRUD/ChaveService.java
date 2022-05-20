package br.edu.utfpr.espolios.service.CRUD;

import br.edu.utfpr.espolios.models.Chave;
import br.edu.utfpr.espolios.models.enums.Rarity;
import br.edu.utfpr.espolios.repository.ChaveRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@Slf4j
public record ChaveService(ChaveRepository repository) {

    public void save(Chave chave) {
        log.info("Salvando Chave {}", chave.getRarity());
        repository.saveAndFlush(chave);
        log.info("Chave Salva Tipo {}", chave.getRarity());
    }

    public void increment(Rarity rarity, Integer quantity) {
        log.info("Incrementando chave {}", rarity);
        Chave chave = repository.findByRarity(rarity);
        chave.setQuantidade(chave.getQuantidade() + quantity);
        repository.saveAndFlush(chave);
        log.info("Incrementou a chave {}", rarity);
    }

    public void decrement(Rarity rarity, Integer quantity) {
        log.info("Decrementando chave {}", rarity);
        Chave chave = repository.findByRarity(rarity);
        chave.setQuantidade(chave.getQuantidade() - quantity);
        repository.saveAndFlush(chave);
        log.info("Decrementou a chave {}", rarity);
    }

    public void logKeys() {
        List<Chave> chaves = repository.findAll();
        chaves.sort(Comparator.comparing(Chave::getRarity));
        chaves.forEach(chave -> System.out.println(chave.getQuantidade() + "x - Tipo: " + chave.getRarity()));
    }

}
