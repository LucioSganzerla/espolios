package br.edu.utfpr.espolios.models;

import br.edu.utfpr.espolios.generics.model.EntityModel;
import br.edu.utfpr.espolios.models.enums.Rarity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "bau")
public class Bau extends EntityModel {

    public Bau(Integer quantidade, Rarity rarity) {
        super.setQuantidade(quantidade);
        super.setRarity(rarity);
    }

}
