package TestMedflix.diga.dev.TestMedflix.Paciente.MOdel;

import TestMedflix.diga.dev.TestMedflix.Paciente.MOdel.Dtos.DtoInputPaciente;
import TestMedflix.diga.dev.TestMedflix.Usuario_Usuario.Models.ModelUsuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Table(
        uniqueConstraints = {
                @UniqueConstraint(name = "unique_nome_sobrenome", columnNames = {"nome", "sobrenome"})
        }
)



@Entity
@NoArgsConstructor
@Getter
public class Paciente {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PACIENTE")
    private Long id;

    @Column(nullable = false)
  private String nome ;

    @Column(nullable = false)
    private  String sobrenome ;

    @Column(nullable = false,unique = true)
    private  String cpf ;

   @Column(nullable = false,unique = true)
    private String telefone;

   @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_USUARIO")
  private ModelUsuario usuario;

   public void SetUsuario(ModelUsuario usuario) {

       this.usuario = usuario;
       usuario.SetPaciente(this);

   }

   public Paciente(DtoInputPaciente inputPaciente, ModelUsuario usuario) {

       this.cpf = inputPaciente.cpf();
       this.nome = inputPaciente.nome();
       this.sobrenome = inputPaciente.sobrenome();
       this.telefone = inputPaciente.telefone();
       this.SetUsuario(usuario);

   }



}
