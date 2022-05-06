package br.edu.utfpr.espolios.repository;

import br.edu.utfpr.espolios.models.FragmentoChave;
import br.edu.utfpr.espolios.models.enums.ShardType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FragmentoChaveRepository extends JpaRepository<FragmentoChave, Long> {

    FragmentoChave findByShardType(ShardType shardType);

}
