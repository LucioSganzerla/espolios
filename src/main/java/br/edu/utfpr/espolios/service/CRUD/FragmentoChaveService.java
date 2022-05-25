package br.edu.utfpr.espolios.service.CRUD;

import br.edu.utfpr.espolios.generics.EntityService;
import br.edu.utfpr.espolios.generics.repository.EntityRepository;
import br.edu.utfpr.espolios.models.FragmentoChave;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class FragmentoChaveService extends EntityService<FragmentoChave> {

    private final EntityRepository<FragmentoChave> repository;

    @Override
    public EntityRepository<FragmentoChave> getRepository() {
        return repository;
    }

}
