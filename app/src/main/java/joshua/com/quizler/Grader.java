package joshua.com.quizler;

/**
 * Created by Joshua on 5/18/2016.
 */
public class Grader {
    Test test;

    public Grader (Test test)
    {
        this.test = test;
    }

    public boolean GradeAnswerToCurrentQuestion(int Answer)
    {
        Question question = test.GetCurrentQuestion();
        if(question.getAnswer() == Answer)
        {
            return true;
        }else return false;
    }

    private void LogResult()
    {

    }
}
