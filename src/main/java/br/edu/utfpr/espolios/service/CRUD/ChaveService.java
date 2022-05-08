package br.edu.utfpr.espolios.service.CRUD;

import br.edu.utfpr.espolios.models.Chave;
import br.edu.utfpr.espolios.models.FragmentoChave;
import br.edu.utfpr.espolios.models.enums.ShardType;
import br.edu.utfpr.espolios.repository.ChaveRepository;
import br.edu.utfpr.espolios.repository.FragmentoChaveRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@Slf4j
public record ChaveService(ChaveRepository repository) {

    public void save(Chave chave) {
        log.info("Salvando Chave {}", chave.getType());
        repository.saveAndFlush(chave);
        log.info("Chave Salva Tipo {}", chave.getType());
    }

    public void increment(ShardType type, Integer quantity) {
        log.info("Incrementando chave {}", type);
        Chave chave = repository.findChaveByType(type);
        chave.setQuantidade(chave.getQuantidade() + quantity);
        repository.saveAndFlush(chave);
        log.info("Incrementou a chave {}", type);
    }

    public void decrement(ShardType type, Integer quantity) {
        log.info("Decrementando chave {}", type);
        Chave chave = repository.findChaveByType(type);
        chave.setQuantidade(chave.getQuantidade() - quantity);
        repository.saveAndFlush(chave);
        log.info("Decrementou a chave {}", type);
    }

    public void logKeys() {
        List<Chave> chaves = repository.findAll();
        chaves.sort(Comparator.comparing(Chave::getType));
        chaves.forEach(chave -> System.out.println(chave.getQuantidade() + "x - Tipo: " + chave.getType()));
    }

}
