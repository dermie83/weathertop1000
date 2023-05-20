/**
 * This is the Dashboard Control Class.
 * It maintains and enables the dashboard.html file display Stations and their latest Readings
 *
 * @version (20th May 2023)
 */

package controllers;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import models.Member;
import models.Station;
import play.Logger;
import play.mvc.Controller;

public class Dashboard extends Controller
{
  public static void index() {
    Logger.info("Rendering Dashboard");
    Member member = Accounts.getLoggedInMember();
    List<Station> stations = member.stations;
    Collections.sort(stations, new Comparator<Station>() {
      @Override
      public int compare(Station o1, Station o2) {
        return o1.name.compareTo(o2.name);
      }
    });
    render ("dashboard.html", member, stations);
  }

  public static void deleteStation(Long id, Long stationid)
  {
    Member member = Member.findById(id);
    Station station = Station.findById(stationid);
    member.stations.remove(station);
    member.save();
    station.delete();
    Logger.info ("Removing Station named" + station.name);

    redirect("/dashboard");
  }

  public static void addStation(String name, float lat, float lng)
  {
    Member member = Accounts.getLoggedInMember();
    Station station = new Station (name, lat, lng);
    member.stations.add(station);
    Logger.info ("Adding a new Weather Station called " + name);
    station.save();
    redirect ("/dashboard");
  }

}

