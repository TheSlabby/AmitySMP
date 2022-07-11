package codes.slab.amitysmp.commands;

import codes.slab.amitysmp.AmitySMP;
import codes.slab.amitysmp.TPRequest;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class TP implements CommandExecutor {

    public static final int COOLDOWN = 60000;
    public static ArrayList<TPRequest> requests = new ArrayList<TPRequest>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player && args.length > 0) {
            //send request
            Player to = AmitySMP.server.getPlayer(args[0]);
            if (to != null) {
                requests.add(new TPRequest((Player) sender, to));
                sender.sendMessage(ChatColor.GREEN + "Request sent");
                to.sendMessage(ChatColor.GREEN + sender.getName() + " sent a tp request. \nAccept with "
                        + ChatColor.BOLD + "/tpa");
            } else {
                sender.sendMessage(ChatColor.RED + "Invalid player");
            }

        } else if (args.length == 0) {
            //accept request
            for (TPRequest request : requests) {
                if (request.isValid() && request.out == (Player) sender) {
                    request.in.teleport(request.out.getLocation());
                    sender.sendMessage(ChatColor.GREEN + "Teleport accepted for " + request.in.getName());
                    request.in.sendMessage(ChatColor.GREEN + "Teleporting to " + sender.getName());

                    //make this request invalid
                    request.tick -= TP.COOLDOWN;
                } else if (!request.isValid()) {
                    //make this request invalid
                    request.tick -= request.tick - TP.COOLDOWN;
                }
            }
        }
        return true;
    }

}
