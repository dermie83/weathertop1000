package controllers;

import java.util.ArrayList;
import java.util.List;
import models.Station;
import models.Reading;
import play.Logger;
import play.mvc.Controller;

public class Dashboard extends Controller
{
  public static void index() {
    Logger.info("Rendering Dashboard");
    List<Station> stations = Station.findAll();
    render ("dashboard.html", stations);
  }

  public static void deleteStation(Long id)
  {
    Station station = Station.findById(id);
    station.delete();
    Logger.info ("Removing " + station.id);

    redirect("/dashboard");
  }

  public static void addStation(String name)
  {
    Station station = new Station (name);
    Logger.info ("Adding a new Weather Station is called " + name);
    station.save();
    redirect ("/dashboard");
  }

}

