package com.los.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(schema = "public", name = "collateral_appraisal")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SQLDelete(sql = "UPDATE collateral_appraisal SET is_deleted = true WHERE id = ?")
@Where(clause = "is_deleted = false")
public class CollateralAppraisal extends BaseEntity implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "collateral_id", nullable = false, referencedColumnName = "id")
    private Collateral collateral;

    @Column(name = "appraiser_name", nullable = false)
    private String appraiserName;

    @Column(name = "appraisal_value", nullable = false)
    private Double appraisalValue;

    // @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "appraisal_date", nullable = false)
    private LocalDateTime appraisalDate;

    @Column(name = "is_deleted", nullable = false, columnDefinition = "boolean default false")
    private Boolean isDeleted;
}
