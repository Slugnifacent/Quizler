package joshua.com.quizler;

/**
 * Created by Joshua on 5/18/2016.
 */
public class Grade {
    int correct;
    int total;

    public Grade(int Correct, int Total)
    {
        correct = Correct;
        total = Total;
    }

    public float Percentage()
    {
        if (total == 0) return 0;
        return (float)correct/(float)total;
    }

    public String GetLetterGrade()
    {
        float percentage = Percentage();
        if(percentage >= 90)
        {
            return LetterGradeHelper(percentage,90,"A");
        }
        if(percentage >= 80)
        {
            return LetterGradeHelper(percentage,80,"B");
        }
        if(percentage >= 70)
        {
            return LetterGradeHelper(percentage,70,"C");
        }
        return "F";
    }

    private String LetterGradeHelper(float percentage,int Floor,String LetterGrade)
    {
        StringBuilder grade = new StringBuilder(LetterGrade);
        if(percentage >= Floor + 7)
        {
            grade.append('+');
        }else if(percentage >= Floor + 3)
        {
             // Do nothing
        }else if(percentage >= Floor)
        {
            grade.append('-');
        }
        return grade.toString();
    }
}
