package TestMedflix.diga.dev.TestMedflix.SecurityClasses.SecutiyFilterChain;


import TestMedflix.diga.dev.TestMedflix.SecurityClasses.SecurityFilter.SecurityFilterAdmin;
import TestMedflix.diga.dev.TestMedflix.SecurityClasses.SecurityFilter.SecurityFilterMedico;
import TestMedflix.diga.dev.TestMedflix.SecurityClasses.SecurityFilter.SecurityFilterUsuario;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityFilterChainGeral {


    private final SecurityFilterMedico securityFilterMedico;
    private final SecurityFilterUsuario securityFilterUsuario;
    private final SecurityFilterAdmin securityFilterAdmin;



public SecurityFilterChainGeral(SecurityFilterAdmin securityFilterAdmin,
                                SecurityFilterMedico securityFilterMedico,
                                SecurityFilterUsuario securityFilterUsuario) {

    this.securityFilterAdmin = securityFilterAdmin;
    this.securityFilterMedico = securityFilterMedico;
    this.securityFilterUsuario = securityFilterUsuario;
}


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {




      return http.csrf(x -> x.disable())
.sessionManagement(x -> x.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(x ->{


                    x.requestMatchers(HttpMethod.POST,"/Medflix/Medico/Cadastrar/Dados").hasAuthority("MEDICO");
                    x.requestMatchers(HttpMethod.POST,"/Medflix/Paciente/Cadastrar").hasAuthority("USUARIO");
                    x.requestMatchers(HttpMethod.POST,"/Medflix/Usuario/Login").permitAll();
                    x.requestMatchers(HttpMethod.POST,"/Medflix/Usuario/Usuario/Cadastrar").permitAll();
                    x.requestMatchers(HttpMethod.POST, "/Medflix/Medico/Login").permitAll();
                    x.requestMatchers(HttpMethod.POST,"/Medflix/Medico/Cadastrar/Medico").permitAll();
                    x.requestMatchers(HttpMethod.PATCH,"/Medflix/Medico/Ativar/Medico").permitAll();
                    x.requestMatchers(HttpMethod.POST,"/Medflix/Admin/Login/Admin").permitAll();
                    x.requestMatchers(HttpMethod.POST,"/Medflix/Admin/Cadastrar/Medico").hasAuthority("ADMIN");
                    x.requestMatchers("/error").permitAll();
                    x.anyRequest().authenticated();


                })


                 .addFilterBefore(securityFilterMedico, UsernamePasswordAuthenticationFilter.class)
              .addFilterBefore(securityFilterUsuario, UsernamePasswordAuthenticationFilter.class)
               .addFilterBefore(securityFilterAdmin, UsernamePasswordAuthenticationFilter.class)


             .build();





    }
}
