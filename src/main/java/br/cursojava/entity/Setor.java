package br.cursojava.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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
@Table(name = "setor")
@Entity
public class Setor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "setorId")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "empresaId", referencedColumnName = "empresaId"),
        @JoinColumn(name = "departamentoId", referencedColumnName = "sequenciaDepartamento")
    })
    private Departamento departamento;
    
}
