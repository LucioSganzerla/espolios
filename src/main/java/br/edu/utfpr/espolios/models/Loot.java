package br.edu.utfpr.espolios.models;

import br.edu.utfpr.espolios.generics.model.BaseModel;
import br.edu.utfpr.espolios.models.enums.Rarity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "loot")
public class Loot extends BaseModel {

    @Setter
    @Column(updatable = false)
    private Rarity rarity;

    @Column(nullable = false, name = "valor_monetario")
    private Double valorMonetario;

    @Setter
    @JoinColumn(name = "inventario_id", nullable = false, foreignKey = @ForeignKey(name = "loot_inventario"))
    @ManyToOne(optional = false)
    private Inventario inventario;

    public Loot(Rarity rarity, Double valorMonetario) {
        this.rarity = rarity;
        this.valorMonetario = valorMonetario;
    }

}
