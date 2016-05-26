package joshua.com.quizler;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.text.Format;
import java.util.ArrayList;

import joshua.com.quizler.test.Question;
import joshua.com.quizler.test.Test;

/**
 * Created by Joshua on 5/18/2016.
 */
public class UI {
    static Activity  activity;
    public static void SetActivity(Activity Context)
    {
        activity = Context;
    }

    public static void onClick(View view)
    {
        if(activity == null) return;
        int id = view.getId();
        switch(id)
        {
            case R.id.button_quiz:
                QuizLayout();
                break;
            case R.id.button_stats:
                StatsLayout();
                break;
            case R.id.button_tests:
                TestLayout();
                break;
            case R.id.button_reset:
                ResetStats();
                break;
            default:
                break;

        }
    }

    public static void QuizLayout() {
            activity.setContentView(R.layout.activity_main);
            GenerateNextQuestion();
    }

    public static void GenerateNextQuestion()
    {
        Question question = MainActivity.GenerateQuestion();
        if(question != null) {
            PostNextQuestion(question.getQuestion(), question.getOptions(), question.getAnswer());
        }
    }

    public static void PassUserAnswerToGrader(int Answer)
    {
        if(MainActivity.GradeQuestion(Answer))
        {
            GenerateNextQuestion();
        }
    }

    public static void ChangeTest(String TestName)
    {
        MainActivity.SetTest(TestName);
    }

    private static void PostNextQuestion(String Question,String[] Options,int Answer )
    {
        ListView listView = (ListView) activity.findViewById(R.id.list_answers);

        TextView textViewQuestion = (TextView) activity.findViewById(R.id.text_question);
        textViewQuestion.setText(Question);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity,
                android.R.layout.simple_list_item_1, Options);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PassUserAnswerToGrader(position);
            }
        });
    }

    private static void StatsLayout() {
            activity.setContentView(R.layout.stats_layout);

            ListView listView = (ListView) activity.findViewById(R.id.list_results);

            final String[] TestNames = MainActivity.GetTestNames();
            ArrayList<String> list = new ArrayList<String>();
            for(String testName : TestNames)
            {
                Test test = MainActivity.GetTest(testName);
                String grade = MainActivity.GetGrade(test);
                String formatted = String.format("%s\n %s", testName,grade);
                list.add(formatted);
            }

            String[] grades = list.toArray(new String[0]);

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity,
                    android.R.layout.simple_list_item_1, grades);
            listView.setAdapter(adapter);
    }

    private static void TestLayout() {

        activity.setContentView(R.layout.tests_layout);

        ListView listView = (ListView) activity.findViewById(R.id.list_tests);

        final String[] TestNames = MainActivity.GetTestNames();


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity,
                android.R.layout.simple_list_item_1, TestNames);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String answer = TestNames[position];
                ChangeTest(answer);
                QuizLayout();
            }
        });
    }

    public static void Toast(String Message)
    {
        if(activity != null) {
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(activity, Message, duration);
            toast.show();
        }
    }

    private static void ResetStats()
    {
        MainActivity.ResetGrade();
        StatsLayout();
    }

}
