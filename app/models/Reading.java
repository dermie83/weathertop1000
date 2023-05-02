package models;

import javax.persistence.Entity;
import java.text.SimpleDateFormat;
import java.util.Date;
import play.db.jpa.Model;
@Entity
public class Reading extends Model
{
    public int code;
    public double temperature;
    public double windSpeed;
    public int pressure;
    public String timeStamp;

//    Date date = new Date();
//    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
//    String timeStamp = sdf.format(date);


    public Reading(String timeStamp, int code, double temperature, double windSpeed, int pressure)
    {

        this.timeStamp = timeStamp;
        this.code = code;
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.pressure = pressure;
    }
}
