package sa.zad.pagedrecyclerlistexample.models;

import androidx.annotation.IntDef;
import androidx.annotation.StringDef;
import com.google.gson.annotations.SerializedName;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class AuthTypeInfo {

  @SerializedName("id")
  @AuthTypeId
  private int mId;
  @SerializedName("auth_type")
  @AuthTypes
  private String mAuthType;

  public AuthTypeInfo() {
  }

  public AuthTypeInfo(@AuthTypeId int id) {

    mId = id;

    if (id == AuthTypeId.FACEBOOK_TYPE_ID) {
      mAuthType = AuthTypes.FACEBOOK;
    }
    if (id == AuthTypeId.GOOGLE_TYPE_ID) {
      mAuthType = AuthTypes.GOOGLE;
    } else if (id == AuthTypeId.EMAIL_TYPE_ID) {
      mAuthType = AuthTypes.EMAIL;
    }

  }

  public AuthTypeInfo(@AuthTypes String authType) {

    mAuthType = authType;

    if (authType.equalsIgnoreCase(AuthTypes.FACEBOOK)) {
      mId = AuthTypeId.FACEBOOK_TYPE_ID;
    }
    if (authType.equalsIgnoreCase(AuthTypes.GOOGLE)) {
      mId = AuthTypeId.GOOGLE_TYPE_ID;
    } else if (authType.equalsIgnoreCase(AuthTypes.EMAIL)) {
      mId = AuthTypeId.EMAIL_TYPE_ID;
    }

  }


  @Retention(RetentionPolicy.SOURCE)
  @StringDef({AuthTypes.FACEBOOK, AuthTypes.GOOGLE, AuthTypes.EMAIL})
  public @interface AuthTypes {
    String GOOGLE = "google";
    String FACEBOOK = "facebook";
    String EMAIL = "email";
  }

  @Retention(RetentionPolicy.SOURCE)
  @IntDef(
          {AuthTypeId.FACEBOOK_TYPE_ID, AuthTypeId.GOOGLE_TYPE_ID, AuthTypeId.EMAIL_TYPE_ID})
  public @interface AuthTypeId {
    int FACEBOOK_TYPE_ID = 1;
    int GOOGLE_TYPE_ID = 2;
    int EMAIL_TYPE_ID = 3;
  }
}
