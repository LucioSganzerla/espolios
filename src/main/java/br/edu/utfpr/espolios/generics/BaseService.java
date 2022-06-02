package br.edu.utfpr.espolios.generics;

import br.edu.utfpr.espolios.generics.model.BaseModel;
import br.edu.utfpr.espolios.generics.repository.BaseRepository;
import br.edu.utfpr.espolios.generics.repository.EntityRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class BaseService<T extends BaseModel> {

    public abstract BaseRepository<T> getRepository();

}
