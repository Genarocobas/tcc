package inatel.br.nfccontrol.utils;

/*
 * Copyright (C) 2018 Instituto Nacional de Telecomunicações
 *
 * All rights are reserved. Reproduction in whole or part is
 * prohibited without the written consent of the copyright owner.
 *
 */

import android.util.Log;

/**
 * Logger class util to help application logging.
 *
 * @author Daniela Mara Silva <danielamara@inatel.br>
 * @since 30/01/2018.
 */
public class Logger {

  public static final boolean DEBUG = true;
  private static final int MAX_TAG_LENGTH = 23;
  private static final int CLASS_STACK_ITEM = 3;

  /**
   * Returns A TAG for the caller class.
   *
   * @return The Tag to be used for logs.
   */
  public static String getTag() {
    final StackTraceElement caller = Thread.currentThread().getStackTrace()[CLASS_STACK_ITEM];
    String tag = caller.getClassName();
    final int lastDot = tag.lastIndexOf('.');

    if (lastDot > 0) {
      tag = tag.substring(lastDot + 1);
    }

    if (tag.length() > MAX_TAG_LENGTH) {
      tag = tag.substring(0, MAX_TAG_LENGTH);
    }

    return tag;
  }

  /**
   * Returns a TAG for the caller class.
   *
   * @param caller the caller class
   * @return The Tag to be used for logs.
   */
  private static String getTag(final Class caller) {
    String tag = caller.getSimpleName();
    if (tag.length() > MAX_TAG_LENGTH) {
      tag = tag.substring(0, MAX_TAG_LENGTH);
    }
    return tag;
  }

  public static void d(Class caller, String text) {
    Log.d(getTag(caller), text);
  }

  public static void e(Class caller, String text) {
    Log.e(getTag(caller), text);
  }

  public static void i(Class caller, String text) {
    Log.i(getTag(caller), text);
  }
}

