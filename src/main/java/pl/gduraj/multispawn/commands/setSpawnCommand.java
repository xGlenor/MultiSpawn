package pl.gduraj.multispawn.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.gduraj.multispawn.MultiSpawn;

public class setSpawnCommand implements CommandExecutor {

    private MultiSpawn plugin;

    public setSpawnCommand() {
        this.plugin = MultiSpawn.getInstance();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)) {
            sender.sendMessage(plugin.getConfigManager().getMSG("onlyPlayer"));
            return true;
        }

        Player player = (Player) sender;

        if(!player.hasPermission("multispawn.command.setspawn")){
            player.sendMessage(plugin.getConfigManager().getMSG("noPermission"));
            return true;
        }

        if(args.length == 1) {
            String name = args[0];
            if(validate(name)){
                plugin.getSpawnStorage().setSpawn(player.getLocation(), name);
                player.sendMessage(plugin.getConfigManager().getMSG("setSpawn").replace("%name%", name));
            }else {
                player.sendMessage(plugin.getConfigManager().getMSG("validateError"));
            }
        }else {
            String name = "default";
            plugin.getSpawnStorage().setSpawn(player.getLocation(), name);
            player.sendMessage(plugin.getConfigManager().getMSG("setSpawn").replace("%name%", name));
        }

        return true;
    }


    private boolean validate(String str) {
        return str.matches("^[a-zA-Z0-9]*$");
    }

}
