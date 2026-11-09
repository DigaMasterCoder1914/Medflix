package TestMedflix.diga.dev.TestMedflix.Usuario_Medico.Controller;

import TestMedflix.diga.dev.TestMedflix.Usuario_Medico.MOdels.Dtos.DtoInputCadastro;
import TestMedflix.diga.dev.TestMedflix.Usuario_Medico.MOdels.Dtos.DtoInputLogin;
import TestMedflix.diga.dev.TestMedflix.Usuario_Medico.MOdels.Dtos.DtoInputMedicoAtivar;
import TestMedflix.diga.dev.TestMedflix.SecurityClasses.Dtos.DtoToken;
import TestMedflix.diga.dev.TestMedflix.Usuario_Medico.Service.ServiceMedico;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Medflix/Medico")
public class ControllerMedico {

ServiceMedico serviceMedico;

public ControllerMedico(ServiceMedico  serviceMedico){


    this.serviceMedico=serviceMedico;
}

    @PatchMapping("/Ativar/Medico")
    public ResponseEntity AtivarMedico(@RequestBody DtoInputMedicoAtivar dtoInput){

    serviceMedico.ValidarMedico(dtoInput);

return ResponseEntity.status(HttpStatus.ACCEPTED).body("Medico Válidado com Sucesso");


    }


    @PostMapping("/Cadastrar/Medico")
    public ResponseEntity<DtoToken>  CadastrarMedico(@RequestBody  DtoInputCadastro inputUsuario ){


    String token =  serviceMedico.CadastrarMedico(inputUsuario);


        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new DtoToken(token));

    }


    @PostMapping("/Login")
public ResponseEntity<DtoToken> LoginMedico(@RequestBody DtoInputLogin dtoInput){


  String token =  serviceMedico.LoginMedico(dtoInput);

  return ResponseEntity.status(HttpStatus.ACCEPTED).body(new DtoToken(token));

    }








    //metdodo que verifica se já foi validado

    //ve se  o medico existe e ta true via nome
    //DTO PRA LOGIN
    //AUTENTICATE
    //Token + Liberar a conta


    //verifica se o medico existe com a palavra chave e o crm
    //Cadastrar o medico via palavra Chave
    //quando cadastra muda pra true
    //se já tiver da erro




}
