package joshua.com.quizler;

import android.app.Activity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by Joshua on 5/18/2016.
 */
public class Test {
    ArrayList<Question> questions;
    Random random;

    Question currentQuestion;

    public Test(Activity activity, String Filename)
    {
        random = new Random();
        questions = new ArrayList<Question>();

        BufferedReader reader = null;
        try {
            InputStream raw = activity.getResources().openRawResource(R.raw.questions);
            reader = new BufferedReader(
                    new InputStreamReader(raw, "UTF8"));

            // do reading, usually loop until end of file reading
            String mLine;
            while ((mLine = reader.readLine()) != null) {
                String[] values = mLine.split("\\|");
                String question = values[0];
                String[] options = Arrays.copyOfRange(values, 1, values.length);
                int answer = 0;
                questions.add( new Question(question,options,answer));
            }
        } catch (IOException e) {
            //log the exception
            e.printStackTrace();

        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    //log the exception
                }
            }
        }

    }

    public Question GenerateQuestion()
    {
        int index = random.nextInt(questions.size());
        if (index >= questions.size() )
        {
            currentQuestion = null;
        }else currentQuestion = questions.get(index);
        return currentQuestion;
    }

    public  Question GetCurrentQuestion()
    {
        return currentQuestion;
    }

}
