package TestMedflix.diga.dev.TestMedflix.Paciente.repository;

import TestMedflix.diga.dev.TestMedflix.Paciente.MOdel.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryPaciente extends JpaRepository<Paciente, Long> {

    Paciente findByTelefoneOrNomeAndSobrenome(String telefone, String nome, String sobrenome);

}
