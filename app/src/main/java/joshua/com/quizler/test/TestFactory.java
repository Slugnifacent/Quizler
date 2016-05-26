package joshua.com.quizler.test;

import android.app.Activity;
import android.os.Environment;
import android.support.v4.content.ContextCompat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

import joshua.com.quizler.R;
import joshua.com.quizler.util.Storage;

/**
 * Created by Joshua on 5/18/2016.
 */
public class TestFactory
{
    ArrayList<Test> tests;

    public TestFactory(Activity activity)
    {
        tests = new ArrayList<Test>();
        GetInternalQuestions(activity);
        GetExternalQuestions(activity);
    }

    public String[] GetTestNames()
    {
        ArrayList<String> testnames = new ArrayList<String>();
        for(Test test : tests)
        {
            testnames.add(test.getName());
        }
        return testnames.toArray(new String[0]);
    }


    public Test GetTest(String Name)
    {
        for(Test test:tests)
        {
            if(test.getName().compareTo(Name) == 0)
            {
                return test;
            }
        }
        return null;
    }

    private ArrayList<Integer> GetRawIDs(Activity activity)
    {
        ArrayList<Integer> list = new ArrayList<Integer>();
        Field[] fields=R.raw.class.getFields();
        for(int count=0; count < fields.length; count++){
            String name = fields[count].getName();
            int id = activity.getResources().getIdentifier(name, "raw", activity.getPackageName());
            list.add(id);
        }
        return list;
    }

    private void GetExternalQuestions(Activity activity)
    {
        if(Storage.instance().isExternalStorageReadable()) {
            File[] temp = ContextCompat.getExternalFilesDirs(activity, null);

            for(File tempFile : temp) {
                File f = new File(tempFile, "Tests");
                if (!f.exists()) {
                    f.mkdirs();
                } else {
                    // Load Files
                    File[] files = f.listFiles();
                    for (File file : files) {
                        FileInputStream fIn = null;
                        try {
                            fIn = new FileInputStream(file);
                            ArrayList<Question> questions = GetQuestions(fIn);
                            if (questions.size() > 0) {
                                String[] matches = file.getName().split("\\.");
                                tests.add(new Test(matches[0], questions));
                            }
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    private void GetInternalQuestions(Activity activity)
    {
        ArrayList<Integer> ids = GetRawIDs(activity);
        for (int id: ids) {
            InputStream input = activity.getResources().openRawResource(id);
            ArrayList<Question> questions = GetQuestions(input);
            if(questions.size() > 0)
            {
                tests.add(new Test(activity.getResources().getResourceEntryName (id),questions));
            }
        }
    }

    private ArrayList<Question> GetQuestions(InputStream input)
    {
        ArrayList<Question> questions = new ArrayList<Question>();
        BufferedReader reader = null;
        try {

            reader = new BufferedReader(
                    new InputStreamReader(input, "UTF8"));

            // do reading, usually loop until end of file reading
            String mLine;
            while ((mLine = reader.readLine()) != null) {
                String[] values = mLine.split("\\|");
                String question = values[0];
                String[] options = Arrays.copyOfRange(values, 1, values.length);
                int answer = 0;
                questions.add( new Question(question,options,answer));
            }
        } catch (IOException e) {
            //log the exception
            e.printStackTrace();

        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    //log the exception
                }
            }
        }
        return questions;
    }
}
