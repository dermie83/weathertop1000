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