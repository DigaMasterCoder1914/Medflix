package TestMedflix.diga.dev.TestMedflix.Usuario_Admin.MV.Repositorio;

import TestMedflix.diga.dev.TestMedflix.Usuario_Admin.MV.Models.ModelAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryAdmin  extends JpaRepository<ModelAdmin,Long> {


    ModelAdmin findByNome(String username);
}
