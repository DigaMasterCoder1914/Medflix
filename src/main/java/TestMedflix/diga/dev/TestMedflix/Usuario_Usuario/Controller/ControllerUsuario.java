package TestMedflix.diga.dev.TestMedflix.Usuario_Usuario.Controller;

import TestMedflix.diga.dev.TestMedflix.SecurityClasses.Dtos.DtoToken;
import TestMedflix.diga.dev.TestMedflix.Usuario_Usuario.Models.Dtos.DtoInputUsuario;
import TestMedflix.diga.dev.TestMedflix.Usuario_Usuario.Service.ServiceUsuario;
import jakarta.validation.Valid;
import org.hibernate.dialect.aggregate.H2AggregateSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Medflix/Usuario")
public class ControllerUsuario {

    ServiceUsuario serviceUsuario;

    public ControllerUsuario(ServiceUsuario serviceUsuario) {
        this.serviceUsuario = serviceUsuario;
    }

    @PostMapping("/Usuario/Cadastrar")
    public ResponseEntity CadastrarUsuario( @RequestBody  @Valid DtoInputUsuario dtoInputUsuario){


        serviceUsuario.CadastrarUsuario(dtoInputUsuario);


        return ResponseEntity.ok().body("Usuario Cadastrado com sucesso");

    }

@PostMapping("/Login")
    public ResponseEntity<DtoToken> Login(@Valid @RequestBody DtoInputUsuario dtoInputUsuario){


  DtoToken Resposta = new DtoToken(serviceUsuario.LoginUsuario(dtoInputUsuario)) ;


return  ResponseEntity.status(HttpStatus.OK).body(Resposta);


}












}
