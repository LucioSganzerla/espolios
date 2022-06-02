package br.edu.utfpr.espolios.generics.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class BaseModel implements Serializable {

    @Id
    @Getter
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

}
