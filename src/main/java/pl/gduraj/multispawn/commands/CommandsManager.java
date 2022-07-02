package pl.gduraj.multispawn.commands;

import pl.gduraj.multispawn.MultiSpawn;

public class CommandsManager {

    private MultiSpawn plugin;

    public CommandsManager() {
        this.plugin = MultiSpawn.getInstance();

        plugin.getCommand("spawn").setExecutor(new spawnCommand());
        plugin.getCommand("setspawn").setExecutor(new setSpawnCommand());

    }
}
