package ghosti3.mcplugin.customplugin;

import org.bukkit.configuration.file.FileConfiguration;

import ghosti3.mcplugin.customplugin.settings.GriefCaptureSettings;

/**
 * Settings, Singleton
 */
public class Settings {
  private GriefCaptureSettings griefOptions;

  private Settings() {
  }

  public GriefCaptureSettings getGriefOptions() {
    return griefOptions;
  }

  @Override
  public String toString() {
    return "Settings";
  }

  public static Settings fromFileConfig(FileConfiguration config) {
    var instance = new Settings();
    instance.griefOptions = new GriefCaptureSettings(config.getConfigurationSection("capture.grief"));
    return instance;
  }
}
