package br.edu.utfpr.espolios.service.CRUD;

import br.edu.utfpr.espolios.models.FragmentoChave;
import br.edu.utfpr.espolios.models.enums.ShardType;
import br.edu.utfpr.espolios.repository.FragmentoChaveRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@Slf4j
public record FragmentoChaveService(FragmentoChaveRepository repository) {

    public void save(FragmentoChave chave) {
        log.info("Salvando Chave {}", chave.getShardType());
        repository.saveAndFlush(chave);
        log.info("Chave Salva Tipo {}", chave.getShardType());
    }

    public void increment(ShardType shardType, Integer quantity) {
        log.info("Incrementando chave {}", shardType);
        FragmentoChave chave = repository.findByShardType(shardType);
        chave.setQuantidade(chave.getQuantidade() + quantity);
        repository.save(chave);
        log.info("Incrementou a chave {}", shardType);
    }

    public void decrement(ShardType shardType, Integer quantity) {
        log.info("Decrementando chave {}", shardType);
        FragmentoChave chave = repository.findByShardType(shardType);
        chave.setQuantidade(chave.getQuantidade() - quantity);
        repository.save(chave);
        log.info("Decrementou a chave {}", shardType);
    }

    public void logKeys() {
        List<FragmentoChave> chaves = repository.findAll();
        chaves.sort(Comparator.comparing(FragmentoChave::getShardType));
        chaves.forEach(fragmento -> System.out.println(fragmento.getQuantidade() + "x - Tipo: " + fragmento.getShardType()));
    }

}
