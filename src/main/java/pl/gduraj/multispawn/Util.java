package pl.gduraj.multispawn;

import org.bukkit.ChatColor;
import org.bukkit.Location;

public class Util {

    public static String color(String msg){
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    public static Location getLocation(String path){
        Location location = MultiSpawn.getInstance().getConfig().getLocation(path);
        return location;
    }

}
