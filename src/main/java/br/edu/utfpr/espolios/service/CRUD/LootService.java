package br.edu.utfpr.espolios.service.CRUD;

import br.edu.utfpr.espolios.repository.LootRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public record LootService(LootRepository repository) {

}
