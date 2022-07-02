package pl.gduraj.multispawn;

import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import pl.gduraj.multispawn.Utils.Config;
import pl.gduraj.multispawn.commands.CommandsManager;
import pl.gduraj.multispawn.storage.SpawnStorage;

public final class MultiSpawn extends JavaPlugin implements TabCompleter {

    private static MultiSpawn instance;
    private Config config;
    private SpawnStorage spawnStorage;
    private CommandsManager commandsManager;

    public static MultiSpawn getInstance(){
        return instance;
    }


    @Override
    public void onEnable() {
        instance = this;

        this.config = new Config();
        this.config.saveDefault();

        this.spawnStorage = new SpawnStorage();
        this.commandsManager = new CommandsManager();

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
