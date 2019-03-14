package sa.zad.pagedrecyclerlistexample.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MediaModel  {

  @Expose
  @SerializedName("size") public int size;
  @Expose
  @SerializedName("dimensions") public MediaModel.ImageDimension dimension;
  @Expose
  @SerializedName("name") public String name;
  @Expose
  @SerializedName("image") public String image;
  @Expose
  @SerializedName("user") public int userId;
  @Expose
  @SerializedName("id") public int id;


  public static class ImageDimension {

    @Expose
    @SerializedName("width") private int width;
    @Expose
    @SerializedName("height") private int height;
  }
}
