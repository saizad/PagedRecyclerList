package sa.zad.pagedrecyclerlistexample;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {


  public static Intent getActivityIntent(Class<?> activity, Context context) {
    return new Intent(context, activity);
  }

  public void log(String logText) {
    log(this.getLocalClassName(), logText);
  }

  public void log(String tag, String logText) {
    Log.d(this.getLocalClassName(), logText);
  }

  public void toast(String toastText) {
    Toast.makeText(this, toastText, Toast.LENGTH_SHORT).show();
  }


}
