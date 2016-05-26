package joshua.com.quizler.util;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

/**
 * Created by Joshua on 5/26/2016.
 */
public class XUI {

    private static Activity  activity;
    public static void SetActivity(Activity context)
    {
        activity = context;
    }

    public static void Toast(String Message)
    {
        if(activity != null) {
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(activity, Message, duration);
            toast.show();
        }
    }
}
