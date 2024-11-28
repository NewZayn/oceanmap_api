package org.oceanmap.dto.geocoding;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Feature {
    private Geometry geometry;
    private Properties properties;
}