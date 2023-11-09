package com.los.controller;


import com.los.dto.request.CollateralAppraisalRequest;
import com.los.dto.response.CollateralAppraisalResponse;
import com.los.dto.response.CommonResponse;
import com.los.service.CollateralAppraisalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/collateralAppraisal")
@RequiredArgsConstructor
public class CollateralAppraisalController {
    private final CollateralAppraisalService collateralAppraisalService;

    @PostMapping
    public ResponseEntity<CommonResponse> createCollateralAppraisal(@Valid @RequestBody CollateralAppraisalRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(collateralAppraisalService.createCollateralAppraisal(request));
    }

    @GetMapping
    public ResponseEntity<List<CollateralAppraisalResponse>> getAllCollateralAppraisal(){
        return ResponseEntity.ok(collateralAppraisalService.getAllCollateralAppraisal());
    }

    @GetMapping(path = "/{collateralAppId}")
    public ResponseEntity<CollateralAppraisalResponse> getCollateralAppraisalById(@PathVariable(name = "collateralAppId") Long id){
        return ResponseEntity.ok(collateralAppraisalService.getByIdCollateralAppraisal(id));
    }

    @PutMapping(path = "/{collateralAppId}")
    public ResponseEntity<CommonResponse> updateCollateralAppraisal(@PathVariable(name = "collateralAppId") Long id,@Valid @RequestBody CollateralAppraisalRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(collateralAppraisalService.updateCollateralAppraisal(id, request));
    }

    @DeleteMapping(path = "/{collateralAppId}")
    public ResponseEntity<CommonResponse> deleteCollateralAppraisal(@PathVariable(name = "collateralAppId") Long id){
        collateralAppraisalService.deleteCollateralAppraisal(id);
        return ResponseEntity.ok(new CommonResponse());
    }
}
