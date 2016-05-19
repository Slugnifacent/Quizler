package joshua.com.quizler;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

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
        PostNextQuestion(question.getQuestion(), question.getOptions(), question.getAnswer());
    }

    public static void PassUserAnswerToGrader(int Answer)
    {
        if(MainActivity.GradeQuestion(Answer))
        {
            GenerateNextQuestion();
        }
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

            String[] grade = new String[1];
            grade[0] = MainActivity.GetGrade();


            ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity,
                    android.R.layout.simple_list_item_1, grade);

            listView.setAdapter(adapter);
    }

    private static void ResetStats()
    {
        MainActivity.ResetGrade();
        StatsLayout();
    }

}
