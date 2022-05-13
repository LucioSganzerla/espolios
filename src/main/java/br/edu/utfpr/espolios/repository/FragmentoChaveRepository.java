package br.edu.utfpr.espolios.repository;

import br.edu.utfpr.espolios.models.FragmentoChave;
import br.edu.utfpr.espolios.models.enums.Rarity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FragmentoChaveRepository extends JpaRepository<FragmentoChave, Long> {

    FragmentoChave findByRarity(Rarity rarity);

}
