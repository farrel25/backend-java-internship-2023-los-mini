package com.los.dto.request;

import com.los.validation.UniqueFlowSequence;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class MasterMenuRequest {
    @NotNull
    private String name;

    @NotNull
    @UniqueFlowSequence
    private Long flowSequence;

    @NotNull
    private Boolean isActive;
}
