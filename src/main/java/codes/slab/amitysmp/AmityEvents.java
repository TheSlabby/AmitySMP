package codes.slab.amitysmp;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class AmityEvents implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        e.setJoinMessage(ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + e.getPlayer().getName()
                + ChatColor.RESET + ChatColor.AQUA + " joined the game");

        //send message of the day
        AmitySMP.server.getScheduler().runTaskLater(AmitySMP.plugin, new Runnable() {
            @Override
            public void run() {
                e.getPlayer().sendMessage(ChatColor.BLUE
                        + "Welcome " + e.getPlayer().getName() + "\n" + Data.loadData(Data.path).motd);
            }
        }, 40); // 2 seconds

        if (!e.getPlayer().hasPlayedBefore()) {
            AmitySMP.server.broadcastMessage(ChatColor.BOLD.toString()
                    + ChatColor.GOLD + e.getPlayer().getName() + " joined for the first time!");
        }
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        e.setQuitMessage(ChatColor.DARK_PURPLE.toString() + ChatColor.BOLD + e.getPlayer().getName()
                + ChatColor.RESET + ChatColor.LIGHT_PURPLE + " left the game");
    }

    @EventHandler
    public void onChat(PlayerChatEvent e) {
        e.setCancelled(true);
        AmitySMP.server.broadcastMessage(ChatColor.DARK_GREEN + e.getPlayer().getName()
                + ChatColor.GRAY + " ⫸ " + ChatColor.GREEN + e.getMessage());
    }
}
