package joshua.com.quizler;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    static Test test;
    static Grader grader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        test = new Test(this,"questions.txt");
        grader = new Grader(test);
        UI.SetActivity(this);
        UI.QuizLayout();
    }

    public void onClick(View view) {
        UI.onClick(view);
    }

    public static boolean GradeQuestion(int Answer)
    {
        return grader.GradeAnswerToCurrentQuestion(Answer);
    }

    public static Question GenerateQuestion()
    {
        if(test == null) return null;
        return test.GenerateQuestion();
    }

}
