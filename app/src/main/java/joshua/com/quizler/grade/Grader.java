package joshua.com.quizler.grade;

import joshua.com.quizler.MainActivity;
import joshua.com.quizler.UI;
import joshua.com.quizler.test.Question;
import joshua.com.quizler.util.Storage;
import joshua.com.quizler.test.Test;
import joshua.com.quizler.util.XLogger;
import joshua.com.quizler.util.XUI;

/**
 * Created by Joshua on 5/18/2016.
 */
public class Grader {
    private Test test;
    private final XLogger logger = new XLogger(Grader.class);

    public Grader (Test test)
    {
        this.test = test;
        RetrieveGrades(test);
    }

    public void ChangeTest(Test test)
    {
        this.test = test;
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
                test.AdjustGrade(1, 1);
                saveGrade(test);
            }
            currentQuestion.Refresh();
            result = true;
            XUI.Toast("Correct");

            return result;
        }

        if(!currentQuestion.isMarkedWrong()) {
            currentQuestion.MarkWrong();
            test.AdjustGrade(0,1);
            saveGrade(test);
        }
        XUI.Toast("Wrong");
        return result;
    }

    private void RetrieveGrades(Test test)
    {
        String testName = test.getName();
        test.ResetGrade();
        int points = Storage.instance().retrieveFromPreferences(testName + GradeComponent.Points);
        int total  = Storage.instance().retrieveFromPreferences(testName + GradeComponent.Total);
        test.AdjustGrade(points, total);
    }

    private void saveGrade(Test test)
    {
        String testName = test.getName();

        Storage.instance().saveToPreferences(
                testName + GradeComponent.Points,
                test.getGrade().getPoints());

        Storage.instance().saveToPreferences(
                testName+GradeComponent.Total,
                test.getGrade().getTotal());
    }

    public void ResetGrade()
    {
        test.ResetGrade();
    }

}
