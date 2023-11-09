package com.los.dto.request;

import com.los.entity.Customer;
import com.los.entity.MasterProduct;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CreditRequest {
    @NotNull
    private Customer customer;

    @NotNull
    private MasterProduct masterProduct;

    @NotNull
    private Double numeric;

    @NotNull
    private Integer installmentPeriod;

    @NotNull
    private Double interest;

    @NotNull
    private String accountNumber;

    @NotNull
    private Boolean isApproved;
}
