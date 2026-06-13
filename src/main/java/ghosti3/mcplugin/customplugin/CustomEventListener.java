package ghosti3.mcplugin.customplugin;

import java.util.logging.Logger;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import ghosti3.mcplugin.customplugin.settings.GriefCaptureSettings;

class CustomEventListener implements Listener {
  private Logger logger;
  private GriefCaptureSettings griefOptions;

  public CustomEventListener(CustomPlugin context) {
    this.logger = context.getLogger();
    this.griefOptions = context.getSettings().getGriefOptions();
  }

  @EventHandler
  public void onEntityExplode(EntityExplodeEvent evt) {
    if (griefOptions.notExplosionSet(evt.getEntityType())) {
      return;
    }

    evt.blockList().clear();
  }

  @EventHandler
  public void onEntityChangeBlock(EntityChangeBlockEvent evt) {
    if (griefOptions.notBlockChangeSet(evt.getEntityType())) {
      return;
    }

    evt.setCancelled(true);
  }

  @EventHandler
  public void onPlayerDeath(PlayerDeathEvent evt) {
    var playerName = evt.getPlayer().getName();
    var posVec3 = new int[] {
      evt.getPlayer().getLocation().getBlock().getX(),
      evt.getPlayer().getLocation().getBlock().getY(),
      evt.getPlayer().getLocation().getBlock().getZ(),
    };

    logger.info(String.format(
      "Player death: %s at [%d, %d, %d]",
      playerName,
      posVec3[0],
      posVec3[1],
      posVec3[2]
    ));
  }
}
