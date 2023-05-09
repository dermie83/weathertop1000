package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class Station extends Model
{
    public String name;
    public float lat;
    public float lng;

    @OneToMany(cascade = CascadeType.ALL)
    public List<Reading> readings = new ArrayList<>();

    public Station(String name, float lat, float lng)
    {
        this.name = name;
        this.lat = lat;
        this.lng = lng;
    }

    public String getLatestTimestamp() {
                String timestamp = readings.get(readings.size() - 1).timeStamp;
             return timestamp;
    }

    public int getLatestWeatherCode() {
                int code = readings.get(readings.size() - 1).code;
            return code;
    }

    public double getLatestTemp() {
                double temp = readings.get(readings.size() - 1).temperature;
            return temp;
    }

    public double getLatestWind() {
                double wind = readings.get(readings.size() - 1).windSpeed;
            return wind;

    }

    public double getLatestWindDirection() {
        double windDirection = readings.get(readings.size() - 1).windDirection;
        return windDirection;

    }

    public int getLatestPressure() {
        int pressure = readings.get(readings.size() - 1).pressure;
            return pressure;
    }

    public String getLatestWeatherCodeToText() {
            int codeNumber = getLatestWeatherCode();
            String codeToText = Conversions.convertWeatherCodeToText(codeNumber);
            return codeToText;
    }

    public double getLatestConvertTemp() {
        double temp = getLatestTemp();
        double tempToText = Conversions.convertTemp(temp);
        return tempToText;
    }

    public double getLatestWindChill() {
        double latestTemp = getLatestTemp();
        double latestWindSpeed = getLatestWind();
        double windChillTemp = Conversions.calculateWindChill(latestTemp, latestWindSpeed);
        return windChillTemp;
    }


    public int getLatestWindToBeaufort() {
        double wind = getLatestWind();
        double windToBeaufort = Conversions.convertWindSpeedToBeaufortIndex(wind);
        return (int) windToBeaufort;
    }

    public String getLatestWindDirectionToText() {
        double windDirection = getLatestWindDirection();
        String windDirectionToText = Conversions.convertWindDirectionToText(windDirection);
        return windDirectionToText;
    }

    public String getLatestBeaufortToText() {
        int bft = getLatestWindToBeaufort();
        String bftToText = Conversions.convertBFTCodeToText(bft);
        return bftToText;
    }





}