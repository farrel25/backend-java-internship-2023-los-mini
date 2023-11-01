package com.los.dto.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class MasterMenuResponse {
    private Long id;
    private String name;
    private Long flowSequence;
    private Boolean isActive;
}
