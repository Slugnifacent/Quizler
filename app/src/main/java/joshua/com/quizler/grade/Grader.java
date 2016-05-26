package joshua.com.quizler.grade;

import joshua.com.quizler.MainActivity;
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
        RetrieveGrades();
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

            }else
            {
                // Do Something before leaving Right Answer
                // Add to correct Points
                AdjustGrade(1,1);
            }
            currentQuestion.Refresh();
            result = true;
            MainActivity.Toast("Correct");

            saveGrade();
            return result;
        }

        if(!currentQuestion.isMarkedWrong()) {
            currentQuestion.MarkWrong();
            AdjustGrade(0,1);
        }
        MainActivity.Toast("Wrong");
        return result;
    }

    private void AdjustGrade(int Points, int Total)
    {
        grade.AddPoints(Points);
        grade.IncreaseTotal(Total);
    }

    public void RetrieveGrades()
    {
        int points = Storage.instance().retrieveFromPreferences(GradeComponent.Points);
        int total  = Storage.instance().retrieveFromPreferences(GradeComponent.Total);
        grade = new Grade(points,total);
    }

    public void saveGrade()
    {
        Storage.instance().saveToPreferences(GradeComponent.Points,grade.getPoints());
        Storage.instance().saveToPreferences(GradeComponent.Total,grade.getTotal());
    }

    public String RetrieveGrade()
    {
        String grades = String.format("Letter Grade:  %s \n" +
                                      "Percentage:    %f \n" +
                                      "Correct/Total: %s",
                grade.GetLetterGrade(),
                grade.Percentage(),
                grade.GetGradeRatio());
        return grades;
    }

    public void ResetGrade()
    {
        grade.ResetGrade();
    }

}
