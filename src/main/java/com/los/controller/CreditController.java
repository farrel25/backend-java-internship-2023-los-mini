package com.los.controller;


import com.los.dto.request.CreditRequest;
import com.los.dto.response.CommonResponse;
import com.los.dto.response.CreditResponse;
import com.los.service.CreditService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/credit")
@RequiredArgsConstructor
public class CreditController {
    private final CreditService creditService;

    @PostMapping
    public ResponseEntity<CommonResponse> createCredit(@Valid @RequestBody CreditRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(creditService.createCredit(request));
    }

    @GetMapping
    public ResponseEntity<List<CreditResponse>> getAllCredit(){
        return ResponseEntity.ok(creditService.getAllCredit());
    }

    @GetMapping(path = "/{creditId}")
    public ResponseEntity<CreditResponse> getCreditById(@PathVariable(name = "creditId") Long id){
        return ResponseEntity.ok(creditService.getByIdCredit(id));
    }

    @PutMapping(path = "/{creditId}")
    public ResponseEntity<CommonResponse> updateCredit(@PathVariable(name = "creditId") Long id,@Valid @RequestBody CreditRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(creditService.updateCredit(id, request));
    }

    @DeleteMapping(path = "/{creditId}")
    public ResponseEntity<CommonResponse> deleteCredit(@PathVariable(name = "creditId") Long id){
        creditService.deleteCredit(id);
        return ResponseEntity.ok(new CommonResponse());
    }
}
