package codes.slab.amitysmp;

import codes.slab.amitysmp.commands.JoinTeam;
import codes.slab.amitysmp.commands.MOTD;
import codes.slab.amitysmp.commands.Stats;
import codes.slab.amitysmp.commands.TP;
import org.bukkit.Server;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class AmitySMP extends JavaPlugin {
    public static Server server;
    public static Plugin plugin;

    @Override
    public void onEnable() {
        // Plugin startup logic
        PluginDescriptionFile pdfFile = getDescription();
        Logger logger = Logger.getLogger("Minecraft");

        logger.info(pdfFile.getName()+ "has been enabled! Version:" + pdfFile.getVersion());

        server = this.getServer();
        plugin = this;

        registerEvents();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        PluginDescriptionFile pdfFile = getDescription();
        Logger logger = Logger.getLogger("Minecraft");

        logger.info(pdfFile.getName()+ "has been disabled! Version:" + pdfFile.getVersion());
    }

    public void registerEvents(){
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new AmityEvents(),  this);

        //commands
        this.getCommand("join").setExecutor(new JoinTeam());
        this.getCommand("tpa").setExecutor(new TP());
        this.getCommand("motd").setExecutor(new MOTD());
        this.getCommand("stats").setExecutor(new Stats());
    }

}
