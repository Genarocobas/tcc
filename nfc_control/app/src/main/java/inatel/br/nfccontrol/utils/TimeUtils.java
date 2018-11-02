package inatel.br.nfccontrol.utils;

public class TimeUtils {

  public static final String DATEFORMAT = "dd/MM/yyyy";
  public static final String HOURFORMAT = "HH:mm";

  public static long SECONDS_IN_MILLIS = 1000;
  public static long MINUTES_IN_MILLIS = SECONDS_IN_MILLIS * 60;
  public static long HOURS_IN_MILLIS = MINUTES_IN_MILLIS * 60;
  public static long DAYS_IN_MILLIS = HOURS_IN_MILLIS * 24;

  public static long[] getHour(long time) {
    long elapsedDays = time / DAYS_IN_MILLIS;
    time = time % DAYS_IN_MILLIS;

    long elapsedHours = time / HOURS_IN_MILLIS;
    time = time % HOURS_IN_MILLIS;

    long elapsedMinutes = time / MINUTES_IN_MILLIS;
    time = time % MINUTES_IN_MILLIS;

    return new long[] {elapsedHours, elapsedMinutes};
  }
}
