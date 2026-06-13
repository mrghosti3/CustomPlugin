package ghosti3.mcplugin.customplugin.settings;

import java.util.ArrayList;
import java.util.Map;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.EntityType;

public class GriefCaptureSettings {

    private static final EntityType[] SUPPORTED_BLOCK_CHANGE_ENTITIES = new EntityType[]{
        EntityType.ENDERMAN
    };

    // Not all explosive entities cause the explosion themselves. While CREEPERs explode themselves,
    // GHASTs summon EntityType::FIREBALLs which explode. Players can do it too, using commands,
    // but we will count them as "spawned by ghasts".
    private static final Map<EntityType,EntityType> SUPPORTED_EXPLODING_ENTITIES = Map.of(
        EntityType.CREEPER,
        EntityType.CREEPER,
        EntityType.GHAST,
        EntityType.FIREBALL
    );

    private ArrayList<EntityType> blockChangeEntitySet;
    private ArrayList<EntityType> explodeEntitySet;

    public GriefCaptureSettings(ConfigurationSection griefOptions) {
        this.blockChangeEntitySet = new ArrayList<>();
        this.explodeEntitySet = new ArrayList<>();

        for (EntityType entityType : SUPPORTED_BLOCK_CHANGE_ENTITIES) {
            boolean griefOpt = griefOptions.getBoolean(entityType.toString());

            if (griefOpt) {
                blockChangeEntitySet.add(entityType);
            }
        }

        for (var explosiveEntity : SUPPORTED_EXPLODING_ENTITIES.entrySet()) {
            if (!griefOptions.getBoolean(explosiveEntity.getKey().toString())) {
                continue;
            }

            explodeEntitySet.add(explosiveEntity.getValue());
        }
    }

    public boolean notBlockChangeSet(EntityType entityType) {
        return !blockChangeEntitySet.contains(entityType);
    }

    public boolean notExplosionSet(EntityType entityType) {
        return !explodeEntitySet.contains(entityType);
    }
}
