package ghosti3.mcplugin.customplugin.disco;

import com.google.gson.Gson;
import java.io.Serializable;
import java.util.ArrayList;

public class DiscordMessage implements Serializable {

  /**
   * Must not exceed 2000 chars.
   */
  private String content;

  /**
   * Must not exceed 10 embed objects.
   */
  private ArrayList<DiscordEmbed> embeds;

  private DiscordMessage() {}

  /**
   * @return the content
   */
  public String getContent() { return content; }

  /**
   * @return the embed
   */
  public ArrayList<DiscordEmbed> getEmbeds() { return embeds; }

  @Override
  public String toString() {
    return new Gson().toJson(this).toString();
  }

  public static class Builder {
    public static final int MAX_CONTENT_LENGTH = 2001;
    public static final int MAX_EMBED_LENGTH = 10;

    private DiscordMessage result = new DiscordMessage();

    // BUG: should properly indicate that it this func may error and why.
    public Builder setContent(String content) {
      if (content.length() > MAX_CONTENT_LENGTH)
        return null;

      result.content = content;
      return this;
    }

    /**
     * Adds embed obj to array if {@link
     * DiscordMessage.Builder.MAX_EMBED_LENGTH} is not exceeded.
     *
     * @param embed {@link DiscordEmbed} to be added to message embed obj array
     * @return The calling object is returned.
     */
    public Builder setEmbed(DiscordEmbed embed) {
      if (result.embeds == null)
        result.embeds = new ArrayList<>();

      if (result.embeds.size() < MAX_EMBED_LENGTH)
        result.embeds.add(embed);

      return this;
    }

    public DiscordMessage toMessage() { return result; }
  }
}
