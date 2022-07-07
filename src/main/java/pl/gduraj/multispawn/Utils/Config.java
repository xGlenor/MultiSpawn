package pl.gduraj.multispawn.Utils;

import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import pl.gduraj.multispawn.MultiSpawn;
import pl.gduraj.multispawn.Utils.Util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Config {

    private MultiSpawn plugin;
    private FileConfiguration config;
    private File file;

    public Config(){
        this.plugin = MultiSpawn.getInstance();
        this.file = new File(plugin.getDataFolder(), "spawn.yml");
        this.config = new YamlConfiguration();

    }

    public void saveDefault(){
        if(!file.exists()){
            plugin.saveResource("spawn.yml", false);
        }

        try {
            config.load(file);
        } catch (InvalidConfigurationException | IOException e){
            plugin.getLogger().severe("CONFIGURATION ERROR: " + e.getMessage());
        }
    }

    public void save(){
        if(config == null || file == null) return;

        try {
            getConfig().save(file);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void reload(){
        config = YamlConfiguration.loadConfiguration(file);
        saveDefault();
    }

    public FileConfiguration getConfig(){
        return config;
    }

    public Map<String, Location> getLocationSectionMap(String path){
        ConfigurationSection section = config.getConfigurationSection(path);
        Map<String, Location> result = new HashMap<>();

        for (String locStr : section.getKeys(false)){
            result.put(locStr, Util.getLocation("spawns."+locStr+".location"));
        }

        System.out.println(result);
        return result;
    }

    public String getMSG(String path) {
        return Util.color(config.getString("message."+path));
    }


}
