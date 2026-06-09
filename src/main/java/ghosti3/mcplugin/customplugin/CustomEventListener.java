package ghosti3.mcplugin.customplugin;

import static org.bukkit.entity.EntityType.CREEPER;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

class CustomEventListener implements Listener {
  public CustomEventListener(CustomPlugin instance) {
  }

  @EventHandler
  public void onEntityExplode(EntityExplodeEvent evt) {
    if (evt.getEntityType() != CREEPER) {
      return;
    }

    evt.blockList().clear();
  }
}
