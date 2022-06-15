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
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "inventario", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Loot> loots = new ArrayList<>();

    public List<Loot> getLoots() {
        return loots;
    }

}
