package ghosti3.mcplugin.customplugin;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

import static org.bukkit.entity.EntityType.CREEPER;

class CustomEventListener implements Listener {
  @EventHandler
  public void onCreeperExplode(EntityExplodeEvent evt) {
    if (evt.getEntityType() != CREEPER) return;
      evt.blockList().clear();
  }
}
