package ghosti3.mcplugin.customplugin;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerQuitEvent.QuitReason;

import ghosti3.mcplugin.customplugin.disco.DiscordEmbed;
import ghosti3.mcplugin.customplugin.disco.DiscordMessage;
import ghosti3.mcplugin.customplugin.disco.DiscordPush;

import static org.bukkit.entity.EntityType.CREEPER;

import org.bukkit.Color;

class CustomEventListener implements Listener {
    private final DiscordPush senderRef;

    public CustomEventListener(CustomPlugin instance) {
        this.senderRef = instance.getSender();
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
                .setTitle("%s joined the server".formatted(evt.getPlayer().getName()))
                .toEmbed();
        senderRef.send(new DiscordMessage.Builder().setEmbed(embed).toMessage());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent evt) {
        if (evt.getReason() != QuitReason.DISCONNECTED)
            return;

        var embed = new DiscordEmbed.Builder()
                .setColor(Color.RED)
                .setTitle("%s left the server".formatted(evt.getPlayer().getName()))
                .toEmbed();
        senderRef.send(new DiscordMessage.Builder().setEmbed(embed).toMessage());
    }
}
