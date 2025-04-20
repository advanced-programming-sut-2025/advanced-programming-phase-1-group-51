package Models;

import Models.Enums.Types.WeatherType;

public class Weather {

    private WeatherType weatherType;


    public Weather(WeatherType weatherType) {
        this.weatherType = weatherType;
    }

    public WeatherType getWeatherType() {
        return weatherType;
    }

    public void setWeatherType(WeatherType weatherType) {
        this.weatherType = weatherType;
    }

    public void thor(){

    }
}
