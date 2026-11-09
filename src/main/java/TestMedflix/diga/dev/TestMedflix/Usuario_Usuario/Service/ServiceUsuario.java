package TestMedflix.diga.dev.TestMedflix.Usuario_Usuario.Service;


import TestMedflix.diga.dev.TestMedflix.SecurityClasses.SecurityFilter.TokenService.TokenServiceGeral;
import TestMedflix.diga.dev.TestMedflix.Usuario_Usuario.Models.Dtos.DtoInputUsuario;
import TestMedflix.diga.dev.TestMedflix.Usuario_Usuario.Models.ModelUsuario;
import TestMedflix.diga.dev.TestMedflix.Usuario_Usuario.Repositorio.RepositoryUsuario;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class ServiceUsuario {


    TokenServiceGeral tokenServiceUsuario;
    PasswordEncoder passwordEncoderUsuario;
    RepositoryUsuario repositoryUsuario;
    AuthenticationManager authenticationManagerUsuario;

    public ServiceUsuario(@Qualifier("authenticationManagerUsuario") AuthenticationManager authenticationManagerUsuario,
                          RepositoryUsuario repositoryUsuario,
                          @Qualifier("passwordEncoderUsuario") PasswordEncoder passwordEncoderUsuario
, TokenServiceGeral tokenServiceUsuario
    ) {
        this.authenticationManagerUsuario = authenticationManagerUsuario;
        this.repositoryUsuario = repositoryUsuario;
        this.passwordEncoderUsuario = passwordEncoderUsuario;
        this.tokenServiceUsuario = tokenServiceUsuario;
    }


    //ValidaCadastro
    public void ValidaCadastroLogin(DtoInputUsuario dtoInputUsuario) {

        ModelUsuario usuario = repositoryUsuario.findByEmail(dtoInputUsuario.email());

        if (usuario != null) throw new RuntimeException("Usuario já cadastrado");

    }

    //Salva usuarioo com senha croptgrafada
    public void CadastroSenhaCriptografada(DtoInputUsuario dtoInputUsuario) {

        String senha = dtoInputUsuario.senha();
        ModelUsuario usuario = new ModelUsuario(dtoInputUsuario, passwordEncoderUsuario.encode(senha));
        repositoryUsuario.save(usuario);
    }


    //fluxo de cadastro de Usuario
    public void CadastrarUsuario(DtoInputUsuario dtoInputUsuario) {

        ValidaCadastroLogin(dtoInputUsuario);
        CadastroSenhaCriptografada(dtoInputUsuario);

    }


    //ValidaLogin
    public void ValidaLogin(DtoInputUsuario dtoInputUsuario) {

        String email = dtoInputUsuario.email();

        ModelUsuario Usuario = repositoryUsuario.findByEmail(email);
        if (Usuario == null) throw new  RuntimeException("Usuário ou senha Incorretos");

        if (!Usuario.isStatus()) throw new RuntimeException("Usuário ou senha Incorretos");

    }


    //Valida Senha
    public void ValidaSenha (String senha, String email) {

        ModelUsuario usuario = repositoryUsuario.findByEmail(email);

        if (!passwordEncoderUsuario.matches(senha,usuario.getSenha())) throw new RuntimeException("Senha Incorretos");

}

//GDevolverToken
    public String DevolveToken(String subject) {
        return  tokenServiceUsuario.GerarToken(subject);


    }


    //LogarNoSistema
    public String LoginUsuario(DtoInputUsuario dtoInputUsuario){


        ValidaLogin(dtoInputUsuario);
        ValidaSenha (dtoInputUsuario.senha(),dtoInputUsuario.email());
     return   DevolveToken(dtoInputUsuario.email());


    }

}
