package codes.slab.amitysmp.commands;

import codes.slab.amitysmp.AmitySMP;
import codes.slab.amitysmp.Data;
import codes.slab.amitysmp.util.ConcatArgs;
import codes.slab.amitysmp.util.DiscordWebhook;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class SetHome implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        
        if (sender instanceof Player) {
            Player player = (Player) sender;
            Data.setHome(player.getUniqueId(), player.getLocation());
            player.sendMessage(ChatColor.GOLD + "Set home to your current location: "
                            + ChatColor.YELLOW + player.getLocation().getBlockX() + ", " + player.getLocation().getBlockY() + ", " + player.getLocation().getBlockZ());
        }
        else {
            sender.sendMessage("You must be a player to use this command");
        }

        return true;
    }

}
