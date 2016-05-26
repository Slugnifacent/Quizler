package joshua.com.quizler.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;

/**
 * Created by Joshua on 5/25/2016.
 */
public class Storage {
    private static final String prefFilename = "Preferences";
    private static Storage storage;
    private boolean initialized;
    private Activity activity;
    private SharedPreferences preferences;

    public Storage()
    {
        initialized = false;
    }

    public static Storage instance()
    {
        if(storage == null)
        {
            storage = new Storage();
        }
        return storage;
    }

    public void init(Activity activity)
    {
        initialized = true;
        this.activity = activity;
        preferences = activity.getPreferences(Context.MODE_PRIVATE);
    }

    public int retrieveFromPreferences(String Key)
    {
        if(!initialized) return 0;
        return preferences.getInt(Key, 0);
    }

    public  void saveToPreferences(String Key, int Value)
    {
       if(!initialized)return;
       SharedPreferences.Editor edit = preferences.edit();
       edit.putInt(Key, Value);
       edit.commit();
    }

    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /* Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }
}
