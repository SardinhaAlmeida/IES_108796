package p1;

import p2.City;
import p2.CityForecast;
import p2.WeatherStarter;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger; 

public class AnyCityForecast {

    private static Logger logger = LogManager.getLogger(AnyCityForecast.class);
  
    public static void main(String args[]) {

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        Runnable task = () -> {
            City randomCity = WeatherStarter.getRandomCity();

            // Make a request to IpmaApiClient to get the forecast for the random city.
            CityForecast forecast = WeatherStarter.getForecast(randomCity.getGlobalIdLocal());

            
            // Log the forecast result.
            logger.info("Weather forecast for " + randomCity.getLocal() + ": between " + forecast.getTMin() + "ºC and " + forecast.getTMax() + "ºC.");

            // logger.debug("Debug log message");
            // logger.info("Info log message");
            // logger.error("Error log message");       
        };
        scheduler.scheduleAtFixedRate(task, 0, 20, TimeUnit.SECONDS);

    }
}