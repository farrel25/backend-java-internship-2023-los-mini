package com.los.dto.response;

import com.los.constant.CustomerType;
import com.los.constant.FlowStatus;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CustomerFilterResponse {
    private Long masterMenuId;
    private CustomerType customerType;
    private FlowStatus flowStatus;
    private Boolean isShowRejected;
}
