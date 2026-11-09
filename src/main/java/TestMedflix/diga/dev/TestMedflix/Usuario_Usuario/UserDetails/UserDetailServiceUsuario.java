package TestMedflix.diga.dev.TestMedflix.Usuario_Usuario.UserDetails;


import TestMedflix.diga.dev.TestMedflix.Usuario_Usuario.Models.ModelUsuario;
import TestMedflix.diga.dev.TestMedflix.Usuario_Usuario.Repositorio.RepositoryUsuario;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceUsuario implements UserDetailsService {

    RepositoryUsuario repositoryUsuario;

    public UserDetailServiceUsuario(RepositoryUsuario repositoryUsuario){
         this.repositoryUsuario = repositoryUsuario;

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

       ModelUsuario usuario = repositoryUsuario.findByEmail(username);

        if(usuario == null) throw new UsernameNotFoundException("Usuario no encontrado");

        return new UserDetailsUsuario(usuario);

    }
}
