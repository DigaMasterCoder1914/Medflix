package TestMedflix.diga.dev.TestMedflix.Usuario_Usuario.ConfigurationsUsuario;
import TestMedflix.diga.dev.TestMedflix.Usuario_Usuario.UserDetails.UserDetailServiceUsuario;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecuriyUsuario {


    @Bean
    public PasswordEncoder passwordEncoderUsuario() {


        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManagerUsuario(UserDetailServiceUsuario serviceDetailUsuario,
                                                              PasswordEncoder passwordEncoderUsuario) {

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(serviceDetailUsuario);

        provider.setPasswordEncoder(passwordEncoderUsuario);

        return  new ProviderManager(provider);

    }


}
