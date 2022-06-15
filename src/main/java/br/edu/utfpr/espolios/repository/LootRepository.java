package br.edu.utfpr.espolios.repository;

import br.edu.utfpr.espolios.generics.repository.BaseRepository;
import br.edu.utfpr.espolios.models.Loot;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface LootRepository extends BaseRepository<Loot> {

    List<Loot> findAllByInventarioId(Long id);

    @Transactional
    @Modifying
    @Query("delete from Loot where id = ?1")
    void deleteLootById(Long id);

}
