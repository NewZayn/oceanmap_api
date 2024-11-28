package org.oceanmap.dto;


import lombok.Data;
import org.oceanmap.dto.weather.Forecasts;
import org.oceanmap.dto.weather.Summary;

import java.util.ArrayList;

@Data
public class WeatherDTO {
    public Summary summary;
    public ArrayList<Forecasts> forecasts;
}