package TestMedflix.diga.dev.TestMedflix.Usuario_Admin.UserDetails;

import TestMedflix.diga.dev.TestMedflix.Usuario_Admin.MV.Models.ModelAdmin;
import TestMedflix.diga.dev.TestMedflix.Usuario_Admin.MV.Repositorio.RepositoryAdmin;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ServiceDetailsAdmin implements UserDetailsService {

    RepositoryAdmin repositoryAdmin;

    public ServiceDetailsAdmin(RepositoryAdmin repositoryAdmin) {
        this.repositoryAdmin = repositoryAdmin;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

       ModelAdmin admin = repositoryAdmin.findByNome(username);
       if(admin == null){

            throw new UsernameNotFoundException(username);
       }

        return new UserDetailAdmin(admin);
    }
}
