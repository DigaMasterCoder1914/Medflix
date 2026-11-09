package TestMedflix.diga.dev.TestMedflix.Usuario_Usuario.Repositorio;

import TestMedflix.diga.dev.TestMedflix.Usuario_Usuario.Models.ModelUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryUsuario extends JpaRepository<ModelUsuario, Long> {

    ModelUsuario findByEmail(String username);

}
