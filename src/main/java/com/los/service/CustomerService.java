package com.los.service;

import com.los.dto.request.CustomerRequest;
import com.los.dto.response.CommonResponse;
import com.los.dto.response.CustomerResponse;
import com.los.entity.Customer;
import com.los.entity.MasterMenu;
import com.los.exception.BadRequestException;
import com.los.exception.ResourceNotFoundException;
import com.los.mapper.CustomerMapper;
import com.los.repository.CustomerRepository;
import com.los.repository.MasterMenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Service
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
        return  new CommonResponse(savedCustomer.getId());
    }

    public List<CustomerResponse> getAllCustomer(){
        List<Customer> customers = customerRepository.findAll();
        return customerMapper.toCustomerResponseList(customers);
    }

    public CustomerResponse getByIdCustomer(Long id){
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isEmpty()){
            throw new ResourceNotFoundException("Customer not found with id : " + id);
        }

        Customer customer = optionalCustomer.get();
        return customerMapper.toCustomerResponse(customer);
    }

    public CommonResponse updateCustomer(Long id, CustomerRequest request){
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isEmpty()){
            throw new ResourceNotFoundException("Customer not found with id : " + id);
        }

        Customer customer = optionalCustomer.get();
        customerMapper.updateCustomer(request, customer);

        Customer updatedCustomer = customerRepository.save(customer);
        return new CommonResponse(updatedCustomer.getId());
    }

    public void deleteCustomer(Long id){
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isEmpty()){
            throw new ResourceNotFoundException("Customer not found with id : " + id);
        }

        customerRepository.delete(optionalCustomer.get());
    }
}
