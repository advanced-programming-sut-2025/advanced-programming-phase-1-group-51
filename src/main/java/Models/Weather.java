package Models;

public class Weather {

    private Models.Enums.Others.Weather weatherType;


    public Weather(Models.Enums.Others.Weather weatherType) {
        this.weatherType = weatherType;
    }

    public Models.Enums.Others.Weather getWeatherType() {
        return weatherType;
    }

    public void setWeatherType(Models.Enums.Others.Weather weatherType) {
        this.weatherType = weatherType;
    }

    public void thor(){

    }
}
