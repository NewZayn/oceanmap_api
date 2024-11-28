package org.oceanmap.dto.weather;

import lombok.Data;

@Data
public class AirAndPollen{
    private String name;
    private int value;
    private String category;
    private int categoryValue;
    private String type;
}


