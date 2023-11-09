package com.los.controller;

import com.los.dto.request.CustomerRequest;
import com.los.dto.response.CommonResponse;
import com.los.dto.response.CustomerResponse;
import com.los.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CommonResponse> createCustomer(@Valid @RequestBody CustomerRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.createCustomer(request));
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getAllCustomer(){
        return ResponseEntity.ok(customerService.getAllCustomer());
    }

    @GetMapping(path = "/{customerId}")
    public ResponseEntity<CustomerResponse> getByIdCustomer(@PathVariable(name = "customerId") Long id){
        return ResponseEntity.ok(customerService.getByIdCustomer(id));
    }

    @PutMapping(path = "/{customerId}")
    public ResponseEntity<CommonResponse> updateCustomer(@PathVariable(name = "customerId") Long id, @Valid @RequestBody CustomerRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.updateCustomer(id, request));
    }

    public ResponseEntity<CommonResponse> deleteCustomer(@PathVariable(name = "customerId") Long id){
        customerService.deleteCustomer(id);
        return ResponseEntity.ok(new CommonResponse());
    }
}
