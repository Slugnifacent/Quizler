package joshua.com.quizler;

/**
 * Created by Joshua on 5/18/2016.
 */
public class Grade {
    private int points;
    private int total;

    public Grade(int Points, int Total)
    {
        points = Points;
        total = Total;
    }

    public void ResetGrade()
    {
        points = 0;
        total = 0;
    }

    public void AddPoints(int Amount)
    {
        points += Amount;
    }

    public void IncreaseTotal(int Amount)
    {
        total += Amount;
    }

    public float Percentage()
    {
        if (total == 0) return 0;
        return (float)points/(float)total * 100;
    }

    public String GetGradeRatio()
    {
        String grades = String.format("%d/%d", points, total);
        return grades;
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

    private String LetterGradeHelper(float percentage,float Floor,String LetterGrade)
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
