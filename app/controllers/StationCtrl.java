package controllers;

import play.Logger;
import models.Station;
import models.Reading;
import play.mvc.Controller;

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

    public static void addReading(Long id, int code, double temperature, double windSpeed, int pressure)
    {
        Reading reading = new Reading(code,temperature,windSpeed,pressure);
        Station station = Station.findById(id);
        station.readings.add(reading);
        station.save();
        redirect ("/station/" + id);
    }
}