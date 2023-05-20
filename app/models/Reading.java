/**
 * This is the Reading Class. It maintains a collection of readings.
 * The Reading object has 6 fields in its constructor, code, temp, wind speed, wind direction, pressure, timestamp
 *
 * @version (20th May 2023)
 */

package models;

import javax.persistence.Entity;
import play.db.jpa.Model;

@Entity
public class Reading extends Model
{
    public int code;
    public double temperature;
    public double windSpeed;
    public double windDirection;
    public int pressure;
    public String timeStamp;

    public Reading(String timeStamp, int code, double temperature, double windSpeed, double windDirection, int pressure)
    {

        this.timeStamp = timeStamp;
        this.code = code;
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.pressure = pressure;
    }


}
