package joshua.com.quizler;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import joshua.com.quizler.grade.Grader;
import joshua.com.quizler.test.Question;
import joshua.com.quizler.test.Test;
import joshua.com.quizler.test.TestFactory;
import joshua.com.quizler.util.Storage;
import joshua.com.quizler.util.XUI;

public class MainActivity extends AppCompatActivity {

    private static TestFactory testFactory;
    private static Grader grader;
    private static Test currentTest;

    private static Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Storage.instance().init(this);
        testFactory = new TestFactory(this);
        currentTest = testFactory.GetTest("joshuaiscool");
        grader = new Grader(currentTest);
        XUI.SetActivity(this);
        UI.SetActivity(this);
        UI.QuizLayout();
        context = this;
    }

    public void onClick(View view) {
        UI.onClick(view);
    }

    public static Test GetCurrentTest()
    {
        return currentTest;
    }

    public static Test GetTest(String TestName)
    {
        return testFactory.GetTest(TestName);
    }

    public static void ResetTest(Test test)
    {
        testFactory.ResetTest(test);
        testFactory.SaveGrades();
    }

    public static void SetTest(String TestName)
    {
        currentTest = testFactory.GetTest(TestName);
        grader.ChangeTest(currentTest);
    }

    public static String GetGrade(Test test)
    {
        return testFactory.RetrieveGrade(test);
    }

    public static String[] GetTestNames()
    {
        return testFactory.GetTestNames();
    }

    public static String[] GetMissedQuestions(Test test)
    {
        return test.GetMissedQuestions();
    }


    public static boolean GradeQuestion(int Answer)
    {
        return grader.GradeAnswerToCurrentQuestion(Answer);
    }

    public static void ResetAll(){
        testFactory.ResetTests();
        testFactory.SaveGrades();
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
