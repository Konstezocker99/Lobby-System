package eu.trexplay.lobby.Listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class WeahterChangeListener implements Listener {

     @EventHandler
     public void onWeatherChange(WeatherChangeEvent event) {

         event.setCancelled(true);

     }

}
