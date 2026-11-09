package TestMedflix.diga.dev.TestMedflix.Usuario_Admin.MV.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Table(name = "ADMIN")
@Entity
public class ModelAdmin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ADMIN")
    private Long id;

    // A variável Java TEM que ser minúscula (nome), a coluna do banco pode ser maiúscula (Nome)
    @Column(unique = true, updatable = false, name = "Nome")
    private String nome;

    @Column(updatable = false, insertable = false, name = "Senha")
    private String senha;

    @Column(insertable = false, updatable = false, name = "Role")
    private String role = "ADMIN";

    private boolean status = true;
}