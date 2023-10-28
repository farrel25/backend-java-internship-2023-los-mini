package com.los.entity;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(schema = "public", name = "credit")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SQLDelete(sql = "UPDATE credit SET is_deleted = true WHERE id = ?")
@Where(clause = "is_deleted = false")
public class Credit extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id",nullable = false, referencedColumnName = "id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "master_product_id", nullable = false, referencedColumnName = "id")
    private MasterProduct masterProduct;

    @Column(name = "numeric", nullable = false)
    private Double numeric;

    @Column(name = "installment_period", nullable = false)
    private Integer installmentPeriod;

    @Column(name = "interest", nullable = false)
    private Double interest;

    @Column(name = "account_number", nullable = false)
    private String accountNumber;

    @Column(name = "is_approved", nullable = false, columnDefinition = "boolean default false")
    private Boolean isApproved;

    @Column(name = "is_deleted", nullable = false, columnDefinition = "boolean default false")
    private Boolean isDeleted;
}
