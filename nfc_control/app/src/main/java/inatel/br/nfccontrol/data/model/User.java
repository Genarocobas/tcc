package inatel.br.nfccontrol.data.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.Data;

@Data
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
}
