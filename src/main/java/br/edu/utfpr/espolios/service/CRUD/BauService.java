package br.edu.utfpr.espolios.service.CRUD;

import br.edu.utfpr.espolios.generics.EntityService;
import br.edu.utfpr.espolios.generics.repository.EntityRepository;
import br.edu.utfpr.espolios.models.Bau;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class BauService extends EntityService<Bau> {

    private final EntityRepository<Bau> repository;

    @Override
    public EntityRepository<Bau> getRepository() {
        return repository;
    }

}
