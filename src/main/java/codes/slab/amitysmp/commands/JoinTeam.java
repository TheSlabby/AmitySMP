package codes.slab.amitysmp.commands;

import codes.slab.amitysmp.AmitySMP;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.Set;

public class JoinTeam implements CommandExecutor {

    private Scoreboard main = AmitySMP.server.getScoreboardManager().getMainScoreboard();
    private Set<Team> teams;
    private String teamList = "Teams: ";

    public JoinTeam() {
        //setup teams
        teams = main.getTeams();
        AmitySMP.server.getLogger().info("teams found: " + teams.size());

        //team representation
        for (Team team : teams) {
            teamList += team.getName() + ", ";
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player && args.length > 0) {

//            if (args[0].toLowerCase().equals("loner") && main.getEntryTeam(sender.getName()) != null) {
//                main.getEntryTeam(sender.getName()).removeEntry(sender.getName());
//                AmitySMP.server.broadcastMessage(ChatColor.RED + sender.getName() + " is now a loner :(");
//                return true;
//            }

            Player player = (Player) sender;
            if (main.getTeam(args[0]) != null) {
                main.getTeam(args[0]).addEntry(player.getName());
                sender.sendMessage(ChatColor.GREEN + "Success. :)");
                AmitySMP.server.broadcastMessage(ChatColor.AQUA + player.getName() + " has joined " +
                        args[0].toUpperCase());
            } else {
                sender.sendMessage(ChatColor.RED
                        + "Invalid team. :(\n" + teamList);
            }
        } else return false;
        return true;
    }
}
