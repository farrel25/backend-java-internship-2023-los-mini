package com.los.dto.response;

import com.los.entity.Collateral;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CollateralAppraisalResponse {
    private Collateral collateral;
    private String appraiserName;
    private Double appraisalValue;
    private LocalDateTime appraisalDate;
}
