package pl.gduraj.multispawn;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.Map;

public class SpawnStorage {

    private MultiSpawn plugin;
    private FileConfiguration config;
    private Map<String, Location> spawns;

    public SpawnStorage() {
        this.plugin = MultiSpawn.getInstance();
        this.config = plugin.getConfig();
        this.spawns = new HashMap<>();
    }

    public void reloadSpawn(){
        synchronized (spawns) {
            plugin.getConfigManager().reload();
            spawns.clear();
            spawns.putAll(plugin.getConfigManager().getLocationSectionMap("spawns"));
        }
    }

    public void setSpawn(Location loc, String name){
        name = name.toLowerCase();

        synchronized (spawns) {
            spawns.put(name, loc);
            config.set("spawns."+name, loc);
            plugin.getConfigManager().save();
        }
    }

    public Location getSpawn(String name){
        if(name == null){
            return getWorldSpawn();
        }

        name = name.toLowerCase();

        synchronized (spawns){
            if(!spawns.containsKey(name)){
                if(spawns.containsKey("default")){
                    return spawns.get("spawn");
                }

                return getWorldSpawn();
            }
            return spawns.get(name);
        }
    }

    private Location getWorldSpawn(){
        for(World world : plugin.getServer().getWorlds()){
            if(world.getEnvironment() != World.Environment.NORMAL) {
                continue;
            }
            return world.getSpawnLocation();
        }
        return plugin.getServer().getWorlds().get(0).getSpawnLocation();
    }


}
