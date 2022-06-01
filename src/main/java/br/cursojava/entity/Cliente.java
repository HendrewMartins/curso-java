package br.cursojava.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "cliente")
@Entity
public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "clienteId")
    private Long id;
    
    @Column(name = "nome")
    private String nome;

    @OneToOne(fetch = FetchType.LAZY,  cascade=CascadeType.ALL)
    @JoinColumn(name = "cpfId", referencedColumnName = "cpfid")
    private Cpf cpf;

    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval = true )
    private List<Endereco> endereco; 

    @JoinTable(
        name = "clientesetor",
        joinColumns =  @JoinColumn(name="clienteId", referencedColumnName = "clienteId"),
        inverseJoinColumns = @JoinColumn(name = "setorId", referencedColumnName = "setorId"))
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Setor> setor;
}
