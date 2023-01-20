package codes.slab.amitysmp;

import codes.slab.amitysmp.commands.*;
import org.bukkit.Server;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class AmitySMP extends JavaPlugin {
    public static Server server;
    public static Plugin plugin;

    //discord stuff
    public static String discordHookURL;
    public static String discordAvatarURL;
    public static String discordUsername;
    FileConfiguration config = getConfig();

    @Override
    public void onEnable() {
        // Plugin startup logic
        PluginDescriptionFile pdfFile = getDescription();
        Logger logger = Logger.getLogger("Minecraft");

        logger.info(pdfFile.getName()+ "has been enabled! Version:" + pdfFile.getVersion());

        //config
        config.addDefault("discord_webhook_url", "[empty]");
        config.addDefault("discord_username", "SMP");
        config.addDefault("discord_avatar_url", "https://preview.redd.it/mp8zyh671nt61.png?width=1920&format=png&auto=webp&s=d74893f71fac3d7e51cbc63e34e08e7d6a96afba");

        config.options().copyDefaults(true);
        saveConfig();
        discordHookURL = config.getString("discord_webhook_url");
        discordAvatarURL = config.getString("discord_avatar_url");
        discordUsername = config.getString("discord_username");

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
        this.getCommand("broadcast").setExecutor(new DiscordBroadcast());
        this.getCommand("home").setExecutor(new Home());
        this.getCommand("sethome").setExecutor(new SetHome());
    }

}
