package com.los.service;



import com.los.dto.request.CollateralRequest;
import com.los.dto.response.CollateralResponse;
import com.los.dto.response.CommonResponse;
import com.los.entity.Collateral;
import com.los.exception.ResourceNotFoundException;
import com.los.mapper.CollateralMapper;
import com.los.repository.CollateralRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Service
@Validated
@RequiredArgsConstructor
public class CollateralService {
    private final CollateralRepository collateralRepository;
    private final CollateralMapper collateralMapper;

    public CommonResponse createCollateral(CollateralRequest request){
        Collateral collateral = collateralMapper.toCollateral(request);
        collateral.setIsDeleted(false);

        Collateral savedCredit = collateralRepository.save(collateral);
        return  new CommonResponse(savedCredit.getId());
    }

    public List<CollateralResponse> getAllCollateral(){
        List<Collateral> collaterals = collateralRepository.findAll();
        return collateralMapper.toCollateralResponses(collaterals);
    }

    public CollateralResponse getByIdCollateral(Long id){
        Optional<Collateral> optionalCollateral = collateralRepository.findById(id);
        if (optionalCollateral.isEmpty()){
            throw new ResourceNotFoundException("Collateral not found with id : " + id);
        }

        Collateral credit = optionalCollateral.get();
        return collateralMapper.toCollateralResponse(credit);
    }

    public CommonResponse updateCollateral(Long id, CollateralRequest request){
        Optional<Collateral> optionalCollateral = collateralRepository.findById(id);
        if (optionalCollateral.isEmpty()){
            throw new ResourceNotFoundException("Collateral not found with id : " + id);
        }

        Collateral collateral = optionalCollateral.get();
        collateralMapper.updateCollateral(request, collateral);

        Collateral updatedCollateral = collateralRepository.save(collateral);
        return new CommonResponse(updatedCollateral.getId());
    }

    public void deleteCollateral(Long id){
        Optional<Collateral> optionalCredit = collateralRepository.findById(id);
        if (optionalCredit.isEmpty()){
            throw new ResourceNotFoundException("Collateral not found with id : " + id);
        }

        collateralRepository.delete(optionalCredit.get());
    }
}
