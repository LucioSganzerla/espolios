package br.edu.utfpr.espolios.models;

import br.edu.utfpr.espolios.generics.model.BaseModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "inventario")
public class Inventario extends BaseModel {

    @Setter
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "inventario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Loot> loots = new ArrayList<>();

    public List<Loot> getLoots() {
        return loots;
    }

    public List<Loot> addLoot(Loot loot) {
        if (this.getLoots() == null) {
            this.setLoots(new ArrayList<>());
        }
        loot.setInventario(this);
        this.loots.add(loot);
        return this.loots;
    }

    public List<Loot> removeLoot(Loot loot) {
        if (this.getLoots() == null) {
            this.setLoots(new ArrayList<>());
        } else {
            this.loots.remove(loot);
        }
        loot.setInventario(this);
        return this.loots;
    }
}
