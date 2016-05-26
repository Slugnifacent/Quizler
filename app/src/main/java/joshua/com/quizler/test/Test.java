package joshua.com.quizler.test;

import java.util.ArrayList;
import java.util.Random;

import joshua.com.quizler.grade.Grade;

/**
 * Created by Joshua on 5/25/2016.
 */
public class Test {

    private ArrayList<Question> questions;
    private Random random;
    private Question currentQuestion;
    private String name;
    private Grade grade;
    private ArrayList<Question> missed;

    public Test(String Name, ArrayList<Question> Questions)
    {
        name = Name;
        questions = Questions;
        random = new Random();
        missed = new ArrayList<Question>();
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

    public void SaveMissedQuestion(Question question)
    {
        if(!missed.contains(question))
        {
            missed.add(question);
        }
    }

    public String[] GetMissedQuestions()
    {
        ArrayList<String> missedQuestions = new ArrayList<String>();
        String temp;
        for(Question question: missed)
        {
            temp = String.format("%s \n <Answer> %s",question.getQuestion(),question.getStringAnswer());
            missedQuestions.add(temp);
        }
        return  missedQuestions.toArray(new String[0]);
    }

    public String getName()
    {
        return name;
    }
}
