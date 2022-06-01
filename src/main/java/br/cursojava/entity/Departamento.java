package br.cursojava.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
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
@Table(name = "departamento")
@Entity
public class Departamento {
    
    @EmbeddedId
    private DepartamentoId id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresaid", referencedColumnName = "empresaId", insertable = false, updatable = false)
    private Empresa empresa;
    
    @Column(name = "nome")
    private String nome;
}
