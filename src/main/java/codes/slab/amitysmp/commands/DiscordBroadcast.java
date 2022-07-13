package codes.slab.amitysmp.commands;

import codes.slab.amitysmp.AmitySMP;
import codes.slab.amitysmp.util.ConcatArgs;
import codes.slab.amitysmp.util.DiscordWebhook;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class DiscordBroadcast implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length > 0) {
            String result = ConcatArgs.concat(args);
            sendBroadcast(result);
        }

        return true;
    }

    public void sendBroadcast(String msg) {
        DiscordWebhook hook = new DiscordWebhook(AmitySMP.discordHookURL);
        hook.setContent(msg);
        hook.setUsername(AmitySMP.discordUsername);
        hook.setAvatarUrl(AmitySMP.discordAvatarURL);

        try {
            hook.execute();
            AmitySMP.server.getLogger().info("sent webhook!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
