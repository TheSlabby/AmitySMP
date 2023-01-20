package codes.slab.amitysmp.util;

import codes.slab.amitysmp.AmitySMP;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;

import java.util.*;


public class FetchScore {


    private static Scoreboard main = AmitySMP.server.getScoreboardManager().getMainScoreboard();

    public static int getScore(Player player, String objective) {
        return main.getObjective(objective).getScore(player.getName()).getScore();
    }

    public static HashMap<String, Integer> getTop(String objective) {
        HashMap<String, Integer> map = new HashMap<String, Integer>();

        Set<String> entries = main.getEntries();
        for (String entry : entries) {
            int score = main.getObjective(objective).getScore(entry).getScore();
            if (score != 0)
                map.put(entry, score);
        }

        return map;
    }
}
