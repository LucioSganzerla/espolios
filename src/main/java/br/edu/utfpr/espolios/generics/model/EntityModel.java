package br.edu.utfpr.espolios.generics.model;

import br.edu.utfpr.espolios.models.enums.Rarity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class EntityModel extends BaseModel {

    @Setter
    @Column
    private Integer quantidade;

    @Setter
    @Column(updatable = false)
    private Rarity rarity;

}
