package TestMedflix.diga.dev.TestMedflix.Usuario_Medico.MOdels;

import TestMedflix.diga.dev.TestMedflix.Medico.Model.Model.Medicom;
import TestMedflix.diga.dev.TestMedflix.Usuario_Admin.MV.Models.Dtos.DtoCadMedicoAdmin;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@NoArgsConstructor
@Getter
@Table(name = " USUARIO_MEDICO")
public class ModelMedico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USUARIO_MEDICO")
    private Long id;

    @Column(name = "Senha")
    private String senha;

    @Column(name = "Nome",unique = true)
    private String nome;

    @Column(nullable = false)
    private boolean status;

    @Column(name = "Chave_De_Acesso", updatable = false, nullable = false)
    private String chaveDeAcesso;

    @Column(nullable = false, name = "Crm")
    private String crm;

    @Column(updatable = false, nullable = false, name = "Role")
    private String role = "MEDICO";

    @OneToOne(mappedBy = "usuarioMedico")
   private Medicom medico;

    public void  setMedico(Medicom medico){
         this.medico = medico;

    }

    public ModelMedico(DtoCadMedicoAdmin dtoCadMedicoAdmin, String chaveCriptografada) {
        this.crm = dtoCadMedicoAdmin.crm();
        this.chaveDeAcesso = chaveCriptografada;
        this.status = false;
        this.role = "MEDICO";
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}

