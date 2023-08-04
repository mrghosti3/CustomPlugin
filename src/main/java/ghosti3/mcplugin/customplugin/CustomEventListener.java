package ghosti3.mcplugin.customplugin;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerQuitEvent.QuitReason;
import org.bukkit.scheduler.BukkitScheduler;

import ghosti3.mcplugin.customplugin.disco.DiscordEmbed;
import ghosti3.mcplugin.customplugin.disco.DiscordMessage;
import ghosti3.mcplugin.customplugin.disco.DiscordRunnable;

import static org.bukkit.entity.EntityType.CREEPER;

import org.bukkit.Color;

class CustomEventListener implements Listener {

    private final CustomPlugin plugin;
    private final BukkitScheduler scheduler;

    public CustomEventListener(CustomPlugin instance) {
        this.plugin = instance;
        this.scheduler = instance.getServer().getScheduler();
    }

    @EventHandler
    public void onCreeperExplode(EntityExplodeEvent evt) {
        if (evt.getEntityType() != CREEPER)
            return;

        evt.blockList().clear();
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent evt) {
        var embed = new DiscordEmbed.Builder()
                    .setColor(Color.LIME)
                    .setTitle("%s joined the server".formatted(
                        evt.getPlayer().getName()))
                    .toEmbed();
        this.scheduler.runTaskAsynchronously(
            plugin,
            new DiscordRunnable(
                plugin.getSender(),
                new DiscordMessage.Builder().setEmbed(embed).toMessage()
            )
        );
  }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent evt) {
        if (evt.getReason() != QuitReason.DISCONNECTED) {
            return;
        }

        var embed = new DiscordEmbed.Builder()
                    .setColor(Color.RED)
                    .setTitle("%s left the server".formatted(
                        evt.getPlayer().getName()))
                    .toEmbed();
        this.scheduler.runTaskAsynchronously(
            plugin,
            new DiscordRunnable(
                plugin.getSender(),
                new DiscordMessage.Builder().setEmbed(embed).toMessage()
            )
        );
  }
}
