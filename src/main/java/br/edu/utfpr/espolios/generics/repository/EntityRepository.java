package br.edu.utfpr.espolios.generics.repository;

import br.edu.utfpr.espolios.generics.model.EntityModel;
import br.edu.utfpr.espolios.models.enums.Rarity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface EntityRepository<T extends EntityModel> extends JpaRepository<T, Long> {

    T findByRarity(Rarity rarity);
    List<T> findAllByRarity(Rarity rarity);

}
