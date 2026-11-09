package TestMedflix.diga.dev.TestMedflix.Usuario_Admin.MV.Service;


import TestMedflix.diga.dev.TestMedflix.SecurityClasses.SecurityFilter.TokenService.TokenServiceGeral;
import TestMedflix.diga.dev.TestMedflix.Usuario_Admin.MV.Models.Dtos.DtoCadMedicoAdmin;
import TestMedflix.diga.dev.TestMedflix.Usuario_Admin.MV.Models.Dtos.DtoInputAdmin;
import TestMedflix.diga.dev.TestMedflix.Usuario_Admin.MV.Models.Dtos.DtoToken;
import TestMedflix.diga.dev.TestMedflix.Usuario_Admin.UserDetails.UserDetailAdmin;
import TestMedflix.diga.dev.TestMedflix.Usuario_Medico.MOdels.ModelMedico;
import TestMedflix.diga.dev.TestMedflix.Usuario_Medico.Repository.RepositoryMedicoUser;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ServiceAdmin {

  AuthenticationManager authenticationManagerAdmin;
    TokenServiceGeral tokenService;
    RepositoryMedicoUser repositoryMedicoUser;
    PasswordEncoder passwordEncoderAdmin;

    public ServiceAdmin(RepositoryMedicoUser repositoryMedicoUser,
                        PasswordEncoder passwordEncoderAdmin,
                        TokenServiceGeral tokenService ,
                        @Qualifier("authenticationManagerAdmin") AuthenticationManager authenticationManagerAdmin  ) {

        this.authenticationManagerAdmin = authenticationManagerAdmin;
        this.tokenService = tokenService;
        this.repositoryMedicoUser = repositoryMedicoUser;
        this.passwordEncoderAdmin = passwordEncoderAdmin;
    }


public void SalvarMedico(DtoCadMedicoAdmin dtoCadMedicoAdmin){
        ModelMedico modelMedico = repositoryMedicoUser.findBycrm(dtoCadMedicoAdmin.crm());

        if(modelMedico != null) throw  new RuntimeException("Medico Já cadastrado");

    String ChaveCriptografada = passwordEncoderAdmin.encode(dtoCadMedicoAdmin.ChaveAcesso());

repositoryMedicoUser.save(new ModelMedico(dtoCadMedicoAdmin,ChaveCriptografada));

}

public DtoToken LoginAdmin(DtoInputAdmin inputAdmin){

var autenticar = new UsernamePasswordAuthenticationToken(inputAdmin.nome(), inputAdmin.senha());

    Authentication autenticado = authenticationManagerAdmin.authenticate(autenticar);

UserDetailAdmin user = (UserDetailAdmin) autenticado.getPrincipal();

   String token =  tokenService.GerarToken(user.getUsername());

return new DtoToken( token);
}


}

