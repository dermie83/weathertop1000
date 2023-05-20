/**
 * This is the Station Class. It maintains a collection of Stations and readings.
 * The Station object is created by station name, latitude, longitude fields.
 * A one-to-many relationship, in the database, between the Station Class and Reading Class is created here.
 *
 * @version (20th May 2023)
 */

package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class Station extends Model {
    public String name;
    public float lat;
    public float lng;

    @OneToMany(cascade = CascadeType.ALL)
    public List<Reading> readings = new ArrayList<>();


    // Station Constructor is created here
    public Station(String name, float lat, float lng) {
        this.name = name;
        this.lat = lat;
        this.lng = lng;
    }

    /**
     * getLatestTimestamp() - This getter method returns the latest time stamp.
     *
     * @return     the latest time stamp is returned
     */

    public String getLatestTimestamp() {
        String timestamp = readings.get(readings.size() - 1).timeStamp;
        return timestamp;
    }

    /**
     * All Getter Methods For Weather Code executed here
     *
     * @return     Code, Code to Text, Text to Icon
     */

    public int getLatestWeatherCode() {
        int code = readings.get(readings.size() - 1).code;
        return code;
    }

    public String getLatestWeatherCodeToText() {
        int codeNumber = getLatestWeatherCode();
        String codeToText = Conversions.convertWeatherCodeToText(codeNumber);
        return codeToText;
    }

    public String getLatestWeatherCodeToIcon() {
        int codeNumber = getLatestWeatherCode();
        String codeToIcon = Conversions.convertWeatherToIcon(codeNumber);
        return codeToIcon;
    }

    /**
     * All Getter Methods For Temperature Readings executed here
     *
     * @return     Temperature, Convert Celsius to Fahrenheit,
     *              Temp to Text, Text to Icon, Max & Min Temp,
     *              3 most recent Temperature Readings,
     */

    public double getLatestTemp() {
        double temp = readings.get(readings.size() - 1).temperature;
        return temp;
    }

    public double getLatestConvertTemp() {
        double temp = getLatestTemp();
        double tempToText = Conversions.convertTemp(temp);
        return tempToText;
    }

    public double getMaxTemp() {
        double maxTemp = readings.get(0).temperature;
        for (int i = 1; i < readings.size(); i++) {
            if (maxTemp < readings.get(i).temperature)
                maxTemp = readings.get(i).temperature;
        }
        return maxTemp;
    }

    public double getMinTemp() {
        double minTemp = readings.get(0).temperature;
        for (int i = 1; i < readings.size(); i++) {
            if (minTemp > readings.get(i).temperature)
                minTemp = readings.get(i).temperature;
        }
        return minTemp;
    }

    public List<Double> getlatestTempReading() {
        List<Double> latestReadings = new ArrayList<Double>();

        for (int i = 0; i < readings.size(); i++) {
            latestReadings.add(readings.get(i).temperature);
        }
        return latestReadings;
    }

    public String checkTempTrend() {
        List<Double> arr = getlatestTempReading();
        // If the first two and the last two elements
        // of the array are in increasing order
        if ( readings.size() >2){

            if (arr.get(arr.size() - 2) <= arr.get(arr.size() - 1) &&
                    arr.get(arr.size() - 3) <= arr.get(arr.size() - 2))
                return Conversions.convertTrendToIcon("Increasing");
                // If the first two and the last two elements
                // of the array are in decreasing order
            else if (arr.get(arr.size() - 2) >= arr.get(arr.size() - 1) &&
                    arr.get(arr.size() - 3) >= arr.get(arr.size() - 2))
                return Conversions.convertTrendToIcon("Decreasing");
                // If the first two elements of the array are in
                // decreasing order and the last two elements
                // of the array are in increasing order
            else
                return Conversions.convertTrendToIcon("Steady");
        }
        {
            return  Conversions.convertTrendToIcon("No Trend Yet");
        }
    }

    /**
     * All Getter Methods For Wind Readings executed here
     *
     * @return     Latest Wind Reading, Wind Direction, and Wind Chill,
     *              Wind to bft , bft to text, text to Icon,
     *              Max & Min Wind,
     *              3 most recent Wind Readings,
     */


    public double getLatestWind() {
        double wind = readings.get(readings.size() - 1).windSpeed;
        return wind;
    }

    public double getLatestWindDirection() {
        double windDirection = readings.get(readings.size() - 1).windDirection;
        return windDirection;
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

    public double getMaxWind() {
        double maxWind = readings.get(0).windSpeed;
        for (int i = 1; i < readings.size(); i++) {
            if (maxWind < readings.get(i).windSpeed)
                maxWind = readings.get(i).windSpeed;
        }
        return maxWind;
    }

    public double getMinWind() {
        double minWind = readings.get(0).windSpeed;
        for (int i = 1; i < readings.size(); i++) {
            if (minWind > readings.get(i).windSpeed)
                minWind = readings.get(i).windSpeed;
        }
        return minWind;
    }

    public List<Double> getlatestWindReading() {
        List<Double> latestReadings = new ArrayList<Double>();

        for (int i = 0; i < readings.size(); i++) {
            latestReadings.add(readings.get(i).windSpeed);
        }
        return latestReadings;
    }

    public String checkWindTrend() {
        List<Double> arr = getlatestWindReading();
        // If the first two and the last two elements
        // of the array are in increasing order
        if (readings.size() > 2){
            if (arr.get(arr.size() - 2) <= arr.get(arr.size() - 1) &&
                    arr.get(arr.size() - 3) <= arr.get(arr.size() - 2))
                return Conversions.convertTrendToIcon("Increasing");
                // If the first two and the last two elements
                // of the array are in decreasing order
            else if (arr.get(arr.size() - 2) >= arr.get(arr.size() - 1) &&
                    arr.get(arr.size() - 3) >= arr.get(arr.size() - 2))
                return Conversions.convertTrendToIcon("Decreasing");
                // If the first two elements of the array are in
                // decreasing order and the last two elements
                // of the array are in increasing order
            else
                return Conversions.convertTrendToIcon("Steady");
        }
        {
            return Conversions.convertTrendToIcon("No Trend Yet");
        }
    }

    /**
     * All Getter Methods For Pressure Readings executed here
     *
     * @return     Latest Pressure Reading,
     *              Max & Min Pressure,
     *              3 most recent Pressure Readings,
     */

    public int getLatestPressure() {
        int pressure = readings.get(readings.size() - 1).pressure;
        return pressure;
    }

    public int getMaxPressure() {
        int maxPressure = readings.get(0).pressure;
        for (int i = 1; i < readings.size(); i++) {
            if (maxPressure < readings.get(i).pressure)
                maxPressure = readings.get(i).pressure;
        }
        return maxPressure;
    }

    public int getMinPressure() {
        int minPressure = readings.get(0).pressure;
        for (int i = 1; i < readings.size(); i++) {
            if (minPressure > readings.get(i).pressure)
                minPressure = readings.get(i).pressure;
        }
        return minPressure;
    }

    public List<Integer> getlatestPressureReading() {
        List<Integer> latestReadings = new ArrayList<Integer>();

        for (int i = 0; i < readings.size(); i++) {
            latestReadings.add(readings.get(i).pressure);
        }
        return latestReadings;
    }


    // Function to check the type of the array
    public String checkPressureTrend() {
        List<Integer> arr = getlatestPressureReading();
            // If the first two and the last two elements
            // of the array are in increasing order
        if (readings.size()> 2){
            if (arr.get(arr.size() - 2) <= arr.get(arr.size() - 1) &&
                    arr.get(arr.size() - 3) <= arr.get(arr.size() - 2))
                return Conversions.convertTrendToIcon("Increasing");
                // If the first two and the last two elements
                // of the array are in decreasing order
            else if (arr.get(arr.size() - 2) >= arr.get(arr.size() - 1) &&
                    arr.get(arr.size() - 3) >= arr.get(arr.size() - 2))
                return Conversions.convertTrendToIcon("Decreasing");
                // If the first two elements of the array are in
                // decreasing order and the last two elements
                // of the array are in increasing order
            else
                return Conversions.convertTrendToIcon("Steady");

        }
    {
        return Conversions.convertTrendToIcon("No Trend Yet");
    }
    }


}