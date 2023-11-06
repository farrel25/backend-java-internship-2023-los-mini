package com.los.service;

import com.los.dto.response.CustomerFilterResponse;
import com.los.dto.request.CustomerRequest;
import com.los.dto.response.CommonResponse;
import com.los.dto.response.CustomerResponse;
import com.los.entity.Customer;
import com.los.entity.MasterMenu;
import com.los.exception.ResourceNotFoundException;
import com.los.mapper.CustomerMapper;
import com.los.repository.CustomerRepository;
import com.los.repository.MasterMenuRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Validated
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final MasterMenuRepository masterMenuRepository;

    public CommonResponse createCustomer(CustomerRequest request){
        Customer customer = customerMapper.toCustomer(request);
        Long masterMenuId = request.getMasterMenuId();
        MasterMenu masterMenu = masterMenuRepository.findById(masterMenuId)
                .orElseThrow(() -> new ResourceNotFoundException("Master menu not found with ID: " + masterMenuId));
        if (request.getCustomerType() != null){
            customer.setCustomerType(request.getCustomerType());
        }
        customer.setMasterMenuId(masterMenu);
        customer.setIsDeleted(false);

        Customer savedCustomer = customerRepository.save(customer);
        return new CommonResponse(savedCustomer.getId());
    }

    public List<CustomerResponse> getAllCustomer(){
        List<Customer> customers = customerRepository.findAll();
        log.error("An error occurred while fetching customers.");
        return customerMapper.toCustomerResponseList(customers);
    }

    public CustomerResponse getCustomerById (Long id){
            log.info("Received a request to fetch customer with ID: {}", id);
            Optional<Customer> optionalCustomer = customerRepository.findById(id);
            if (optionalCustomer.isEmpty()){
                log.info("Customer not found with ID: {}", id);
                throw new ResourceNotFoundException("Customer not found with ID: " + id);
            }
            Customer customer = optionalCustomer.get();
            log.info("Fetched customer with ID: {}", id);
            return customerMapper.toCustomerResponse(customer);
    }

    public CommonResponse updateCustomer(Long id, CustomerRequest request){
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isEmpty()){
            throw new ResourceNotFoundException("aster product not found with id : " + id);
        }

        Customer customer = optionalCustomer.get();
        customerMapper.updateCustomer(request, customer);

        Customer updatedCustomer = customerRepository.save(customer);
        return new CommonResponse(updatedCustomer.getId());
    }

    public void deleteCustomer(Long id){
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isEmpty()){
            throw new ResourceNotFoundException("aster product not found with id : " + id);
        }

        customerRepository.delete(optionalCustomer.get());
    }

    public List<CustomerResponse> filterCustomers(CustomerFilterResponse filterRequest) {
        Specification<Customer> spec = Specification.where(null);

        if (filterRequest.getCustomerType() != null) {
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("customerType"), filterRequest.getCustomerType())
            );
        }

        if (filterRequest.getMasterMenuId() != null) {
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("masterMenu").get("id"), filterRequest.getMasterMenuId())
            );
        }

        if (filterRequest.getFlowStatus() != null) {
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("flowStatus"), filterRequest.getFlowStatus())
            );
        }

        if (filterRequest.getIsShowRejected()) {
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.isTrue(root.get("isRejected"))
            );
        } else {
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.isFalse(root.get("isRejected"))
            );
        }

        List<Customer> filteredCustomers = customerRepository.findAll(spec);
        return customerMapper.toCustomerResponseList(filteredCustomers);
    }
}
