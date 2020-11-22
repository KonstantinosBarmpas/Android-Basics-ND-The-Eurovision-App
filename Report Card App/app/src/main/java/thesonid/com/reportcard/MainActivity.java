package thesonid.com.reportcard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //An ArrayList to have all grades of this particular student.
        ArrayList<Subject> subjects = new ArrayList<Subject>();

        //Examples of some subject objects.
        subjects.add(new Subject("Maths",100));
        subjects.add(new Subject("History",60));
        subjects.add(new Subject("Geography",30));
        subjects.add(new Subject("Physics",100));
        subjects.add(new Subject("Chemistry",75));
        subjects.add(new Subject("Biology",70));
        subjects.add(new Subject("Economics",90));
        subjects.add(new Subject("Literature",68));
        subjects.add(new Subject("Philosophy",50));
        subjects.add(new Subject("Software Engineering",100));
        subjects.add(new Subject("Religion",75));
        subjects.add(new Subject("English",70));
        subjects.add(new Subject("German",90));
        subjects.add(new Subject("Art",68));
        subjects.add(new Subject("Music",50));

       //Create an ReportCard object.
        ReportCard Student = new ReportCard("Konstantinos", "21/02/97", subjects);

        //Setting the texts using the Student object.
        TextView nameView = (TextView) findViewById(R.id.name);
        nameView.setText(Student.getName());

        TextView dobView = (TextView) findViewById(R.id.dob);
        dobView.setText(Student.getDOB());

        TextView overallView = (TextView) findViewById(R.id.overall);
        overallView.setText(String.valueOf(Student.getGradeOverall()));

        SubjectAdapter adapter = new SubjectAdapter (this,subjects);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);

    }
}
