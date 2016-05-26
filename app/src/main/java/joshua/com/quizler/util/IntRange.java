package joshua.com.quizler.util;

/**
 * Created by Joshua on 5/19/2016.
 */
public class IntRange {
    int min;
    int max;

    public IntRange(int Min, int Max)
    {
        min = Min;
        max = Max;
    }

    public boolean WithinRange(int Value)
    {
        Boolean result = (Value >= min) && (Value < max);
        if(Value == min) result = true;
        return result;
    }
}
