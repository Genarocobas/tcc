package inatel.br.nfccontrol.data.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
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
  @SerializedName("name")
  private String mName;

  @NonNull
  @ColumnInfo(name = "email")
  @SerializedName("email")
  private String mEmail;

  @ColumnInfo(name = "password")
  @SerializedName("password")
  private String mPassword;

  @ColumnInfo(name = "is_authenticated")
  private boolean mIsAuthenticated;

  @ColumnInfo(name = "grant_type")
  @SerializedName("grant_type")
  private String mGrantType;

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

  public boolean isAuthenticated() {
    return mIsAuthenticated;
  }

  public void setIsAuthenticated(boolean authenticated) {
    mIsAuthenticated = authenticated;
  }

  public String getGrantType() {
    return mGrantType;
  }

  public void setGrantType(String grantType) {
    mGrantType = grantType;
  }
}
