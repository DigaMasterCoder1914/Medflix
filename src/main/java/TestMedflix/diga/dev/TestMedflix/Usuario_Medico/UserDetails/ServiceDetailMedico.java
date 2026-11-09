package TestMedflix.diga.dev.TestMedflix.Usuario_Medico.UserDetails;

import TestMedflix.diga.dev.TestMedflix.Usuario_Medico.MOdels.ModelMedico;
import TestMedflix.diga.dev.TestMedflix.Usuario_Medico.Repository.RepositoryMedicoUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class ServiceDetailMedico implements UserDetailsService {

    private final RepositoryMedicoUser repositoryMedicoUser;

    public ServiceDetailMedico(RepositoryMedicoUser repositoryMedicoUser) {

        this.repositoryMedicoUser = repositoryMedicoUser;
    }

    @Override
    public UserDetails loadUserByUsername(String Crm) throws UsernameNotFoundException {


       ModelMedico Medico =  repositoryMedicoUser.findBycrm(Crm);

        if(Medico == null) throw new UsernameNotFoundException(Crm);

        return new UserDetailsMedico(Medico);

    }
}
