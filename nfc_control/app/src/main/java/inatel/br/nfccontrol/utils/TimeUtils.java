package inatel.br.nfccontrol.utils;

public class TimeUtils {

  public static final String DATEFORMAT = "dd/MM/yyyy";
  public static final String HOURFORMAT = "HH:mm";

  private static long secondsInMilli = 1000;
  private static long minutesInMilli = secondsInMilli * 60;
  private static long hoursInMilli = minutesInMilli * 60;
  private static long daysInMilli = hoursInMilli * 24;

  public static long[] getHour(long time) {
    long elapsedDays = time / daysInMilli;
    time = time % daysInMilli;

    long elapsedHours = time / hoursInMilli;
    time = time % hoursInMilli;

    long elapsedMinutes = time / minutesInMilli;
    time = time % minutesInMilli;

    return new long[] {elapsedHours, elapsedMinutes};
  }
}
