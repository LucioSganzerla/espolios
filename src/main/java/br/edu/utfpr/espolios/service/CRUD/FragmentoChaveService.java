package br.edu.utfpr.espolios.service.CRUD;

import br.edu.utfpr.espolios.models.FragmentoChave;
import br.edu.utfpr.espolios.models.enums.Rarity;
import br.edu.utfpr.espolios.repository.FragmentoChaveRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@Slf4j
public record FragmentoChaveService(FragmentoChaveRepository repository) {

    public void save(FragmentoChave chave) {
        log.info("Salvando Fragmento de Chave {}", chave.getRarity());
        repository.saveAndFlush(chave);
        log.info("Fragmento deChave Salva Tipo {}", chave.getRarity());
    }

    public void increment(Rarity rarity, Integer quantity) {
        log.info("Incrementando fragmentos de chave {}", rarity);
        FragmentoChave chave = repository.findByRarity(rarity);
        chave.setQuantidade(chave.getQuantidade() + quantity);
        repository.save(chave);
        log.info("Incrementou os fragmentos de chave {}", rarity);
    }

    public void decrement(Rarity rarity, Integer quantity) {
        log.info("Decrementando os fragmentos de chave {}", rarity);
        FragmentoChave chave = repository.findByRarity(rarity);
        chave.setQuantidade(chave.getQuantidade() - quantity);
        repository.save(chave);
        log.info("Decrementou os fragmento de chave {}", rarity);
    }

    public void log() {
        List<FragmentoChave> chaves = repository.findAll();
        chaves.sort(Comparator.comparing(FragmentoChave::getRarity));
        chaves.forEach(fragmento -> log.info(fragmento.getQuantidade() + "x - Tipo: " + fragmento.getRarity()));
    }

}
