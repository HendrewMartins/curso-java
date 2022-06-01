package br.cursojava.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.cursojava.entity.enums.TipoPessoa;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cpf")
@Entity
public class Cpf {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cpfid")
    private Long id;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "tipopessoa")
    @Enumerated(EnumType.STRING)
    private TipoPessoa tipoPessoa;
}
