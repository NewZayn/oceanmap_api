package org.oceanmap.dto.geocoding;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Properties {
    private String type;
    private String confidence;
    private Address address;

}
