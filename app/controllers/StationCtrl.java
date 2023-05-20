/**
 * This is the Station Control Class.
 * It maintains and enables the station.html file display the station readings
 *
 * @version (20th May 2023)
 */

package controllers;

import play.Logger;
import models.Station;
import models.Reading;
import play.mvc.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StationCtrl extends Controller {

    public static void index(Long id) {
        Station station = Station.findById(id);
        Logger.info("Station id = " + id);
        render("station.html", station);
    }

    public static void deleteReading(Long id, Long readingID)
    {
        Station station = Station.findById(id);
        Reading reading = Reading.findById(readingID);
        station.readings.remove(reading);
        station.save();
        reading.delete();
        Logger.info ("Removing " + reading.code);

        render("station.html", station);

    }

    public static void addReading(Long id, String timeStamp, int code, double temperature,
                                  double windSpeed, int windDirection, int pressure)
    {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy h:mm:ss a");
        String formattedDate = sdf.format(date);
        Reading reading = new Reading(formattedDate, code, temperature, windSpeed, windDirection, pressure);
        Station station = Station.findById(id);
        station.readings.add(reading);
        station.save();
        redirect ("/station/" + id);
    }


}