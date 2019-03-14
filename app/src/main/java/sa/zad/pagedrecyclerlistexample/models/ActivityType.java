package sa.zad.pagedrecyclerlistexample.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Sa-ZAD on 2017-11-24.
 */

public class ActivityType {

  @SerializedName("id") public Integer id;
  @SerializedName("activity") public String activity;
  @SerializedName("verified") public boolean verified;
  @SerializedName("creator") public UserInfo creator;
  
}
