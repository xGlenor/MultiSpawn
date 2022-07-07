package pl.gduraj.multispawn.commands;

import io.papermc.lib.PaperLib;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;
import pl.gduraj.multispawn.MultiSpawn;

public class spawnCommand implements CommandExecutor {

    private MultiSpawn plugin;

    public spawnCommand() {
        this.plugin = MultiSpawn.getInstance();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        String name = args[0];

        if(args.length > 0) {

            if(!(sender instanceof Player)){
                Player target = Bukkit.getPlayer(args[1]);
                if(target == null){
                    sender.sendMessage(plugin.getConfigManager().getMSG("offlinePlayer"));
                    return true;
                }

                plugin.getSpawnStorage().teleportPlayer(target, name);
                return true;
            }

            Player player = (Player) sender;

            if(!player.hasPermission("multispawn.command.spawn")){
                player.sendMessage(plugin.getConfigManager().getMSG("noPermission"));
                return true;
            }
            
            if(args.length == 1) {
                if(args[0].equals("r")){
                    plugin.getSpawnStorage().teleportRandomSpawn(player);
                    return true;
                }else if(args[0].equals("-r")){
                    plugin.getSpawnStorage().reloadSpawn();
                    player.sendMessage("reload");
                    return true;
                }
                plugin.getSpawnStorage().teleportPlayer(player, name);
                return true;
            } else if (args.length == 2) {

                if(!player.hasPermission("multispawn.command.spawn.others")){
                    player.sendMessage(plugin.getConfigManager().getMSG("noPermission"));
                    return true;
                }

                Player target = Bukkit.getPlayer(args[1]);

                if(target == null){
                    sender.sendMessage(plugin.getConfigManager().getMSG("offlinePlayer"));
                    return true;
                }

                plugin.getSpawnStorage().teleportPlayer(target, name);
                return true;
            }

            sender.sendMessage(plugin.getConfigManager().getMSG("usageSpawn"));
            return true;

        }else {
            sender.sendMessage(plugin.getConfigManager().getMSG("usageSpawn"));
            return true;
        }

    }
}
