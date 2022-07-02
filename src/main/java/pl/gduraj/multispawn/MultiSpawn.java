package pl.gduraj.multispawn;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class MultiSpawn extends JavaPlugin {

    private static MultiSpawn instance;
    private Config config;
    private SpawnStorage spawnStorage;

    public static MultiSpawn getInstance(){
        return instance;
    }


    @Override
    public void onEnable() {
        instance = this;

        this.config = new Config();
        this.config.saveDefault();

        this.spawnStorage = new SpawnStorage();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public FileConfiguration getConfig() {
        return getConfigManager().getConfig();
    }

    public Config getConfigManager() {
        return config;
    }

    public SpawnStorage getSpawnStorage() {
        return spawnStorage;
    }
}
