package codes.slab.amitysmp.commands;

import codes.slab.amitysmp.util.FetchScore;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Stats implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player) {

            String playerName = sender.getName();
            if (args.length > 0)
                playerName = args[0];

            String msg = ChatColor.GOLD + playerName + "'s Stats:\n" + ChatColor.YELLOW;

            //TIME COUNTER
            int minutesPlayed = FetchScore.getScore((Player) sender, "timeInMinutes");

            if (minutesPlayed < 60) {
                //show in minutes
                msg += minutesPlayed + " minutes played.";
            } else if (minutesPlayed < 1440) {
                //show in hours
                msg += (minutesPlayed / 60) + " hours played";
            } else {
                //show in days
                msg += (minutesPlayed / 60 / 24) + " days played";
            }

            //show miles travelled
            int milesFlown = FetchScore.getScore((Player) sender, "fly") / 160934;
            msg += "\nMiles Flown: " + milesFlown;

            //endermen killed
            int endermenKilled = FetchScore.getScore((Player) sender, "endermenKilled");
                    msg += "\nEndermen Killed: " + endermenKilled;

            //items enchanted
            int enchanted = FetchScore.getScore((Player) sender, "enchanted");
                    msg += "\nItems Enchanted: " + enchanted;

            //crafting sessions
            int crafts = FetchScore.getScore((Player) sender, "craft");
                    msg += "\nCrafting Sessions: " + crafts;

            int deaths = FetchScore.getScore((Player) sender, "deaths");
                    msg += "\nDeaths: " + deaths;

            sender.sendMessage(msg);
        } else {
            return false;
        }
        return true;
    }
}
