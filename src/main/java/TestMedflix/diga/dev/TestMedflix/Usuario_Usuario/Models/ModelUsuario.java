package TestMedflix.diga.dev.TestMedflix.Usuario_Usuario.Models;

import TestMedflix.diga.dev.TestMedflix.Paciente.MOdel.Paciente;
import TestMedflix.diga.dev.TestMedflix.Usuario_Usuario.Models.Dtos.DtoInputUsuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "USUARIO")
public class ModelUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USUARIO")
    private Long id;

    @Column(nullable = false, unique = true, name = "Email")
    private String email;

    @Column(nullable = false, name = "Senha")
    private String senha;

    @Column( updatable = false, name = "Role")
    private String role;

    private boolean status = true;

    public void setEmail(String email) { this.email = email; }
    public void setSenha(String senha) { this.senha = senha; }

    //Paciente Foreign Key
    @OneToOne(mappedBy = "usuario",cascade = CascadeType.ALL)
   Paciente paciente;

    //Seta Chave Estrangeira
  public void SetPaciente(Paciente paciente) { this.paciente = paciente;}


    //Construtor Dto
    public ModelUsuario(DtoInputUsuario input,String SenhaCriptografada) {
        this.email = input.email();
        this.senha = SenhaCriptografada;
        this.role = "USUARIO";

    }
}

