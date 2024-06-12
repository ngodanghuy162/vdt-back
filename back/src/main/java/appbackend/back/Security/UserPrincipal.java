package appbackend.back.Security;

import appbackend.back.model.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserPrincipal implements UserDetails {

    private Account acc;

    public UserPrincipal() {
    }

    public UserPrincipal(Account acc) {
        super();
        this.acc = acc;
    }


    public Account getAcc() {
        return acc;
    }

    public void setAcc(Account acc) {
        this.acc = acc;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (acc != null) {
            return List.of(new SimpleGrantedAuthority(acc.getRole()));
        }
        return null;
    }

    @Override
    public String getPassword() {
        if(acc != null) {
            return acc.getPassword();
        }
        return null;
    }

    @Override
    public String getUsername() {
        if(acc != null) {
            return acc.getUsername();
        }
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}