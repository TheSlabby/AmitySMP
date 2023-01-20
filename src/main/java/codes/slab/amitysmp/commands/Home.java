package codes.slab.amitysmp.commands;

import codes.slab.amitysmp.Data;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


public class Home implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        
        if (sender instanceof Player) {
            Player player = (Player) sender;
            Data data = Data.loadData(Data.path);
            if (data.homes.containsKey(player.getUniqueId())) {
                player.teleport(data.homes.get(player.getUniqueId()));
                player.sendMessage(ChatColor.GOLD + "" +  ChatColor.BOLD + "Teleported home!");
            }
            else {
                player.sendMessage(ChatColor.RED + "You don't have a home set! Set one with /sethome <name>");
            }
        }
        else {
            sender.sendMessage("You must be a player to use this command");
        }

        return true;
    }

}
