package com.los.mapper;

import com.los.dto.request.MasterProductRequest;
import com.los.dto.response.MasterProductResponse;
import com.los.entity.MasterProduct;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Mapper(componentModel = "spring")
public interface MasterProductMapper {
    MasterProductResponse toMasterProductResponse (MasterProduct masterProduct);
    List<MasterProductResponse> toMasterProductResponseList (List<MasterProduct> masterProducts);
    MasterProduct toMasterProduct (MasterProductRequest request);
    void updateMasterProduct(MasterProductRequest request, @MappingTarget MasterProduct masterProduct);
}
