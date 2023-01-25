package ghosti3.mcplugin.customplugin.disco;

import java.io.Serializable;

import com.google.gson.Gson;

/**
 * Embed object used for transforming into embed json obj. Must not exceed
 * overall 6000 chars.
 */
public class DiscordEmbed implements Serializable {

    /**
     * Must not exceed 256 chars.
     */
    private String title;
    @SuppressWarnings("unused")
    private final String type = "rich";
    /**
     * Must not exceed 4096 chars.
     */
    private String description;
    private Integer color;

    private DiscordEmbed() {
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return the color
     */
    public Integer getColor() {
        return color;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this).toString();
    }

    public static class Builder {

        private DiscordEmbed result = new DiscordEmbed();

        public Builder setTitle(String title) {
            result.title = title;
            return this;
        }

        public Builder setDescription(String description) {
            result.description = description;
            return this;
        }

        public Builder setColor(int color) {
            result.color = color;
            return this;
        }

        public DiscordEmbed toEmbed() {
            return result;
        }
    }
}
