package br.edu.utfpr.espolios.generics.repository;

import br.edu.utfpr.espolios.generics.model.EntityModel;
import br.edu.utfpr.espolios.models.enums.Rarity;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface EntityRepository<T extends EntityModel> extends BaseRepository<T> {

    T findByRarity(Rarity rarity);

}
