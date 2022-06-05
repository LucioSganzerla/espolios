package br.edu.utfpr.espolios.repository;

import br.edu.utfpr.espolios.generics.repository.BaseRepository;
import br.edu.utfpr.espolios.models.Loot;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LootRepository extends BaseRepository<Loot> {

    List<Loot> findAllByInventarioId(Long id);

}
