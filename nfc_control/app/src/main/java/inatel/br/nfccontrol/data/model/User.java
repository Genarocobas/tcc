package inatel.br.nfccontrol.data.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "users")
public class User implements Serializable {

  @NonNull
  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "id")
  private long mId;

  @ColumnInfo(name = "name")
  @SerializedName("username")
  private String mName;

  @NonNull
  @ColumnInfo(name = "email")
  @SerializedName("email")
  private String mEmail;

  @Ignore
  @SerializedName("id")
  @ColumnInfo(name = "server_id")
  private int mServerId;

  @ColumnInfo(name = "password")
  @SerializedName("password")
  private String mPassword;

  @ColumnInfo(name = "cardId")
  @SerializedName("idCard")
  private String mCardId;

  @ColumnInfo(name = "is_authenticated")
  private boolean mIsAuthenticated;

  private boolean mConfigured;

  @Ignore
  private JourneyConfig mJourneyConfig;

  @Ignore
  private String mAccessToken;

  @NonNull
  public long getId() {
    return mId;
  }

  public void setId(@NonNull long id) {
    mId = id;
  }

  public String getName() {
    return mName;
  }

  public void setName(String name) {
    mName = name;
  }

  @NonNull
  public String getEmail() {
    return mEmail;
  }

  public void setEmail(@NonNull String email) {
    mEmail = email;
  }

  public String getPassword() {
    return mPassword;
  }

  public void setPassword(String password) {
    mPassword = password;
  }

  public int getServerId() {
    return mServerId;
  }

  public void setServerId(int serverId) {
    mServerId = serverId;
  }

  public String getCardId() {
    return mCardId;
  }

  public void setCardId(String cardId) {
    mCardId = cardId;
  }

  public boolean isAuthenticated() {
    return mIsAuthenticated;
  }

  public void setIsAuthenticated(boolean authenticated) {
    mIsAuthenticated = authenticated;
  }

  public void setAuthenticated(boolean authenticated) {
    mIsAuthenticated = authenticated;
  }

  public boolean isConfigured() {
    return mConfigured;
  }

  public void setConfigured(boolean configured) {
    mConfigured = configured;
  }

  public String getAccessToken() {
    return mAccessToken;
  }

  public void setAccessToken(String accessToken) {
    mAccessToken = accessToken;
  }

  public JourneyConfig getJourneyConfig() {
    return mJourneyConfig;
  }

  public void setJourneyConfig(JourneyConfig journeyConfig) {
    mJourneyConfig = journeyConfig;
  }
}
