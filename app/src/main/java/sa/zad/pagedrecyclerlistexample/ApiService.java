package sa.zad.pagedrecyclerlistexample;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import retrofit2.http.Url;
import sa.zad.easyretrofit.CachePolicy;
import sa.zad.easyretrofit.EasyRetrofitClient;
import sa.zad.easyretrofit.observables.NeverErrorObservable;
import sa.zad.pagedrecyclerlistexample.models.ActivityType;
import sa.zad.pagedrecyclerlistexample.models.DataModel;
import sa.zad.pagedrecyclerlistexample.models.RedditPagedModel;

public interface ApiService {

  @Headers(EasyRetrofitClient.CACHE_POLICY_HEADER + ": " + CachePolicy.LOCAL_IF_FRESH)
  @GET
  NeverErrorObservable<RedditPagedModel> list(@Url String url);

  @Headers(EasyRetrofitClient.CACHE_POLICY_HEADER + ": " + CachePolicy.LOCAL_IF_FRESH)
  @GET(value = "http://192.168.0.27:8000/api/events/activities/")
  NeverErrorObservable<DataModel<List<ActivityType>>> findActivities(
          @Query("search") String search, @Query("after") String after, @Query("before") String before);
}
