package ghosti3.mcplugin.customplugin.disco;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DiscordEmbedTest {
    @Test
    void basicEmbedTestWithTitle() {
        var embed = new DiscordEmbed.Builder().setTitle("Test").toEmbed();

        assertEquals(null, embed.getDescription());
        assertEquals(null, embed.getColor());
        assertEquals("Test", embed.getTitle());
    }

    @Test
    void basicEmbedTestWithDescription() {
        var embed = new DiscordEmbed.Builder().setDescription("Test").toEmbed();

        assertEquals(null, embed.getTitle());
        assertEquals(null, embed.getColor());
        assertEquals("Test", embed.getDescription());
    }
}
