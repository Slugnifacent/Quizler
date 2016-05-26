package joshua.com.quizler.util;

import android.nfc.Tag;
import  android.util.Log;
/**
 * Created by Joshua on 5/25/2016.
 */
public class XLogger {
    private String tag;

    public XLogger(Class JavaClass)
    {
        tag = JavaClass.toString();
    }

    public void info(String Message)
    {
        Log.i(tag,Message);
    }

    public void debug(String Message)
    {
        Log.d(tag, Message);
    }

    public void warning(String Message)
    {
        Log.w(tag, Message);
    }

    public void error(String Message)
    {
        Log.e(tag, Message);
    }
}
