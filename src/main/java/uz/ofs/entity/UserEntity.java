package uz.ofs.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.ofs.constants.Role;
import uz.ofs.constants.TableNames;
import uz.ofs.entity.base.BaseEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = TableNames.OFS_USER)
public class UserEntity extends BaseEntity implements UserDetails , Serializable {

    private String firstname;

    private String lastname;

    @Column(unique = true, nullable = false)
    private String username;

    private String password;


    @Enumerated(EnumType.STRING)
    private List<Role> roleList;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> roles = new ArrayList<>();
        roleList.forEach((rol) -> {
            roles.add(new SimpleGrantedAuthority("ROLE_" + rol.name()));
        });

        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
