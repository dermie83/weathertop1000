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

    public String getLatestCodeToText() {
        int number = readings.get(readings.size() - 1).code;
        String codeText = Conversion.convertCodeToText(number);
        return codeText;
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


}