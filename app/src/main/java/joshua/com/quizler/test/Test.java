package joshua.com.quizler.test;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Joshua on 5/25/2016.
 */
public class Test {

    private ArrayList<Question> questions;
    private Random random;
    private Question currentQuestion;
    private String name;

    public Test(String Name, ArrayList<Question> Questions)
    {
        name = Name;
        questions = Questions;
        random = new Random();
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

    public Question GetCurrentQuestion()
    {
        return currentQuestion;
    }

    public String getName()
    {
        return name;
    }
}
