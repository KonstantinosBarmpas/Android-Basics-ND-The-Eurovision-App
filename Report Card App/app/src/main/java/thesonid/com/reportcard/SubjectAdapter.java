package thesonid.com.reportcard;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by user on 27/5/17.
 */

public class SubjectAdapter extends ArrayAdapter<Subject> {

    public SubjectAdapter(Activity context, ArrayList<Subject> subjects) {
        super(context, 0, subjects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.subjects_grades, parent, false);
        }
        Subject currentSubject = getItem(position);

        TextView subjectTextView = (TextView) listItemView.findViewById(R.id.subject);
        subjectTextView.setText(currentSubject.getSubjectName());

        TextView gradeTextView = (TextView) listItemView.findViewById(R.id.grade);
        gradeTextView.setText(String.valueOf(currentSubject.getSubjectGrade()));

        return listItemView;
    }
}






