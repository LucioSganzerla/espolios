package br.edu.utfpr.espolios.service.CRUD;

import br.edu.utfpr.espolios.generics.EntityService;
import br.edu.utfpr.espolios.generics.repository.EntityRepository;
import br.edu.utfpr.espolios.models.Chave;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChaveService extends EntityService<Chave> {

    private final EntityRepository<Chave> repository;

    @Override
    public EntityRepository<Chave> getRepository() {
        return repository;
    }

}
