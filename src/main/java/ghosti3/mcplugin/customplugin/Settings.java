package ghosti3.mcplugin.customplugin;

import org.bukkit.configuration.file.FileConfiguration;

/**
 * Settings, Singleton
 */
public class Settings {
  private static Settings instance;

  public final String webhookId;
  public final String webhookToken;
  public final int messageDelay;

  private Settings(final String webhookId,
                   final String webhookToken,
                   final int messageDelay)
  {
    this.webhookId = webhookId;
    this.webhookToken = webhookToken;
    this.messageDelay = messageDelay;
  }

  @Override
  public String toString() {
    return String.format(
      "Settings [webhookId = '%s', webhookToken = '%s', messageDelay = '%d']",
      webhookId, webhookToken, messageDelay
    );
  }

  public static Settings fromFileConfig(FileConfiguration config)
  {
    if (instance != null)
      return instance;

    String webhookId = config.getString("webhook.id");
    String webhookToken = config.getString("webhook.token");
    int messageDelay = config.getInt("mdelay", 5);
    instance = new Settings(webhookId, webhookToken, messageDelay);

    String err = SettingsValidator.validate(instance);

    if (!err.isBlank())
      throw new RuntimeException(err);

    return instance;
  }
}
