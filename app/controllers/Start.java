/**
 * This is the Start Control Class.
 * It maintains the start.html file i.e. homepage
 *
 * @version (20th May 2023)
 */

package controllers;

import play.Logger;
import play.mvc.Controller;

public class Start extends Controller
{
  public static void index() {
    Logger.info("Rendering Start");
    render ("start.html");
  }
}
