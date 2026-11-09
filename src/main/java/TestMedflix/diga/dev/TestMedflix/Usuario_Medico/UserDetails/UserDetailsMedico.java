package TestMedflix.diga.dev.TestMedflix.Usuario_Medico.UserDetails;

import TestMedflix.diga.dev.TestMedflix.Usuario_Medico.MOdels.ModelMedico;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserDetailsMedico implements UserDetails {

    ModelMedico medico;

    public UserDetailsMedico( ModelMedico medico) {

        this.medico = medico;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(medico.getRole()));
    }

    @Override
    public @Nullable String getPassword() {
        return medico.getSenha();
    }

    @Override
    public String getUsername() {
        return medico.getNome();
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return medico.isStatus();
    }
}
