package com.team_ingterior.ingterior.DTO.geometry;

import java.util.Map;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FeatureVO {
    private String type;
    private Map<String, Object> geometry;
    private Map<String, Object> properties;
}
