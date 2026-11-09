package TestMedflix.diga.dev.TestMedflix.Paciente.Controller;

import TestMedflix.diga.dev.TestMedflix.Paciente.MOdel.Dtos.DtoInputPaciente;
import TestMedflix.diga.dev.TestMedflix.Paciente.Service.ServicePaciente;
import TestMedflix.diga.dev.TestMedflix.Usuario_Usuario.Models.ModelUsuario;
import TestMedflix.diga.dev.TestMedflix.Usuario_Usuario.Repositorio.RepositoryUsuario;
import TestMedflix.diga.dev.TestMedflix.Usuario_Usuario.UserDetails.UserDetailsUsuario;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Medflix/Paciente")
public class ControllerPaciente {

    private final ServicePaciente servicePaciente;
private final RepositoryUsuario repositoryUsuario;


    public ControllerPaciente(ServicePaciente servicePaciente, RepositoryUsuario repositoryUsuario) {
        this.servicePaciente = servicePaciente;
        this.repositoryUsuario = repositoryUsuario;
    }

    @PostMapping("/Cadastrar")
    public ResponseEntity Cadastrar(

            @AuthenticationPrincipal UserDetailsUsuario userDetailsUsuario,
            @RequestBody @Valid DtoInputPaciente inputPaciente)

    {

       ModelUsuario usuario =  repositoryUsuario.findByEmail(userDetailsUsuario.getUsername() );

        servicePaciente.CadastroPaciente(inputPaciente,usuario);

        return ResponseEntity.ok().body("Paciente Cadastrado com sucesso");

    }




}