package br.edu.utfpr.espolios.models;

import br.edu.utfpr.espolios.generics.model.BaseModel;
import br.edu.utfpr.espolios.models.enums.Rarity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Loot extends BaseModel {

    @Setter
    @Column(updatable = false)
    private Rarity rarity;

    @Column(nullable = false, name = "valor_monetario")
    private Double valorMonetario;

    @Column(nullable = false, name = "adquirivel")
    private Boolean isDropable;

    public Loot(Rarity rarity, Double valorMonetario) {
        this.rarity = rarity;
        this.valorMonetario = valorMonetario;
        this.isDropable = true;
    }

}
