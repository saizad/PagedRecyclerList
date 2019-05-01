package sa.zad.pagedrecyclerlistexample;

import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;
import sa.zad.easyretrofit.observables.NeverErrorObservable;
import sa.zad.pagedrecyclerlistexample.models.ActivityType;
import sa.zad.pagedrecyclerlistexample.models.DataModel;
import sa.zad.pagedrecyclerlistexample.models.RedditPagedModel;

import java.util.List;

public interface ApiService {

  @GET
  NeverErrorObservable<RedditPagedModel> list(@Url String url);

  @GET(value = "http://192.168.0.27:8000/api/events/activities/")
  NeverErrorObservable<DataModel<List<ActivityType>>> findActivities(
          @Query("search") String search, @Query("after") String after, @Query("before") String before);
}
