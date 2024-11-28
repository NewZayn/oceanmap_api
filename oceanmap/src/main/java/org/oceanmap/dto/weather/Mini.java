package org.oceanmap.dto.weather;

import lombok.Data;

@Data
public class Mini {
    private double value;
    private String unit;
    private int unitType;
}