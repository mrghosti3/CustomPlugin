package ghosti3.mcplugin.customplugin.disco;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

import javax.net.ssl.HttpsURLConnection;

public class DiscordPush {
    /**
     * Prebuilt message for when server is back online.
     */
    public final static DiscordMessage SERVER_ONLINE = new DiscordMessage.Builder()
                .setContent("Server is online :green_circle:")
                .toMessage();

    /**
     * Prebuilt message for when server is being shut down.
     */
    public final static DiscordMessage SERVER_OFFLINE = new DiscordMessage.Builder()
            .setContent("Server is offline :red_circle:")
            .toMessage();

    private final URL webhook;
    private final Logger logger;

    public DiscordPush(String webhookUrl, Logger logger) throws MalformedURLException {
        webhook = new URL(webhookUrl);
        this.logger = logger;
    }

    public boolean send(final DiscordMessage msg) {
        final String content = msg.toString();

        try {
            var conn = (HttpsURLConnection) webhook.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Content-Length", Integer.toString(content.getBytes().length));

            var httpOut = new DataOutputStream(conn.getOutputStream());
            httpOut.writeBytes(content);
            httpOut.close();

            try (var reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    logger.info("Discord returned: " + line);
                }
            }
            conn.disconnect();
        } catch (IOException e) {
            System.out.println(e);
        }

        return true;
    }
}
