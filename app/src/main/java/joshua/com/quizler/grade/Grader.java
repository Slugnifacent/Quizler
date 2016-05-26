package joshua.com.quizler.grade;

import joshua.com.quizler.MainActivity;
import joshua.com.quizler.UI;
import joshua.com.quizler.test.Question;
import joshua.com.quizler.util.Storage;
import joshua.com.quizler.test.Test;
import joshua.com.quizler.util.XLogger;

/**
 * Created by Joshua on 5/18/2016.
 */
public class Grader {


    private Test test;
    private Grade grade;
    private final XLogger logger = new XLogger(Grader.class);

    public Grader (Test test)
    {
        this.test = test;
        RetrieveGrades(test);
    }

    public boolean GradeAnswerToCurrentQuestion(int Answer)
    {
        boolean result = false;
        Question currentQuestion = test.GetCurrentQuestion();
        if(currentQuestion.getAnswer() == Answer)
        {
            if(currentQuestion.isMarkedWrong())
            {
                // Do Something before Leaving incorrect Answer
                test.SaveMissedQuestion(currentQuestion);
            }else
            {
                // Do Something before leaving Right Answer
                // Add to correct Points
                AdjustGrade(1,1);
            }
            currentQuestion.Refresh();
            result = true;
            UI.Toast("Correct");
            saveGrade(test);
            return result;
        }

        if(!currentQuestion.isMarkedWrong()) {
            currentQuestion.MarkWrong();
            AdjustGrade(0,1);
        }
        UI.Toast("Wrong");
        return result;
    }

    private void AdjustGrade(int Points, int Total)
    {
        grade.AddPoints(Points);
        grade.IncreaseTotal(Total);
    }

    private void RetrieveGrades(Test test)
    {
        String testName = test.getName();
        int points = Storage.instance().retrieveFromPreferences(testName+GradeComponent.Points);
        int total  = Storage.instance().retrieveFromPreferences(testName + GradeComponent.Total);
        grade = new Grade(points,total);
    }

    private  void saveGrade(Test test)
    {
        String testName = test.getName();
        Storage.instance().saveToPreferences(testName+GradeComponent.Points,grade.getPoints());
        Storage.instance().saveToPreferences(testName+GradeComponent.Total,grade.getTotal());
    }

    public String RetrieveGrade(Test test)
    {
        String testName = test.getName();
        int points = Storage.instance().retrieveFromPreferences(testName+GradeComponent.Points);
        int total  = Storage.instance().retrieveFromPreferences(testName + GradeComponent.Total);
        return new Grade(points,total).toString();
    }

    public void ResetGrade()
    {
        grade.ResetGrade();
    }

}
