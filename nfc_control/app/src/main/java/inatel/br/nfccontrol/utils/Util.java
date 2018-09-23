package inatel.br.nfccontrol.utils;

public class Util {

  private static final int MIN_CONTINUOUS_START_HOUR = 4;

  private static final int MIN_JOURNEY_REST_START_HOUR = 10;

  public static int getStepsTime(int hStart, int mStart, int hEnd, int mEnd, int mStep) {
    int steps = (hEnd - hStart) * ((int) (60 / mStep));

    if (mStart != 0) {
      steps = steps - (mStart / mStep);
    }

    if (mEnd != 0) {
      steps = steps + (mEnd / mStep);
    }

    return steps;
  }

  /**
   * Returns the text for the DiscreteSeekbar
   *
   * @param value time
   * @return time in String
   */
  public static String getLunchIntervalBubbleText(int value) {
    int hour_time = value / 6;
    int min_time = value % 6;
    String sMin;

    if (min_time == 0) {
      sMin = "00";
    } else {
      sMin = min_time + "0";
    }
    return "0" + (hour_time + 1) + ":" + sMin;
  }

  /**
   * Returns the text for the Weerly Rest time bubble in DiscreteSeekbar
   *
   * @param value weekly rest time
   * @return time in weekly rest as String
   */
  public static String getContinuousTimeBubbleText(int value) {
    int hStart = MIN_CONTINUOUS_START_HOUR;

    int hour_time = value / 6;
    int min_time = value % 6;

    String sMin;
    if (min_time == 0) {
      sMin = "00";
    } else {
      sMin = min_time + "0";
    }

    String sHour;
    if ((hour_time + hStart) < 10) {
      sHour = "0" + (hour_time + hStart);
    } else {
      sHour = (hour_time + hStart) + "";
    }

    return sHour + ":" + sMin;
  }

  /**
   * Returns the text for the Daily Rest time bubble in DiscreteSeekbar
   *
   * @param value daily rest time
   * @return time in daily rest as String
   */
  public static String getJourneyIntervalBubbleText(int value) {
    int hStart = MIN_JOURNEY_REST_START_HOUR;
    int hour_time = value / 6;
    int min_time = value % 6;

    String sMin;
    if (min_time == 0) {
      sMin = "00";
    } else {
      sMin = min_time + "0";
    }

    String sHour;
    if ((hour_time + hStart) < 10) {
      sHour = "0" + (hour_time + hStart);
    } else {
      sHour = (hour_time + hStart) + "";
    }

    return sHour + ":" + sMin;
  }
}
