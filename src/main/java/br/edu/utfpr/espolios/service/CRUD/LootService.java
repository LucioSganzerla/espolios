package br.edu.utfpr.espolios.service.CRUD;

import br.edu.utfpr.espolios.generics.BaseService;
import br.edu.utfpr.espolios.models.Loot;
import br.edu.utfpr.espolios.repository.LootRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class LootService extends BaseService<Loot> {

    private final LootRepository repository;

    @Override
    public LootRepository getRepository() {
        return repository;
    }

}
