package sa.zad.pagedrecyclerlistexample.models;

import android.support.annotation.Nullable;
import com.google.gson.annotations.SerializedName;

public class UserInfo {

  @Nullable
  @SerializedName("display_pic") private MediaModel dpInfo;
  @SerializedName("id") private Long id;
  @SerializedName("email") private String email;
  @SerializedName("first_name") private String firstName;
  @SerializedName("last_name") private String lastName;
  @SerializedName("avatar_id") private Long avatar_id;
  @SerializedName("auth_type") private int authType;
  @SerializedName("user_gender") private String gender;
  @SerializedName("auth_type_info") private AuthTypeInfo authTypeInfo;
  @SerializedName("dob_timestamp") @Nullable
  private Long dob;
  @SerializedName("dob") @Nullable
  private String date;
  @SerializedName("username") @Nullable
  private String username;
}
