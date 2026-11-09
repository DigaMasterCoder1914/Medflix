package TestMedflix.diga.dev.TestMedflix.SecurityClasses.SecurityFilter;

import TestMedflix.diga.dev.TestMedflix.SecurityClasses.SecurityFilter.TokenService.TokenServiceGeral;
import TestMedflix.diga.dev.TestMedflix.Usuario_Admin.MV.Repositorio.RepositoryAdmin;
import TestMedflix.diga.dev.TestMedflix.Usuario_Admin.UserDetails.UserDetailAdmin;
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
public class SecurityFilterAdmin extends OncePerRequestFilter {

  private final TokenServiceGeral tokenServiceGeral;
  private final RepositoryAdmin  repositoryAdmin  ;


public SecurityFilterAdmin(TokenServiceGeral tokenServiceGeral,
    RepositoryAdmin  repositoryAdmin           ){

    this.tokenServiceGeral = tokenServiceGeral;
    this.repositoryAdmin = repositoryAdmin;
}

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


          String Token = RecuperarToken(request);

      if(Token != null) {

        var subject = tokenServiceGeral.PegarSubject(Token);

          if(subject != null){

              var usuarioEncontrado = repositoryAdmin.findByNome(subject);

              if(usuarioEncontrado != null){

                  UserDetailAdmin detailAmin = new UserDetailAdmin(usuarioEncontrado);

                  var autenticado =    new UsernamePasswordAuthenticationToken(detailAmin, null, detailAmin.getAuthorities());

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
                        return   Req.replace("Bearer ", "");

                    }
                    return null;

                }



}
