package com.los.dto.request;

import com.los.entity.Customer;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CollateralRequest {
    @NotNull
    private Customer customer;

    @NotNull
    private String collateralName;

    @NotNull
    private String ownerName;

    @NotNull
    private String ownerAddress;

    @NotNull
    private String legalityNumber;
}
