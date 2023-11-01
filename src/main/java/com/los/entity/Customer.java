package com.los.entity;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import com.los.constant.CustomerType;
import com.los.constant.FlowStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(schema = "public", name = "customer")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SQLDelete(sql = "UPDATE customer SET is_deleted = true WHERE id = ?")
@Where(clause = "is_deleted = false")
public class Customer extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "customer_type_id", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private CustomerType customerTypeId;

    @Column(name = "identity_number", nullable = false)
    private String identityNumber;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "address", nullable = false)
    private String address;

    @ManyToOne
    @JoinColumn(name = "master_menu_id", nullable = false, referencedColumnName = "id")
    private MasterMenu masterMenu;

    @Column(name = "flow_status", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private FlowStatus flowStatus;

    @ManyToOne
    @JoinColumn(name = "locked_by")
    private MasterUser lockedBy;

    @Column(name = "is_deleted", nullable = false, columnDefinition = "boolean default false")
    private Boolean isDeleted;
}
