package TestMedflix.diga.dev.TestMedflix.Medico.Model.Model;

import TestMedflix.diga.dev.TestMedflix.Medico.Model.Model.DTO.DtoInputMedico;
import TestMedflix.diga.dev.TestMedflix.Usuario_Medico.MOdels.ModelMedico;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "MEDICO")
public class Medicom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_MEDICO")
    private Long id;

    private Boolean cirurgiao;


    @Column(name = "especializacao")
    @Enumerated(EnumType.STRING)
    private especializacao especialidade = especializacao.NAO_RELATADO;

    @Column(unique = true, nullable = false)
    private  String crm;

    @Column(nullable = false)
    private  String nome;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_USUARIO_MEDICO")
    private ModelMedico usuarioMedico;


    public void setUsuarioMedico(ModelMedico usuarioMedico) {
        this.usuarioMedico = usuarioMedico;
        usuarioMedico.setMedico(this);
    }

public Medicom(DtoInputMedico  dtoInputMedico, ModelMedico modelMedico) {

this.crm = modelMedico.getCrm();
this.nome = dtoInputMedico.nome();
this.especialidade = especializacao.defineEspecialidade(dtoInputMedico.especialidade());
this.cirurgiao = dtoInputMedico.Cirurgiao();

setUsuarioMedico(modelMedico);

}


}


