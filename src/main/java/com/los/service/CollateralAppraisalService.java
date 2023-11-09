package com.los.service;



import com.los.dto.request.CollateralAppraisalRequest;
import com.los.dto.response.CollateralAppraisalResponse;
import com.los.dto.response.CommonResponse;
import com.los.entity.CollateralAppraisal;
import com.los.exception.ResourceNotFoundException;
import com.los.mapper.CollateralAppraisalMapper;
import com.los.repository.CollateralAppraisalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Service
@Validated
@RequiredArgsConstructor
public class CollateralAppraisalService {
    private final CollateralAppraisalRepository collateralAppraisalRepository;
    private final CollateralAppraisalMapper collateralAppraisalMapper;

    public CommonResponse createCollateralAppraisal(CollateralAppraisalRequest request){
        CollateralAppraisal collateralAppraisal = collateralAppraisalMapper.toCollateralAppraisal(request);
        collateralAppraisal.setIsDeleted(false);

        CollateralAppraisal savedCredit = collateralAppraisalRepository.save(collateralAppraisal);
        return  new CommonResponse(savedCredit.getId());
    }

    public List<CollateralAppraisalResponse> getAllCollateralAppraisal(){
        List<CollateralAppraisal> collateralAppraisals = collateralAppraisalRepository.findAll();
        return collateralAppraisalMapper.toCollateralAppraisalResponses(collateralAppraisals);
    }

    public CollateralAppraisalResponse getByIdCollateralAppraisal(Long id){
        Optional<CollateralAppraisal> optionalCollateralAppraisal = collateralAppraisalRepository.findById(id);
        if (optionalCollateralAppraisal.isEmpty()){
            throw new ResourceNotFoundException("Collateral appraisal not found with id : " + id);
        }

        CollateralAppraisal collateralAppraisal = optionalCollateralAppraisal.get();
        return collateralAppraisalMapper.toCollateralAppraisalResponse(collateralAppraisal);
    }

    public CommonResponse updateCollateralAppraisal(Long id, CollateralAppraisalRequest request){
        Optional<CollateralAppraisal> optionalCollateralAppraisal = collateralAppraisalRepository.findById(id);
        if (optionalCollateralAppraisal.isEmpty()){
            throw new ResourceNotFoundException("Collateral appraisal not found with id : " + id);
        }

        CollateralAppraisal collateralAppraisal = optionalCollateralAppraisal.get();
        collateralAppraisalMapper.updateCollateralAppraisal(request, collateralAppraisal);

        CollateralAppraisal updatedCollateralAppraisal = collateralAppraisalRepository.save(collateralAppraisal);
        return new CommonResponse(updatedCollateralAppraisal.getId());
    }

    public void deleteCollateralAppraisal(Long id){
        Optional<CollateralAppraisal> optionalCollateralAppraisal = collateralAppraisalRepository.findById(id);
        if (optionalCollateralAppraisal.isEmpty()){
            throw new ResourceNotFoundException("Collateral appraisal not found with id : " + id);
        }

        collateralAppraisalRepository.delete(optionalCollateralAppraisal.get());
    }
}
