package inatel.br.nfccontrol.utils;

/**
 * Created by guilhermeyabu on 06/06/2018.
 */
public class ValidationConstants {
  public static final String IP_MASK_WITH_12_NUMBERS = "###.###.###.###";
  public static final String IP_MASK_WITH_8_NUMBERS = "###.###.#.#";
  public static final String IP_MASK_WITH_9_NUMBERS = "###.###.##.#";
  public static final String IP_MASK_WITH_10_NUMBERS = "###.###.##.##";
  public static final String HOUR_MASK = "##:##";
  public static final String DATE_MASK = "##/##";

  public static final String NAME_REGEX = "[A-Za-z0-9:._@-]+(?:[ ][A-Za-z0-9:._@-]+)*$";
  public static final String PHONE_REGEX = "[0-9,#*]+";
  public static final String PASSWORD_REGEX = "^\\d{4}(?:\\d{2})?$";
  public static final String IP_ADDRESS_REGEX =
      "^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}"
          + "([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$";
  public static final String HOUR_REGEX = "^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$";
  public static final String DATE_REGEX = "(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])";
  public static final String TIME_REGEX = "[0-9]+";
  public static final String PARTITION_ACCOUNT_REGEX = "[B-F0-9]+";
  public static final String SERIAL_NUMBER_REGEX = "^\\d{1,9}$";
  public static final String EMAIL_REGEX = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
      "\\@" + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" + "\\." + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}"
      + ")+";
  public static final String USER_NAME_REGEX =
      "(?=^.{2,60}$)^[a-zA-ZÀÁÂĖÈÉÊÌÍÒÓÔÕÙÚÛÇ][a-zàáâãèéêìíóôõùúç]+(?:[ ]"
          + "(?:das?|dos?|de|e|[A-Z][a-z]+))*$";

  public static final String INPUT_TYPE_DECIMAL = "INPUT TYPE DECIMAL";
  public static final String INPUT_TYPE_DECIMAL_PASSWORD = "INPUT TYPE DECIMAL PASSWORD";
  public static final String INPUT_TYPE_TEXT_NORMAL = "INPUT TYPE TEXT NORMAL";
  public static final String INPUT_TYPE_TEXT_PASSWORD = "INPUT TYPE TEXT PASSWORD";
  public static final String INPUT_TYPE_NUMBER_PHONE = "INPUT TYPE NUMBER PHONE";

  public static final int TEXT_NAME_MAX_LENGTH = 9;
  public static final int PASSWORD_MAX_LENGTH = 6;
  public static final int PARTITION_ACCOUNT_MAX_LENGTH = 4;
  public static final int PORT_MAX_LENGTH = 4;
  public static final int IP_ADDRESS_MAX_LENGTH = 15;
  public static final int HOUR_MAX_LENGTH = 5;
  public static final int DATE_MAX_LENGTH = 5;
  public static final int KEYBOARD_MESSAGE_MAX_LENGTH = 16;
  public static final int TIMES_INPUT_MAX_LENGTH = 3;
  public static final int SSID_NAME_MAX_LENGTH = 32;
  public static final int DDNS_ADDRESS_MAX_LENGTH = 32;
  public static final int SSID_PASSWORD_MAX_LENGTH = 24;
  public static final int PHONE_MAX_LENGTH = 24;
  public static final int APN_MAX_LENGTH = 48;
  public static final int SERIAL_NUMBER_MAX_LENGTH = 9;
  public static final int USER_NAME_MAX_LENGTH = 25;
  public static final int USER_EMAIL_MAX_LENGTH = 60;
  public static final int USER_PASSWORD_MAX_LENGTH = 12;
  public static final int DESTINATION_IP_MAX_LENGTH = 40;

  public static final int TIMES_INPUT_MAX_NUMBER = 255;
  public static final int TEXT_MAX_NUMBER = 0;
  public static final int PORT_MAX_NUMBER = 9999;
  public static final int PASSWORD_MAX_NUMBER = 9999;
  public static final int SERIAL_NUMBER_MAX_NUMBER = 999999999;

  public static final int MAX_LENGTH_24 = 24;
  public static final int MAX_LENGTH_4 = 4;
  public static final int MAX_LENGTH_3 = 3;
  public static final int MAX_NUMBER_OF_24_POSITIONS = 0;
  public static final int MAX_NUMBER_15 = 15;
  public static final int MAX_NUMBER_9999 = 9999;
  public static final String EMAIL_IMAGE = "email image";
  public static final String PASSWORD_IMAGE = "password image";
}

