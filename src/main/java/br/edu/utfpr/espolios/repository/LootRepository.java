package br.edu.utfpr.espolios.repository;

import br.edu.utfpr.espolios.generics.repository.EntityRepository;
import br.edu.utfpr.espolios.models.Bau;
import br.edu.utfpr.espolios.models.Loot;
import br.edu.utfpr.espolios.models.enums.Rarity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LootRepository extends EntityRepository<Loot> {

    List<Loot> findAllByRarityAndIsDropable(Rarity rarity, boolean isDropable);

}
