package br.edu.utfpr.espolios.repository;

import br.edu.utfpr.espolios.generics.repository.EntityRepository;
import br.edu.utfpr.espolios.models.Bau;
import br.edu.utfpr.espolios.models.Chave;
import org.springframework.stereotype.Repository;

@Repository
public interface BauRepository extends EntityRepository<Bau> {

}
