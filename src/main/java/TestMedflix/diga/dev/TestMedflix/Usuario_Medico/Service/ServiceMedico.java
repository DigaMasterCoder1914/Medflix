package TestMedflix.diga.dev.TestMedflix.Usuario_Medico.Service;

import TestMedflix.diga.dev.TestMedflix.SecurityClasses.SecurityFilter.TokenService.TokenServiceGeral;
import TestMedflix.diga.dev.TestMedflix.Usuario_Medico.MOdels.Dtos.DtoInputCadastro;
import TestMedflix.diga.dev.TestMedflix.Usuario_Medico.MOdels.Dtos.DtoInputLogin;
import TestMedflix.diga.dev.TestMedflix.Usuario_Medico.MOdels.Dtos.DtoInputMedicoAtivar;
import TestMedflix.diga.dev.TestMedflix.Usuario_Medico.MOdels.ModelMedico;
import TestMedflix.diga.dev.TestMedflix.Usuario_Medico.Repository.RepositoryMedicoUser;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ServiceMedico {

    private final RepositoryMedicoUser repositoryMedicoUser;

    private final AuthenticationManager authenticationManagerMedico;

    private final PasswordEncoder passwordEncoderMedico;
    TokenServiceGeral TokenServiceMedico;


    public ServiceMedico(RepositoryMedicoUser repositoryMedicoUser,
                         @Qualifier("authenticationManagerMedico") AuthenticationManager authenticationManagerMedico,
                         PasswordEncoder passwordEncoderMedico,
                         TokenServiceGeral TokenServiceMedico) {


        this.repositoryMedicoUser = repositoryMedicoUser;
        this.authenticationManagerMedico = authenticationManagerMedico;
        this.passwordEncoderMedico = passwordEncoderMedico;
        this.TokenServiceMedico = TokenServiceMedico;
    }




    //Valida o médico
    public void ValidarMedico(DtoInputMedicoAtivar input) {

        ValidarPrimeiraVezExceptions(input);
        repositoryMedicoUser.ValidarMedico(input.crm());

    }



    //Testa se O medico já foi validado,é real,senha ta certa
    public void ValidarPrimeiraVezExceptions(DtoInputMedicoAtivar input) {

        ModelMedico medico = repositoryMedicoUser.findBycrm(input.crm());

        if (medico == null) throw new RuntimeException("Medico não existente no Sistema");


        if (medico.isStatus()) throw new RuntimeException("Medico Já foi Validado");

        String ChaveEsperada = medico.getChaveDeAcesso();
        if (!(passwordEncoderMedico.matches(input.chaveAcesso(), ChaveEsperada)))
            throw new RuntimeException("Usuario ou Senha incorretos");


    }





    //Cadastrar Medico
    public String CadastrarMedico(DtoInputCadastro dtoInputCadastro) {

        ValidadoExceptionsCadastro(dtoInputCadastro.crm());
        CadastroException(dtoInputCadastro.crm());
        CriarCadastro(dtoInputCadastro);

        return  GerarTokenMedico(dtoInputCadastro);

    }




//Validar Expections Cadastro
        public void ValidadoExceptionsCadastro( String crm){

        ModelMedico medico = repositoryMedicoUser.findBycrm(crm);
        if(medico == null) throw new RuntimeException("Medico não existente no Sistema");
        if (!medico.isStatus())throw new RuntimeException("Medico não foi validado");


    }

    //Validacoes especificas de Cadastro
    public void CadastroException(String crm){

        ModelMedico medico = repositoryMedicoUser.findBycrm(crm);
        if( medico.getNome() != null) throw new RuntimeException("Usuario Já cadastrado");


    }

    //Criar Cadastro com senha Criptografada
    public void CriarCadastro(DtoInputCadastro  dtoInputCadastro){

        String senha = passwordEncoderMedico.encode( dtoInputCadastro.senha());



        repositoryMedicoUser.atualizarMedicoNome(dtoInputCadastro.crm(), dtoInputCadastro.nome());
        repositoryMedicoUser.atualizarMedicoSenha(dtoInputCadastro.crm(), senha);

    }

    //Gerar Token
    public String GerarTokenMedico(DtoInputCadastro dtoInputCadastro){

       ModelMedico medico =  repositoryMedicoUser.findBycrm(dtoInputCadastro.crm());


       return TokenServiceMedico.GerarToken(medico.getNome());

    }


    //Expceitns Login
    public void ValidadoExceptionsLogin( String nome){

        ModelMedico medico = repositoryMedicoUser.findByNome(nome);
        if(medico == null) throw new RuntimeException("Medico não existente no Sistema");
        if (!medico.isStatus())throw new RuntimeException("Medico não foi validado");


    }

    public String LoginMedico( DtoInputLogin dtoInputLogin){

        ValidadoExceptionsLogin(dtoInputLogin.nome());

   ;ModelMedico medico = repositoryMedicoUser.findByNome(dtoInputLogin.nome());

        if(!passwordEncoderMedico.matches(dtoInputLogin.senha(),medico.getSenha())) throw new RuntimeException("Senha incorreta");

       return TokenServiceMedico.GerarToken(medico.getNome());



    }

}

//Login Medico







/*









 */



