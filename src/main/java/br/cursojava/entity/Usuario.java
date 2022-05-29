package br.cursojava.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import br.cursojava.entity.enums.Role;
import io.smallrye.common.constraint.NotNull;
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
@Table(name = "usuario")
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "usuarioId")
    private Long id;

    @Column(name = "email", unique = true)
    @NotNull
    @Size(min = 10, max = 40)
    private String email;

    @Column(name = "nome")
    @NotNull
    @Size(min=5, max = 100)
    private String nome;

    @NotNull
    @Column(name="senha")
    private String senha;

    @NotNull
    @Column(name="roles")
    @Enumerated (EnumType.STRING)
    private Role roles;

    
}
