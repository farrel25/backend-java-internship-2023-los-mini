package com.los.dto.request;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class MasterMenuRequest {
    @NotNull
    private String name;

    @NotNull
    private Long flowSequence;

    @NotNull
    private Boolean isActive;
}
