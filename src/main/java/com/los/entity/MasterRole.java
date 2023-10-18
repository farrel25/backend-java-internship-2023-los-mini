package com.los.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.io.Serializable;

@Entity
@Table(schema = "public", name = "master_role")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SQLDelete(sql = "UPDATE master_role SET is_deleted = true WHERE id = ?")
@Where(clause = "is_deleted = false")
public class MasterRole extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "role_code", unique = true, nullable = false)
    private String roleCode;

    @Column(name = "role_name", unique = true, nullable = false)
    private String roleName;

    @Column(name = "is_deleted", nullable = false, columnDefinition = "boolean default false")
    private Boolean isDeleted;
}
