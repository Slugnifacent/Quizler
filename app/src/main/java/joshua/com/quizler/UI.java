package joshua.com.quizler;

import android.app.ActionBar;
import android.app.Activity;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import joshua.com.quizler.test.Question;
import joshua.com.quizler.test.Test;

/**
 * Created by Joshua on 5/18/2016.
 */
public class UI {
    private static Activity  activity;

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
                ResetAll();
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

            final ListView listView = (ListView) activity.findViewById(R.id.list_results);

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
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Test tempTest = MainActivity.GetTest(TestNames[position]);
                    ShowMissedQuestions(tempTest);
                }
            });
    }

    private static void ShowMissedQuestions(final Test test)
    {
        activity.setContentView(R.layout.missed_questions_layout);
        ListView listView = (ListView) activity.findViewById(R.id.list_missed_questions);

        TextView text = (TextView) activity.findViewById(R.id.missed_questions_test_name);
        text.setText(test.getName());

        TextView stats = (TextView) activity.findViewById(R.id.missed_questions_stats);
        stats.setText(test.getGrade().toString());

        Button resetButton = (Button) activity.findViewById(R.id.button_individual_reset);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResetTest(test);
                ShowMissedQuestions(test);
            }
        });


        final String[] missedQuestions = test.GetMissedQuestions();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity,
                android.R.layout.simple_list_item_1, missedQuestions);

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

    private static void ResetTest(Test test)
    {
        MainActivity.ResetTest(test);
    }

    private static void ResetAll()
    {
        MainActivity.ResetAll();
        StatsLayout();
    }

}
