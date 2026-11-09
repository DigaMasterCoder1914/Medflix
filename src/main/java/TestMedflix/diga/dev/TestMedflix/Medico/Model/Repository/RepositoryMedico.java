package TestMedflix.diga.dev.TestMedflix.Medico.Model.Repository;

import TestMedflix.diga.dev.TestMedflix.Medico.Model.Model.Medicom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryMedico extends JpaRepository<Medicom,Long> {


    Medicom findBycrm(String crm);



}
