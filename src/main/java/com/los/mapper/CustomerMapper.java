package com.los.mapper;

import com.los.dto.request.CustomerRequest;
import com.los.dto.response.CustomerResponse;
import com.los.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerResponse toCustomerResponse(Customer customer);
    List<CustomerResponse> toCustomerResponseList(List<Customer> customers);
    Customer toCustomer (CustomerRequest request);
    void updateCustomer(CustomerRequest request, @MappingTarget Customer customer);
}
