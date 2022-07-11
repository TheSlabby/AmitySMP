package codes.slab.amitysmp.commands;

import codes.slab.amitysmp.AmitySMP;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.jetbrains.annotations.NotNull;

public class Stats implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Scoreboard mainScoreBoard = AmitySMP.server.getScoreboardManager().getMainScoreboard();

        if (sender instanceof Player) {

            String playerName = sender.getName();
            if (args.length > 0)
                playerName = args[0];

            String msg = ChatColor.GOLD + playerName + "'s Stats:\n" + ChatColor.YELLOW;

            //TIME COUNTER
            int minutesPlayed = mainScoreBoard
                    .getObjective("timeInMinutes").getScore(playerName).getScore();

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
            int milesFlown = mainScoreBoard.getObjective("fly").getScore(playerName).getScore()
                    / 160934;
            msg += "\nMiles Flown: " + milesFlown;

            //endermen killed
            int endermenKilled = mainScoreBoard.getObjective("endermenKilled").getScore(playerName).getScore();
            msg += "\nEndermen Killed: " + endermenKilled;

            //items enchanted
            int enchanted = mainScoreBoard.getObjective("enchanted").getScore(playerName).getScore();
            msg += "\nItems Enchanted: " + enchanted;

            //crafting sessions
            int crafts = mainScoreBoard.getObjective("craft").getScore(playerName).getScore();
            msg += "\nCrafting Sessions: " + crafts;

            int deaths = mainScoreBoard.getObjective("deaths").getScore(playerName).getScore();
            msg += "\nDeaths: " + deaths;

            sender.sendMessage(msg);
        } else {
            return false;
        }
        return true;
    }
}
