
package TestMedflix.diga.dev.TestMedflix.Usuario_Admin.UserDetails;

import TestMedflix.diga.dev.TestMedflix.Usuario_Admin.MV.Models.ModelAdmin;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserDetailAdmin implements UserDetails {

    ModelAdmin  modelAdmin;

    public UserDetailAdmin( ModelAdmin  modelAdmin) {

        this.modelAdmin = modelAdmin;

    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(modelAdmin.getRole()));
    }

    @Override
    public @Nullable String getPassword() {
        return modelAdmin.getSenha();
    }

    @Override
    public String getUsername() {
        return modelAdmin.getNome();
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
        return UserDetails.super.isEnabled();
    }
}




