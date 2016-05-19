package joshua.com.quizler;

/**
 * Created by Joshua on 5/18/2016.
 */
public class Grader {
    Test test;
    Grade grade;

    public Grader (Test test)
    {
        this.test = test;
        grade = new Grade(0,0);
    }

    public boolean GradeAnswerToCurrentQuestion(int Answer)
    {
        boolean result = false;
        Question currentQuestion = test.GetCurrentQuestion();
        if(currentQuestion.getAnswer() == Answer)
        {
            if(currentQuestion.isMarkedWrong())
            {
                // Do not add to correct Points
                AdjustGrade(0,1);
            }else
            {
                // Add to correct Points
                AdjustGrade(1,1);
            }
            currentQuestion.Refresh();
            result = true;
        }
        currentQuestion.MarkWrong();
        return result;
    }

    private void AdjustGrade(int Points, int Total)
    {
        grade.AddPoints(Points);
        grade.IncreaseTotal(Total);
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
