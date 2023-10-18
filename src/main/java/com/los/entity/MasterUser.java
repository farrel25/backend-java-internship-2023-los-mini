package com.los.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@Entity
@Table(schema = "public", name = "master_user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SQLDelete(sql = "UPDATE master_user SET is_deleted = true WHERE id = ?")
@Where(clause = "is_deleted = false")
public class MasterUser extends BaseEntity implements Serializable, UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "username", unique = true, nullable = false)
	private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "is_deleted", nullable = false, columnDefinition = "boolean default false")
    private Boolean isDeleted;



    //=== RELATION ====//

    @ManyToOne(targetEntity = MasterRole.class)
    @JoinColumn(name = "master_role_id", referencedColumnName = "id", nullable = false)
    private MasterRole masterRole;

    @OneToMany(mappedBy = "masterUser")
    private Set<Token> tokens;

    //=== RELATION ====//



    //=== GETTER & SETTER FROM USER DETAILS ====//

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(
                new SimpleGrantedAuthority("ROLE_" + this.masterRole.getRoleCode())
        );
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

    //=== GETTER & SETTER FROM USER DETAILS ====//
}
