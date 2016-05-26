package joshua.com.quizler.test;

import java.util.ArrayList;
import java.util.Random;

import joshua.com.quizler.grade.Grade;
import joshua.com.quizler.util.XRandom;

/**
 * Created by Joshua on 5/25/2016.
 */
public class Test {

    private Question currentQuestion;
    private ArrayList<String> missedQuestions;
    private ArrayList<Question> questions;
    private String name;
    private Grade grade;


    public Test(String Name, ArrayList<Question> Questions)
    {
        name = Name;
        questions = Questions;
        missedQuestions = new ArrayList<String>();
        grade = new Grade(0,0);
    }

    public Question GenerateQuestion()
    {
        int index = XRandom.nextInt(questions.size());
        currentQuestion = questions.get(index);
        return currentQuestion;
    }

    public Question GetCurrentQuestion()
    {
        return currentQuestion;
    }

    public void SaveMissedQuestion(Question question)
    {
        String formattedQuestion = String.format(
                "%s\n<Answer> %s",
                question.getQuestion(),
                question.getStringAnswer());

        if(!missedQuestions.contains(formattedQuestion)) {
            missedQuestions.add(formattedQuestion);
        }
    }

    public String[] GetMissedQuestions()
    {
        return  missedQuestions.toArray(new String[0]);
    }

    public String getName()
    {
        return name;
    }

    public Grade getGrade()
    {
        return grade;
    }

    public void ResetGrade()
    {
        grade.ResetGrade();
    }

    public void AdjustGrade(int Points, int Total)
    {
        grade.AddPoints(Points);
        grade.IncreaseTotal(Total);
    }
}
