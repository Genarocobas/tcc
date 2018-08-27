package inatel.br.nfccontrol.di.preference;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;

/**
 * {@link SharedPreferences} to hold boolean values.
 *
 * @see
 * <a href="https://medium.com/google-developer-experts/persist-your-data-elegantly-u2020-way-c50be19acf9#.p53f7kt4u">Persist
 * your data elegantly: U2020 way - Medium</a>
 * @see <a href="https://github.com/pomopomo/WearPomodoro/">WearPomodoro - GitHub</a>.
 */
public class BooleanPreference {

  private final SharedPreferences mPreferences;

  private final String mKey;

  private final boolean mDefaultValue;

  /**
   * Constructor.
   *
   * @param preferences  {@link SharedPreferences} of Android API.
   * @param key          The name of the preference to retrieve.
   * @param defaultValue Value to return if this preference does not exist.
   */
  public BooleanPreference(@NonNull SharedPreferences preferences, @NonNull String key,
                           boolean defaultValue) {
    mPreferences = preferences;
    mKey = key;
    mDefaultValue = defaultValue;
  }

  /**
   * Retrieve a boolean value from the preferences.
   *
   * @return Returns the preference value if it exists, or defValue.
   */
  public boolean get() {
    return mPreferences.getBoolean(mKey, mDefaultValue);
  }

  /**
   * Set a boolean value in the preferences editor.
   *
   * @param value The new value for the preference.
   */
  public void put(boolean value) {
    mPreferences.edit().putBoolean(mKey, value).apply();
  }

  /**
   * Remove the boolean value in the preferences editor.
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