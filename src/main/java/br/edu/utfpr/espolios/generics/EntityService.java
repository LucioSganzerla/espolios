package br.edu.utfpr.espolios.generics;

import br.edu.utfpr.espolios.generics.model.EntityModel;
import br.edu.utfpr.espolios.generics.repository.EntityRepository;
import br.edu.utfpr.espolios.models.enums.Rarity;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;
import java.util.List;

@Slf4j
public abstract class EntityService<T extends EntityModel> {

    public abstract EntityRepository<T> getRepository();

    public void save(T bau) {
        log.info("Salvando {} {}",this.getClass().getSimpleName(), bau.getRarity());
        getRepository().saveAndFlush(bau);
        log.info("{} Salva Tipo {}",  this.getClass().getSimpleName(), bau.getRarity());
    }

    public void increment(Rarity rarity, Integer quantity) {
        log.info("Incrementando {} {}", this.getClass().getSimpleName(), rarity);
        T bau = getRepository().findByRarity(rarity);
        bau.setQuantidade(bau.getQuantidade() + quantity);
        getRepository().saveAndFlush(bau);
        log.info("Incrementou {} {}", this.getClass().getSimpleName(), rarity);
    }

    public void decrement(Rarity rarity, Integer quantity) {
        log.info("Decrementando {} {}", this.getClass().getSimpleName(), rarity);
        T bau = getRepository().findByRarity(rarity);
        bau.setQuantidade(bau.getQuantidade() - quantity);
        getRepository().saveAndFlush(bau);
        log.info("Decrementou {} {}", this.getClass().getSimpleName(), rarity);
    }

    public void log() {
        List<T> baus = getRepository().findAll();
        baus.sort(Comparator.comparing(T::getRarity));
        baus.forEach(bau -> log.info(bau.getQuantidade() + "x - Tipo: " + bau.getRarity()));
    }

}
