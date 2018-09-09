package inatel.br.nfccontrol.data.model;

import com.google.gson.annotations.SerializedName;

public class UserAuthentication {

  @SerializedName("id")
  private String mId;

  @SerializedName("ttl")
  private String mTTL;

  @SerializedName("created")
  private String mCreateDate;

  @SerializedName("userId")
  private String mServerId;

  public String getId() {
    return mId;
  }

  public void setId(String id) {
    mId = id;
  }

  public String getTTL() {
    return mTTL;
  }

  public void setTTL(String TTL) {
    mTTL = TTL;
  }

  public String getCreateDate() {
    return mCreateDate;
  }

  public void setCreateDate(String createDate) {
    mCreateDate = createDate;
  }

  public String getServerId() {
    return mServerId;
  }

  public void setServerId(String serverId) {
    mServerId = serverId;
  }
}
