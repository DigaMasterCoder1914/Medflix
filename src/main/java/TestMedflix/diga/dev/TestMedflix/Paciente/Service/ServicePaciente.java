package TestMedflix.diga.dev.TestMedflix.Paciente.Service;


import TestMedflix.diga.dev.TestMedflix.Paciente.MOdel.Dtos.DtoInputPaciente;
import TestMedflix.diga.dev.TestMedflix.Paciente.MOdel.Paciente;
import TestMedflix.diga.dev.TestMedflix.Paciente.repository.RepositoryPaciente;
import TestMedflix.diga.dev.TestMedflix.Usuario_Usuario.Models.ModelUsuario;
import org.springframework.stereotype.Service;

@Service
public class ServicePaciente {

private final RepositoryPaciente repositoryPaciente;

    public ServicePaciente(RepositoryPaciente repositoryPaciente) {
        this.repositoryPaciente = repositoryPaciente;
    }


    //Valida Cadastro
    public void ValidaCadastro(ModelUsuario usuario, DtoInputPaciente inputPaciente){

        if(usuario.getPaciente() != null) throw new RuntimeException(" Usuario  já cadastrou um Paciente ");

        if( repositoryPaciente.findByTelefoneOrNomeAndSobrenome(inputPaciente.telefone(),
                inputPaciente.nome(),
                inputPaciente.sobrenome()) != null ) throw new RuntimeException(" Dados indisponiveis! ");

    }


    //Cadastra paciente
    public void SalvaPaciente(DtoInputPaciente inputPaciente, ModelUsuario usuario){

        Paciente paciente = new Paciente(inputPaciente,usuario);
    repositoryPaciente.save(paciente);


    }

    //FLUXO Cadastro Paciente
    public void CadastroPaciente(DtoInputPaciente inputPaciente, ModelUsuario usuario){

        ValidaCadastro(usuario,inputPaciente);
        SalvaPaciente(inputPaciente,usuario);


    }





}
