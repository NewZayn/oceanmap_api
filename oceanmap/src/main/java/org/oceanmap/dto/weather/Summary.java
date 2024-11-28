package org.oceanmap.dto.weather;

import lombok.Data;

@Data
public class Summary {
    private String starDate;
    private String endDate;
    private int severity;
    private String phrase;
    private String category;
}
