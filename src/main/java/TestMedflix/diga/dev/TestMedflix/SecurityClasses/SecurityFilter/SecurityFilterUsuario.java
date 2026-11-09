package TestMedflix.diga.dev.TestMedflix.SecurityClasses.SecurityFilter;

import TestMedflix.diga.dev.TestMedflix.SecurityClasses.SecurityFilter.TokenService.TokenServiceGeral;
import TestMedflix.diga.dev.TestMedflix.Usuario_Usuario.Repositorio.RepositoryUsuario;
import TestMedflix.diga.dev.TestMedflix.Usuario_Usuario.UserDetails.UserDetailsUsuario;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilterUsuario extends OncePerRequestFilter {

    private final TokenServiceGeral tokenServiceGeral;
    private final RepositoryUsuario repositoryUsuario;


    public SecurityFilterUsuario(TokenServiceGeral tokenServiceGeral,
    RepositoryUsuario repositoryUsuario)

    {

        this.tokenServiceGeral = tokenServiceGeral;

        this.repositoryUsuario = repositoryUsuario;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        String Token = RecuperarToken(request);

        if(Token != null) {

            var subject = tokenServiceGeral.PegarSubject(Token);

            if(subject != null){

                var usuarioEncontrado = repositoryUsuario.findByEmail(subject);

                if(usuarioEncontrado != null){

                    UserDetailsUsuario detailUsuario = new UserDetailsUsuario(usuarioEncontrado);

                    var autenticado =    new UsernamePasswordAuthenticationToken(detailUsuario , null, detailUsuario .getAuthorities());

                    SecurityContextHolder.getContext().setAuthentication(autenticado );
                }


            }


        }
        filterChain.doFilter(request, response);

    }







    public String RecuperarToken(HttpServletRequest Requisicao) {
        var Req = Requisicao.getHeader("Authorization");

        //Mudar no futuro
        if(Req != null ){
            return  Req.replace("Bearer ", "");

        }
        return null;

    }



}
