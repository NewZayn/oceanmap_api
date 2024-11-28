package org.oceanmap.dto.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Speed {
    private double value;
    private String unit;
}
