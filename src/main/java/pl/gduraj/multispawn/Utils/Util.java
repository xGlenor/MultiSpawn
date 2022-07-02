package pl.gduraj.multispawn.Utils;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import pl.gduraj.multispawn.MultiSpawn;

public class Util {

    public static String color(String msg){
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    public static Location getLocation(String path){
        Location location = MultiSpawn.getInstance().getConfig().getLocation(path);
        return location;
    }

}
