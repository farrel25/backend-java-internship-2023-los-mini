package com.los.mapper;

import com.los.dto.request.CreditRequest;
import com.los.dto.response.CreditResponse;
import com.los.entity.Credit;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Mapper(componentModel = "spring")
public interface CreditMapper {
    CreditResponse toCreditResponse(Credit credit);
    List<CreditResponse> toCreditResponses(List<Credit> credits);
    Credit toCredit (CreditRequest request);
    void updateCredit(CreditRequest request, @MappingTarget Credit credit);
}
