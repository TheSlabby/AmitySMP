package codes.slab.amitysmp;

import codes.slab.amitysmp.commands.TP;
import org.bukkit.entity.Player;

public class TPRequest {

    public long tick;
    public Player in;
    public Player out;

    public TPRequest(Player in, Player out) {
        this.in = in;
        this.out = out;
        tick = System.currentTimeMillis();
        AmitySMP.server.getLogger().info("tick: "+ tick);
    }

    public boolean isValid() {
        return System.currentTimeMillis() - tick < TP.COOLDOWN;
    }

}
