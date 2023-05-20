/**
 * This is the About Control Class.
 * It maintains the about.html file
 *
 * @version (20th May 2023)
 */

package controllers;

import play.*;
import play.mvc.*;
import java.util.*;
import models.*;

public class About extends Controller
{
  public static void index() {
    Logger.info("Rendering about");
    render ("about.html");
  }
}

