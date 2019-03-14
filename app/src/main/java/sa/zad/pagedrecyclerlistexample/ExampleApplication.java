package sa.zad.pagedrecyclerlistexample;

import android.app.Application;
import sa.zad.easyretrofit.EasyRetrofit;

public class ExampleApplication extends Application {

  private static ExampleApplication INSTANCE;
  private ApiService service;
  private EasyRetrofit easyRetrofit;

  public ExampleApplication(){
    INSTANCE = this;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    easyRetrofit = new EasyRetrofit(this);
    service = easyRetrofit.provideRetrofit().create(ApiService.class);
  }

  public EasyRetrofit sampleEasyRetrofit(){
    return easyRetrofit;
  }

  public static ExampleApplication getInstance(){
    if(INSTANCE != null){
      return INSTANCE;
    }
    return new ExampleApplication();
  }

  public ApiService service() {
    return service;
  }
}
