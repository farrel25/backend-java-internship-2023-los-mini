package com.los.dto.response;

import com.los.constant.CustomerType;
import com.los.constant.FlowStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CustomerResponse {
    private String fullName;
    private CustomerType customerType;
    private String identityNumber;
    private String phoneNumber;
    private String address;
    private Long masterMenuId;
    private FlowStatus flowStatus;
    private Long lockedBy;
}
