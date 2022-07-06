package pl.gduraj.multispawn.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
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

            Player target = Bukkit.getPlayer(args[1]);

            if(target == null){
                sender.sendMessage(plugin.getConfigManager().getMSG("offlinePlayer"));
                return true;
            }

            plugin.getSpawnStorage().teleportPlayer(target, name);
            return true;

        }else {
            sender.sendMessage(plugin.getConfigManager().getMSG("usageSpawn"));
            return true;
        }

    }
}
