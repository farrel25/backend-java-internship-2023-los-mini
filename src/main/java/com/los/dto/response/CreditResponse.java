package com.los.dto.response;

import com.los.entity.Customer;
import com.los.entity.MasterProduct;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CreditResponse {
    private Customer customer;
    private MasterProduct masterProduct;
    private Double numeric;
    private Integer installmentPeriod;
    private Double interest;
    private String accountNumber;
    private Boolean isApproved;
}
