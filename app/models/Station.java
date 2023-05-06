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

    @OneToMany(cascade = CascadeType.ALL)
    public List<Reading> readings = new ArrayList<>();

    public Station(String name)
    {
        this.name = name;
    }

    public String getLatestTimestamp() {
                String timestamp = readings.get(readings.size() - 1).timeStamp;
             return timestamp;
    }

    public int getLatestCode() {
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

    public int getLatestPressure() {
        int pressure = readings.get(readings.size() - 1).pressure;
            return pressure;
    }

    public String getLatestCodeToText() {
            int codeNumber = getLatestCode();
            String codeToText = Conversions.convertCodeToText(codeNumber);
            return codeToText;
    }

    public double getLatestConvertTemp() {
        double temp = getLatestTemp();
        double tempToText = Conversions.convertTemp(temp);
        return tempToText;
    }

    public int getLatestWindToBeaufort() {
        double wind = getLatestWind();
        double windToBeaufort = Conversions.convertWindSpeedToBeaufortIndex(wind);
        return (int) windToBeaufort;
    }

    public String getLatestBeaufortToText() {
        int bft = getLatestWindToBeaufort();
        String bftToText = Conversions.convertBFTCodeToText(bft);
        return bftToText;
    }





}