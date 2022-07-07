package pl.gduraj.multispawn.Utils;

import io.papermc.lib.PaperLib;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;
import pl.gduraj.multispawn.MultiSpawn;
import pl.gduraj.multispawn.storage.SpawnStorage;

public class NewPlayerTeleport implements Runnable{

    private MultiSpawn plugin;
    private SpawnStorage storage;
    private FileConfiguration config;
    private Player player;


    public NewPlayerTeleport(Player player) {
        this.plugin = MultiSpawn.getInstance();
        this.storage = plugin.getSpawnStorage();
        this.config = plugin.getConfig();
        this.player = player;
    }

    @Override
    public void run() {

        Location spawn = storage.getSpawn(storage.getRandomSpawn());

        if(spawn != null) {
            PaperLib.teleportAsync(player, spawn, PlayerTeleportEvent.TeleportCause.PLUGIN);
        }

    }
}
