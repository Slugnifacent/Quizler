package joshua.com.quizler;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import joshua.com.quizler.grade.Grader;
import joshua.com.quizler.test.Question;
import joshua.com.quizler.test.Test;
import joshua.com.quizler.test.TestFactory;
import joshua.com.quizler.util.Storage;

public class MainActivity extends AppCompatActivity {

    private static TestFactory test;
    private static Grader grader;
    private static Test currentTest;

    private static Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Storage.instance().init(this);
        test = new TestFactory(this);
        currentTest = test.GetTest("joshuaiscool");
        grader = new Grader(currentTest);
        UI.SetActivity(this);
        UI.QuizLayout();
        context = this;
    }

    public void onClick(View view) {
        UI.onClick(view);
    }

    public static Test GetTest(String TestName)
    {
        return test.GetTest(TestName);
    }

    public static void SetTest(String TestName)
    {
        currentTest = test.GetTest(TestName);
        grader = new Grader(currentTest);
    }

    public static String GetGrade(Test test)
    {
        return grader.RetrieveGrade(test);
    }

    public static String[] GetTestNames()
    {
        return test.GetTestNames();
    }

    public static boolean GradeQuestion(int Answer)
    {
        return grader.GradeAnswerToCurrentQuestion(Answer);
    }

    public static void ResetGrade()
    {
        grader.ResetGrade();
    }

    public static Question GenerateQuestion()
    {
        if(currentTest == null) return null;
        return currentTest.GenerateQuestion();
    }

    public Activity getContext()
    {
        return this;
    }
}
