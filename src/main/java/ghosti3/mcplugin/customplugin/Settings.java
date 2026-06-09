package ghosti3.mcplugin.customplugin;

import org.bukkit.configuration.file.FileConfiguration;

/**
 * Settings, Singleton
 */
public class Settings {
  private static Settings instance;

  private Settings() {
  }

  @Override
  public String toString() {
    return "Settings";
  }

  public static void reset() {
    instance = null;
  }

  public static Settings fromFileConfig(FileConfiguration config) {
    if (instance != null) {
      return instance;
    }

    instance = new Settings();
    return instance;
  }
}
