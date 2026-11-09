package TestMedflix.diga.dev.TestMedflix.Medico.Model.Service;

import TestMedflix.diga.dev.TestMedflix.Medico.Model.Model.DTO.DtoInputMedico;
import TestMedflix.diga.dev.TestMedflix.Medico.Model.Model.Medicom;
import TestMedflix.diga.dev.TestMedflix.Medico.Model.Repository.RepositoryMedico;
import TestMedflix.diga.dev.TestMedflix.Usuario_Medico.MOdels.ModelMedico;
import org.springframework.stereotype.Service;

@Service
public class ServiceMedicom {

   private final RepositoryMedico repositoryMedico;

   public ServiceMedicom(RepositoryMedico repositoryMedico) {
      this.repositoryMedico = repositoryMedico;
   }

   //vaLIDA CADASTRO Medico
public void ValidaCadastro(ModelMedico  modelMedico, DtoInputMedico dtoInputMedico){

   if(modelMedico.getMedico() != null) throw new RuntimeException("Usuario já cadastrou um medico" );

   if (repositoryMedico.findBycrm(modelMedico.getCrm()) != null )throw new RuntimeException("Medico já cadastrado");


}

//CadastraMedico
public void CadastraMedico(ModelMedico modelMedico, DtoInputMedico dtoInputMedico){

      Medicom medico = new Medicom(dtoInputMedico,modelMedico);

      repositoryMedico.save(medico);

}

//Fluxo Cadastro

  public void  PreencheDados(ModelMedico modelMedico, DtoInputMedico dtoInputMedico){

     ValidaCadastro(modelMedico,dtoInputMedico);
             CadastraMedico(modelMedico, dtoInputMedico);
  }


}
