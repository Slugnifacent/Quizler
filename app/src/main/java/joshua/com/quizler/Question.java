package joshua.com.quizler;

import java.util.Random;

/**
 * Created by Joshua on 5/18/2016.
 */
public class Question {
    private String question;
    private String[] options;
    private int answer;
    private Random random;
    private boolean markedCorrect;

    public Question(String Question, String[] Options, int Answer)
    {
        question = Question;
        options = Options;
        answer = Answer;
        random = new Random();
        Refresh();
    }

    private void shuffleQuestions(int Times)
    {
        for(int index = 0; index < Times; index++)
        {
            shuffleQuestions();
        }
    }

    private void shuffleQuestions()
    {
        for(int index = 0; index < options.length; index++)
        {
            int selectIndex = random.nextInt(options.length);

            //swap answer index
            if(index == answer) answer = selectIndex;
            else if(selectIndex == answer) answer = index;

            // swap index contents
            String temp = options[index];
            options[index] = options[selectIndex];
            options[selectIndex] = temp;

        }
    }

    public String getQuestion()
    {
        return question;
    }

    public String[] getOptions()
    {
        return options;
    }

    public int getAnswer()
    {
        return answer;
    }

    public void MarkWrong()
    {
        markedCorrect = false;
    }

    public boolean isMarkedCorrect()
    {
        return markedCorrect;
    }

    public boolean isMarkedWrong()
    {
        return !markedCorrect;
    }

    public void Refresh()
    {
        markedCorrect = true;
        shuffleQuestions(2);
    }
}
