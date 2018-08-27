package inatel.br.nfccontrol.di.preference;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;

/**
 * {@link SharedPreferences} to hold string values.
 *
 * @see
 * <a href="https://medium.com/google-developer-experts/persist-your-data-elegantly-u2020-way-c50be19acf9#.p53f7kt4u">Persist
 * your data elegantly: U2020 way - Medium</a>.
 * @see <a href="https://github.com/pomopomo/WearPomodoro/">WearPomodoro - GitHub</a>.
 */
public class StringPreference {

  private final SharedPreferences mPreferences;

  private final String mKey;

  private final String mDefaultValue;

  /**
   * Constructor.
   *
   * @param preferences  {@link SharedPreferences} of Android API.
   * @param key          The name of the preference to retrieve.
   * @param defaultValue Value to return if this preference does not exist.
   */
  public StringPreference(@NonNull SharedPreferences preferences, @NonNull String key,
                          String defaultValue) {
    mPreferences = preferences;
    mKey = key;
    mDefaultValue = defaultValue;
  }

  /**
   * Retrieve a string value from the preferences.
   *
   * @return Returns the preference value if it exists, or defValue.
   */
  public String get() {
    return mPreferences.getString(mKey, mDefaultValue);
  }

  /**
   * Set a string value in the preferences editor.
   *
   * @param value The new value for the preference.
   */
  public void put(String value) {
    mPreferences.edit().putString(mKey, value).apply();
  }

  /**
   * Remove the string value in the preferences editor.
   */
  public void remove() {
    mPreferences.edit().remove(mKey).apply();
  }

  /**
   * Checks if key exists.
   *
   * @return <code>true</code> if key exists. <code>false</code> otherwise.
   */
  public boolean exists() {
    return mPreferences.contains(mKey);
  }
}