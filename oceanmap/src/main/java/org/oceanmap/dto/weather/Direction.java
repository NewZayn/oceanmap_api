package org.oceanmap.dto.weather;

import lombok.Data;

@Data
public class Direction {
    private int degrees;
    private String localizedDescription;
}
