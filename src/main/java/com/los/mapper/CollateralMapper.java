package com.los.mapper;

import com.los.dto.request.CollateralRequest;
import com.los.dto.response.CollateralResponse;
import com.los.entity.Collateral;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Mapper(componentModel = "spring")
public interface CollateralMapper {
    CollateralResponse toCollateralResponse(Collateral collateral);
    List<CollateralResponse> toCollateralResponses(List<Collateral> collaterals);
    Collateral toCollateral (CollateralRequest request);
    void updateCollateral(CollateralRequest request, @MappingTarget Collateral collateral);

}
