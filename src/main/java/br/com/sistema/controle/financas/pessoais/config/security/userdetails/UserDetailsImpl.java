package br.com.sistema.controle.financas.pessoais.config.security.userdetails;

import br.com.sistema.controle.financas.pessoais.domain.entity.usuario.UsuarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;


public class UserDetailsImpl implements UserDetails {

    @Autowired
    private UsuarioEntity login;

    public UserDetailsImpl(UsuarioEntity login){
        this.login = login;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return login.getNomeRole()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getNomeRole().name()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return login.getSenhaUsuario();
    }

    @Override
    public String getUsername() {
        return login.getEmailUsuario();
    }

    /**
     * Indica se a conta do usuário está expirada.
     * @return true, se a conta não estiver expirada; false, caso contrário.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Indica se a conta do usuário está bloqueada.
     * @return true, se a conta não estiver bloqueada; false, caso contrário.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Indica se as credenciais do usuário estão expiradas.
     * @return true, se as credenciais não estiverem expiradas; false, caso contrário.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Indica se a conta do usuário está habilitada.
     * @return true, se a conta estiver habilitada; false, caso contrário.
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
