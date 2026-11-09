package TestMedflix.diga.dev.TestMedflix.Usuario_Usuario.UserDetails;

import TestMedflix.diga.dev.TestMedflix.Usuario_Usuario.Models.ModelUsuario;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserDetailsUsuario implements UserDetails {

    private final ModelUsuario modelUsuario;
    public UserDetailsUsuario(ModelUsuario modelUsuario) {

        this.modelUsuario = modelUsuario;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(modelUsuario.getRole()));
    }

    @Override
    public @Nullable String getPassword() {
        return modelUsuario.getSenha();
    }

    @Override
    public String getUsername() {
        return modelUsuario.getEmail();
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
        return modelUsuario.isStatus();
    }
}
