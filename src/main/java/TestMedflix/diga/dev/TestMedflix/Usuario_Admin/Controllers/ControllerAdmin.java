package TestMedflix.diga.dev.TestMedflix.Usuario_Admin.Controllers;

import TestMedflix.diga.dev.TestMedflix.SecurityClasses.SecurityFilter.TokenService.TokenServiceGeral;
import TestMedflix.diga.dev.TestMedflix.Usuario_Admin.MV.Models.Dtos.DtoCadMedicoAdmin;
import TestMedflix.diga.dev.TestMedflix.Usuario_Admin.MV.Models.Dtos.DtoInputAdmin;
import TestMedflix.diga.dev.TestMedflix.Usuario_Admin.MV.Models.Dtos.DtoToken;
import TestMedflix.diga.dev.TestMedflix.Usuario_Admin.MV.Service.ServiceAdmin;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Medflix/Admin")
public class ControllerAdmin {

private final TokenServiceGeral tokenService;
    private final ServiceAdmin serviceAdmin;

    public ControllerAdmin(ServiceAdmin serviceAdmin,
                           TokenServiceGeral tokenService) {

        this.serviceAdmin = serviceAdmin;
this.tokenService = tokenService;
    }

//Admin logar no sistema
    @PostMapping("/Login/Admin")
    public ResponseEntity<DtoToken> LoginAdmin(@Valid @RequestBody DtoInputAdmin inputAdmin){

        DtoToken Token =  serviceAdmin.LoginAdmin(inputAdmin);

        return ResponseEntity.status(HttpStatus.OK).body(Token);

    }


    //Admin Cadastrar Medico
    @PostMapping("/Cadastrar/Medico")
    public ResponseEntity CadastrarMedico(@RequestBody DtoCadMedicoAdmin inputUsuario){

        serviceAdmin.SalvarMedico(inputUsuario);

        return ResponseEntity.ok().body("Medico Cadastrado com sucesso");

    }


}
