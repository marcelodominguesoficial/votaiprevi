package br.com.votaaiprevi.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="E")
public class Eleitor extends Usuario {

}
