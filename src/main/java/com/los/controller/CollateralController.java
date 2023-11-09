package com.los.controller;



import com.los.dto.request.CollateralRequest;
import com.los.dto.response.CollateralResponse;
import com.los.dto.response.CommonResponse;
import com.los.service.CollateralService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/collateral")
@RequiredArgsConstructor
public class CollateralController {
    private final CollateralService collateralService;

    @PostMapping
    public ResponseEntity<CommonResponse> createCollateral(@Valid @RequestBody CollateralRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(collateralService.createCollateral(request));
    }

    @GetMapping
    public ResponseEntity<List<CollateralResponse>> getAllCollateral(){
        return ResponseEntity.ok(collateralService.getAllCollateral());
    }

    @GetMapping(path = "/{collateralId}")
    public ResponseEntity<CollateralResponse> getCollateralById(@PathVariable(name = "collateralId") Long id){
        return ResponseEntity.ok(collateralService.getByIdCollateral(id));
    }

    @PutMapping(path = "/{collateralId}")
    public ResponseEntity<CommonResponse> updateCollateral(@PathVariable(name = "creditId") Long id,@Valid @RequestBody CollateralRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(collateralService.updateCollateral(id, request));
    }

    @DeleteMapping(path = "/{creditId}")
    public ResponseEntity<CommonResponse> deleteCollateral(@PathVariable(name = "creditId") Long id){
        collateralService.deleteCollateral(id);
        return ResponseEntity.ok(new CommonResponse());
    }
}
