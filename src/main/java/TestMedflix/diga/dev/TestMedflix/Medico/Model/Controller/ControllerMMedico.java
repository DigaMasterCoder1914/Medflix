package TestMedflix.diga.dev.TestMedflix.Medico.Model.Controller;

import TestMedflix.diga.dev.TestMedflix.Medico.Model.Model.DTO.DtoInputMedico;
import TestMedflix.diga.dev.TestMedflix.Medico.Model.Service.ServiceMedicom;
import TestMedflix.diga.dev.TestMedflix.Usuario_Medico.MOdels.ModelMedico;
import TestMedflix.diga.dev.TestMedflix.Usuario_Medico.Repository.RepositoryMedicoUser;
import TestMedflix.diga.dev.TestMedflix.Usuario_Medico.UserDetails.UserDetailsMedico;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/Medflix/Medico")
public class ControllerMMedico {

   private final ServiceMedicom serviceMedicom;
   private RepositoryMedicoUser repositoryMedicoUser;

   public ControllerMMedico(ServiceMedicom serviceMedicom, RepositoryMedicoUser repositoryMedicoUser) {

      this.serviceMedicom = serviceMedicom;
       this.repositoryMedicoUser = repositoryMedicoUser;
   }

   @PostMapping("/Cadastrar/Dados")
   public ResponseEntity CadastrarDados(@RequestBody  @Valid DtoInputMedico dtoInputMedico,
   @AuthenticationPrincipal UserDetailsMedico userDetailsMedico

   ) {

      ModelMedico modelMedico = repositoryMedicoUser.findByNome(userDetailsMedico.getUsername()) ;

      serviceMedicom.PreencheDados(modelMedico,dtoInputMedico);

      return ResponseEntity.ok().body("Medico Cadastrado com sucesso");

   }

}
