package com.los.controller;

import com.los.dto.request.MasterProductRequest;
import com.los.dto.response.CommonResponse;
import com.los.dto.response.MasterProductResponse;
import com.los.service.MasterProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/master-product")
@RequiredArgsConstructor
public class MasterProductController {
    private final MasterProductService masterProductService;

    @PostMapping
    public ResponseEntity<CommonResponse> createMasterProduct(@RequestBody MasterProductRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(masterProductService.createMasterProduct(request));
    }

    @GetMapping(path = "/{productId}")
    public ResponseEntity<MasterProductResponse> getMasterProductById(@PathVariable(name = "productId") Long id){
        return ResponseEntity.ok(masterProductService.getMasterProductById(id));
    }

    @GetMapping
    public ResponseEntity<List<MasterProductResponse>> getAllMasterProduct(){
        return ResponseEntity.ok(masterProductService.getAllMasterProduct());
    }

    @PutMapping(path = "/{productId}")
    public ResponseEntity<CommonResponse> updateMasterProduct(@PathVariable(name = "productId") Long id,@RequestBody MasterProductRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(masterProductService.updateMasterProduct(id, request));
    }

    @DeleteMapping(path = "/{productId}")
    public ResponseEntity<CommonResponse> deleteMasterProduct(@PathVariable(name = "productId") Long id){
        masterProductService.deleteMasterProduct(id);
        return ResponseEntity.ok(new CommonResponse());
    }
}
