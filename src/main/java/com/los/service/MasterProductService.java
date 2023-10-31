package com.los.service;

import com.los.dto.request.MasterProductRequest;
import com.los.dto.response.CommonResponse;
import com.los.dto.response.MasterProductResponse;
import com.los.entity.MasterProduct;
import com.los.exception.ResourceNotFoundException;
import com.los.mapper.MasterProductMapper;
import com.los.repository.MasterProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MasterProductService {

    private final MasterProductRepository masterProductRepository;
    private final MasterProductMapper masterProductMapper;

    public CommonResponse createMasterProduct(MasterProductRequest request) {
        MasterProduct masterProduct = masterProductMapper.toMasterProduct(request);
        masterProduct.setIsDeleted(Boolean.valueOf("false"));

        MasterProduct savedMasterMenu = masterProductRepository.save(masterProduct);
        return new CommonResponse(savedMasterMenu.getId());
    }

    public MasterProductResponse getMasterProductById(Long id){
        Optional<MasterProduct> optionalMasterProduct = masterProductRepository.findById(id);

        if (optionalMasterProduct.isEmpty()){
            throw new ResourceNotFoundException("Master product not found with id : " + id);
        }

        MasterProduct masterProduct = optionalMasterProduct.get();
        return masterProductMapper.toMasterProductResponse(masterProduct);
    }

    public List<MasterProductResponse> getAllMasterProduct(){
        List<MasterProduct> masterProducts = masterProductRepository.findAll();
        return masterProductMapper.toMasterProductResponseList(masterProducts);
    }

    public CommonResponse updateMasterProduct(Long id, MasterProductRequest request){
        Optional<MasterProduct> optionalMasterProduct = masterProductRepository.findById(id);

        if (optionalMasterProduct.isEmpty()){
            throw new ResourceNotFoundException("Master product not found with id : " + id);
        }

        MasterProduct masterProduct = optionalMasterProduct.get();
        masterProductMapper.updateMasterProduct(request, masterProduct);

        MasterProduct updatedMasterProduct = masterProductRepository.save(optionalMasterProduct.get());
        return new CommonResponse(updatedMasterProduct.getId());
    }

    public void deleteMasterProduct(Long id){
        Optional<MasterProduct> optionalMasterProduct = masterProductRepository.findById(id);

        if (optionalMasterProduct.isEmpty()){
            throw new ResourceNotFoundException("Master product not found with id : " + id);
        }

        masterProductRepository.delete(optionalMasterProduct.get());
    }
}

