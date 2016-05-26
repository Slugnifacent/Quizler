package joshua.com.quizler.util;

import java.util.Random;

/**
 * Created by Joshua on 5/26/2016.
 */
public class XRandom {
    private static Random random = new Random();
    public static int nextInt(int Max)
    {
        return random.nextInt(Max);
    }
}
