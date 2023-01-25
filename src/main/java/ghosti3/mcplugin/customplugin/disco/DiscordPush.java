package ghosti3.mcplugin.customplugin.disco;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class DiscordPush {
    private final URL webhook;
    private DiscordMessage msg;

    public DiscordPush(String webhookUrl) throws MalformedURLException {
        webhook = new URL(webhookUrl);
    }

    /**
     * @return the msg
     */
    public DiscordMessage getMsg() {
        return msg;
    }

    public DiscordPush build(MsgTypes type) {
        msg = type.toMessage();
        return this;
    }

    public boolean send() {
        String content = msg.toString();

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
                    System.out.println(line);
                }
            }
            conn.disconnect();
        } catch (IOException e) {
            System.out.println(e);
        }

        return true;
    }

    public static enum MsgTypes {
        ServerOnline("Server is online :green_circle:"),
        ServerOffline("Server is offline :red_circle:"),
        OnlinePlayers();

        private String content;

        private MsgTypes() {
        }

        private MsgTypes(String content) {
            this.content = content;
        }

        /**
         * @return the content
         */
        public String getContent() {
            return content;
        }

        /**
         * @return final DiscordMessage 
         */
        public DiscordMessage toMessage() {
            return new DiscordMessage.Builder().setContent(this.content).toMessage();
        }
    }
}
