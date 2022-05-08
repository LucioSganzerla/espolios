package br.edu.utfpr.espolios.repository;

import br.edu.utfpr.espolios.models.Chave;
import br.edu.utfpr.espolios.models.enums.ShardType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChaveRepository extends JpaRepository<Chave, Long> {

    Chave findChaveByType(ShardType type);
}
