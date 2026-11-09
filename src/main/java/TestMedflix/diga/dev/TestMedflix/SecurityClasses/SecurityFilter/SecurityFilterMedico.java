package TestMedflix.diga.dev.TestMedflix.SecurityClasses.SecurityFilter;

import TestMedflix.diga.dev.TestMedflix.SecurityClasses.SecurityFilter.TokenService.TokenServiceGeral;
import TestMedflix.diga.dev.TestMedflix.Usuario_Medico.MOdels.ModelMedico;
import TestMedflix.diga.dev.TestMedflix.Usuario_Medico.Repository.RepositoryMedicoUser;
import TestMedflix.diga.dev.TestMedflix.Usuario_Medico.UserDetails.UserDetailsMedico;
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
public class SecurityFilterMedico extends OncePerRequestFilter {

    private final TokenServiceGeral tokenServiceGeral;
    private final RepositoryMedicoUser repositoryMedicoUser;


    public SecurityFilterMedico(TokenServiceGeral tokenServiceGeral,
                                 RepositoryMedicoUser repositoryMedicoUser)

    {

        this.tokenServiceGeral = tokenServiceGeral;

       this.repositoryMedicoUser = repositoryMedicoUser;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        String Token = RecuperarToken(request);

        if(Token != null) {

            var subject = tokenServiceGeral.PegarSubject(Token);

            if(subject != null){

                ModelMedico usuarioEncontrado = repositoryMedicoUser.findByNome(subject);

                if(usuarioEncontrado != null){

                    UserDetailsMedico detailUsuario = new UserDetailsMedico(usuarioEncontrado);

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
