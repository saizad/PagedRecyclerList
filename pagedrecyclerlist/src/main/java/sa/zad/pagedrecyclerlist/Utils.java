package sa.zad.pagedrecyclerlist;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.*;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Utils {


  public static void writeStreamToFile(@NonNull InputStream input, @NonNull File file) throws IOException {
    writeStreamToFile(input, file, integer -> {
    });
  }

  public static void writeStreamToFile(@NonNull InputStream input, @NonNull File file, @NonNull CallAction<Long> writtenAction) throws IOException {
    OutputStream output = null;
    try {
      output = new FileOutputStream(file);
      byte[] buffer = new byte[4 * 1024]; // or other buffer size
      int read;
      long written = 0;
      while ((read = input.read(buffer)) != -1) {
        output.write(buffer, 0, read);
        written += read;
        writtenAction.call(written);
      }
      output.flush();
    } finally {
      input.close();

      if (Utils.isNotNull(output))
        output.close();
    }
  }

  public static boolean isNull(final @Nullable Object object) {
    return object == null;
  }

  public static boolean isNotNull(final @Nullable Object object) {
    return object != null;
  }

  @NonNull
  public static <T> T coalesce(final @Nullable T value, final @NonNull T theDefault) {
    if (value != null) {
      return value;
    }
    return theDefault;
  }

  public static float decimalFloat(float value) {
    DecimalFormat df = new DecimalFormat("#.##");
    df.setRoundingMode(RoundingMode.HALF_UP);
    return Float.parseFloat(df.format(value));
  }

}
