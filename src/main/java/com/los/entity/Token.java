package com.los.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(schema = "public", name = "token")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Token extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "token", nullable = false, unique = true)
    private String token;

    @Column(name = "is_revoked", nullable = false, columnDefinition = "boolean default false")
    private Boolean isRevoked;



    //==== RELATIONS ====//

    @ManyToOne(targetEntity = MasterUser.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "master_user_id", referencedColumnName = "id", nullable = false)
    private MasterUser masterUser;

    //==== RELATIONS ====//
}
