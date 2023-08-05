package ghosti3.mcplugin.customplugin.disco;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class DiscordMessageTest {
  @Test
  void buildSimpleMessage() {
    var msg =
        new DiscordMessage.Builder().setContent("Hello world!").toMessage();

    assertEquals("Hello world!", msg.getContent());
    assertTrue(DiscordMessage.Builder.MAX_CONTENT_LENGTH >
               msg.getContent().length());
    assertEquals(null, msg.getEmbeds());
  }

  @Test
  void buildEmbedMessage() {
    var embed = new DiscordEmbed.Builder()
                    .setTitle("Test")
                    .setDescription("Desc")
                    .toEmbed();
    var msg = new DiscordMessage.Builder().setEmbed(embed).toMessage();

    assertEquals(null, msg.getContent());
    assertNotEquals(null, msg.getEmbeds());
    assertTrue(DiscordMessage.Builder.MAX_EMBED_LENGTH >
               msg.getEmbeds().size());
  }

  @Test
  void buildMessageWithEmbedAndContent() {
    var embed = new DiscordEmbed.Builder()
                    .setTitle("Test")
                    .setDescription("Desc")
                    .toEmbed();
    var msg = new DiscordMessage.Builder()
                  .setContent("Hello world!")
                  .setEmbed(embed)
                  .toMessage();

    assertEquals("Hello world!", msg.getContent());
    assertTrue(DiscordMessage.Builder.MAX_CONTENT_LENGTH >
               msg.getContent().length());
    assertNotEquals(null, msg.getEmbeds());
    assertTrue(DiscordMessage.Builder.MAX_EMBED_LENGTH >
               msg.getEmbeds().size());
  }
}
