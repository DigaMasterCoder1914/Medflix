package TestMedflix.diga.dev.TestMedflix.Usuario_Admin.Security;

import TestMedflix.diga.dev.TestMedflix.Usuario_Admin.UserDetails.ServiceDetailsAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ConfigurationAdmin {

    @Bean
    public PasswordEncoder passwordEncoderAdmin() {
        return new BCryptPasswordEncoder();
    }

    @Primary
    @Bean
    AuthenticationManager authenticationManagerAdmin(ServiceDetailsAdmin  serviceDetailsAdmin) {

        DaoAuthenticationProvider dependencias = new DaoAuthenticationProvider(serviceDetailsAdmin);
        dependencias.setPasswordEncoder(passwordEncoderAdmin());

        return new ProviderManager(dependencias);


    }

}
