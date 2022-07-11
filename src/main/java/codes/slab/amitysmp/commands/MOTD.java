package codes.slab.amitysmp.commands;

import codes.slab.amitysmp.AmitySMP;
import codes.slab.amitysmp.Data;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MOTD implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 0) {

            //convert args to string
            String result = "";
            for (String arg : args) {
                result += arg + " ";
            }

            Data data = new Data(result);
            data.saveData(Data.path);
            sender.sendMessage(ChatColor.GREEN + "Updated MOTD");
        } else {
            //print out motd
            Data data = Data.loadData(Data.path);
            sender.sendMessage(data.motd);
        }
        return true;
    }

}
