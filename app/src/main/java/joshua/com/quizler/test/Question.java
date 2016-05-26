package joshua.com.quizler.test;

import java.util.ArrayList;
import java.util.List;
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
        options = ProcessOptions(Options);
        answer = Answer;
        random = new Random();
        Refresh();
    }

    // Remove duplicates from options
    private String[] ProcessOptions(String[] Options)
    {
        ArrayList<String> list = new ArrayList<String>();
        for (String string: Options) {
            if(!list.contains(string)) list.add(string);
        }
        return list.toArray(new String[0]);
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

    public String getStringAnswer()
    {
        return options[answer];
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
