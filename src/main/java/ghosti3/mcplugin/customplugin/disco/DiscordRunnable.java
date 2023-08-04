package ghosti3.mcplugin.customplugin.disco;

import javax.annotation.Nonnull;

public class DiscordRunnable implements Runnable {

  private final DiscordPush sender;
  private final DiscordMessage message;

  public DiscordRunnable(@Nonnull final DiscordPush sender,
                         @Nonnull final DiscordMessage message) {

    this.sender = sender;
    this.message = message;
  }

  @Override
  public void run() {
    sender.send(message);
  }
}
