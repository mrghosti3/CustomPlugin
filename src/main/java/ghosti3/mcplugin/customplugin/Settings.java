package ghosti3.mcplugin.customplugin;

import org.bukkit.configuration.file.FileConfiguration;

/**
 * Settings, Singleton
 */
public class Settings {
    private static Settings instance;

    public final String webhookUrl;
    public final int messageDelay;

    private Settings(final String webhookUrl, final int messageDelay) {
        this.webhookUrl = webhookUrl;
        this.messageDelay = messageDelay;
    }

    @Override
    public String toString() {
        return String.format(
                "Settings [webhookUrl = '%s', messageDelay = '%d']",
                webhookUrl, messageDelay);
    }

    public static Settings fromFileConfig(FileConfiguration config) {
        if (instance != null)
            return instance;

        String webhookUrl = config.getString("webhook_url");
        int messageDelay = config.getInt("mdelay", 5);

        if (webhookUrl == null)
            throw new NullPointerException();

        instance = new Settings(webhookUrl, messageDelay);
        return instance;
    }
}
