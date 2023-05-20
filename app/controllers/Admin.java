/**
 * This is the Admin Control Class.
 * It maintains and enables the admin.html file display all the station readings from the database
 *
 * @version (20th May 2023)
 */

package controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import models.Reading;
import models.Station;
import play.mvc.Controller;

public class Admin extends Controller
{
    public static void index() {
        List<Reading> readings = Reading.findAll();
        render ("admin.html", readings);
    }

}