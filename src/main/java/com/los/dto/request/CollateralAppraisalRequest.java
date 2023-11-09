package com.los.dto.request;

import com.los.entity.Collateral;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CollateralAppraisalRequest {
    @NotNull
    private Collateral collateral;

    @NotNull
    private String appraiserName;

    @NotNull
    private Double appraisalValue;

    @NotNull
    private LocalDateTime appraisalDate;
}
