package com.los.dto.response;

import com.los.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CollateralResponse {
    private Customer customer;
    private String collateralName;
    private String ownerName;
    private String ownerAddress;
    private String legalityNumber;
}
