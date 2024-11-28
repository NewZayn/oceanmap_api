package org.oceanmap.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.oceanmap.dto.geocoding.Feature;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeocodingDTO {

        private String type;
        private List<Feature> features;
}