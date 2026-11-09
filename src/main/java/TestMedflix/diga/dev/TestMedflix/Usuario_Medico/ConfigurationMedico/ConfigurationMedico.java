package TestMedflix.diga.dev.TestMedflix.Usuario_Medico.ConfigurationMedico;

import TestMedflix.diga.dev.TestMedflix.Usuario_Admin.UserDetails.ServiceDetailsAdmin;
import TestMedflix.diga.dev.TestMedflix.Usuario_Medico.UserDetails.ServiceDetailMedico;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ConfigurationMedico {

    @Bean
    public PasswordEncoder passwordEncoderMedico() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManagerMedico(ServiceDetailMedico serviceDetailMedico,
                                                             PasswordEncoder passwordEncoderMedico) {

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(serviceDetailMedico);

        provider.setPasswordEncoder(passwordEncoderMedico);

        return  new ProviderManager(provider);

    }


}