package com.los.controller;

import com.los.dto.response.CustomerFilterResponse;
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
    public ResponseEntity<CommonResponse> createCustomer(@RequestBody @Valid CustomerRequest request) {
        CommonResponse response = customerService.createCustomer(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getAllCustomers() {
        List<CustomerResponse> customers = customerService.getAllCustomer();
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable(name = "customerId") Long id) {
        CustomerResponse customer = customerService.getCustomerById(id);
        return ResponseEntity.ok(customer);
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<CommonResponse> updateCustomer(
            @PathVariable(name = "customerId") Long id, @RequestBody @Valid CustomerRequest request) {
        CommonResponse response = customerService.updateCustomer(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable(name = "customerId") Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/filter")
    public ResponseEntity<List<CustomerResponse>> filterCustomers(CustomerFilterResponse filterResponse) {
        List<CustomerResponse> filteredCustomers = customerService.filterCustomers(filterResponse);
        return ResponseEntity.ok(filteredCustomers);
    }
}
