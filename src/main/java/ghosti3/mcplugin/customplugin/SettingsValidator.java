package ghosti3.mcplugin.customplugin;

/**
 * SettingsValidator
 */
class SettingsValidator {
  private static final String WEBHOOK_DEFAULT = "enter_yourself";
  private static final String BAD_DEFAULT_VALUE = "Field %s must be changed from default value!";

  /**
   * Checks validity of settings.
   * 
   * @param settings object to validate.
   *
   * @return If invalid RuntimeException, if valid - null
   */
  public static String validate(Settings settings)
  {
    StringBuilder errMsg = new StringBuilder();

    if (settings.webhookId.equalsIgnoreCase(WEBHOOK_DEFAULT))
      errMsg.append(String.format(BAD_DEFAULT_VALUE, "webhookId", settings.webhookId));

    if (settings.webhookToken.equalsIgnoreCase(WEBHOOK_DEFAULT)) {
      errMsg.append(" ");
      errMsg.append(String.format(BAD_DEFAULT_VALUE, "webhookToken", settings.webhookId));
    }

    return errMsg.toString();
  }
}
