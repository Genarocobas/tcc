package inatel.br.nfccontrol.utils;

public class Util {

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
  public static String getDrivingTimeBubbleText(int value) {
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
}
