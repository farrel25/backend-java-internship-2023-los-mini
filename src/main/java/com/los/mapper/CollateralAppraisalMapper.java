package com.los.mapper;

import com.los.dto.request.CollateralAppraisalRequest;
import com.los.dto.response.CollateralAppraisalResponse;
import com.los.entity.CollateralAppraisal;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Mapper(componentModel = "spring")
public interface CollateralAppraisalMapper {
    CollateralAppraisalResponse toCollateralAppraisalResponse (CollateralAppraisal collateralAppraisal);
    List<CollateralAppraisalResponse> toCollateralAppraisalResponses(List<CollateralAppraisal> collateralAppraisals);
    CollateralAppraisal toCollateralAppraisal (CollateralAppraisalRequest request);
    void updateCollateralAppraisal(CollateralAppraisalRequest request, @MappingTarget CollateralAppraisal collateralAppraisal);
}
