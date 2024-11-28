package org.oceanmap.dto.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Forecasts {
    private Date date;
    private Temperature temperature;
    private double hoursOfSun;
    private ArrayList<AirAndPollen> airAndPollen;
    private Day day;
}




