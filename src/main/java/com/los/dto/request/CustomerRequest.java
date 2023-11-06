package com.los.dto.request;

import com.los.constant.CustomerType;
import com.los.constant.FlowStatus;
import com.los.entity.MasterMenu;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CustomerRequest {
    @NotNull
    private String fullName;

    @NotNull
    private CustomerType customerType;

    @NotNull
    private String identityNumber;

    @NotNull
    private String phoneNumber;

    @NotNull
    private String address;

    @NotNull
    private Long masterMenuId;

    @NotNull
    private FlowStatus flowStatus;

    @NotNull
    private Long lockedById;
}
