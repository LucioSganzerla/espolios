package br.edu.utfpr.espolios.models;

import br.edu.utfpr.espolios.models.enums.Rarity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FragmentoChave {

    @Id
    @Getter
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Setter
    @Column
    private Integer quantidade;

    @Column(updatable = false)
    private Rarity rarity;

    public FragmentoChave(int quantidade, Rarity rarity) {
        this.quantidade = quantidade;
        this.rarity = rarity;
    }
}
