package sa.zad.pagedrecyclerlistexample.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RedditPagedModel {

    @SerializedName("kind")
    public String kind;
    @SerializedName("data")
    public ModelData data;

    public static class ModelData {
        @SerializedName("modhash")
        public String modhash;
        @SerializedName("dist")
        public int dist;
        @SerializedName("children")
        public List<Items> children;
        @SerializedName("after")
        public String after;
        @SerializedName("before")
        public String before;
    }
}
