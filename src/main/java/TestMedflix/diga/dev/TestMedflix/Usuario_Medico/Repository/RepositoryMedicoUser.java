package TestMedflix.diga.dev.TestMedflix.Usuario_Medico.Repository;

import TestMedflix.diga.dev.TestMedflix.Usuario_Medico.MOdels.ModelMedico;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RepositoryMedicoUser extends JpaRepository<ModelMedico, Long> {

    ModelMedico findBycrm( String Crm);


    boolean existsBycrmAndChaveDeAcesso(String Crm, String ChaveAcesso);

    ModelMedico findByNome(String subject);


    @Transactional
    @Modifying
    @Query("UPDATE ModelMedico u  SET u.status = TRUE WHERE u.crm = :crm " )
    void ValidarMedico(@Param("crm") String crm);

    @Transactional
    @Modifying
    @Query("UPDATE ModelMedico u SET u.nome = :nome WHERE u.crm = :crm")
    void atualizarMedicoNome(String crm, String nome);

    @Transactional
    @Modifying
    @Query("UPDATE ModelMedico u SET  u.senha = :senha WHERE u.crm = :crm")
    void atualizarMedicoSenha(@Param("crm") String crm, @Param("senha") String senha);

}
