package thesonid.com.habittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import thesonid.com.habittracker.data.HabitContract;
import thesonid.com.habittracker.data.HabitDbHelper;

public class MainActivity extends AppCompatActivity {

    EditText mNameEditText;
    EditText mRepEditText;
    private HabitDbHelper mDbHelper = new HabitDbHelper(this);
    Button add;
    Button display;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNameEditText = (EditText) findViewById(R.id.name);
        mRepEditText = (EditText) findViewById(R.id.repetitions);
        add = (Button) findViewById(R.id.add);
        display = (Button) findViewById(R.id.display);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertHabit();
            }
        });

        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cursor=createCursor();
                display();
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        cursor=createCursor();
        display();
    }


    private Cursor createCursor() {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();


        String[] project = {
                HabitContract.HabitEntry._ID,
                HabitContract.HabitEntry.COLUMN_HABIT_NAME,
                HabitContract.HabitEntry.COLUMN_REPETITIONS,
        };

        cursor = db.query(
                HabitContract.HabitEntry.TABLE_NAME,
                project,
                null,
                null,
                null,
                null,
                null
        );

        return cursor;
    }

    private void display(){
        TextView displayView = (TextView) findViewById(R.id.text_view_pet);

        try {

            displayView.setText("The habits table contains " + cursor.getCount() + " habits.\n\n");
            displayView.append(HabitContract.HabitEntry._ID + " - " +
                    HabitContract.HabitEntry.COLUMN_HABIT_NAME + " - " +
                    HabitContract.HabitEntry.COLUMN_REPETITIONS + "\n");

            int idColumnIndex = cursor.getColumnIndex(HabitContract.HabitEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(HabitContract.HabitEntry.COLUMN_HABIT_NAME);
            int repColumnIndex = cursor.getColumnIndex(HabitContract.HabitEntry.COLUMN_REPETITIONS);

            while (cursor.moveToNext()) {
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                String currentRep = cursor.getString(repColumnIndex);

                displayView.append(("\n" + currentID + " - " +
                        currentName + " - " +
                        currentRep));
            }
        } finally {
            cursor.close();
        }
    }

    private void insertHabit() {

        int repetitionsInt = -1;
        String nameString = mNameEditText.getText().toString().trim();
        if (!mRepEditText.getText().toString().trim().isEmpty()) {
            repetitionsInt = Integer.parseInt(mRepEditText.getText().toString().trim());
        }

        if (!nameString.isEmpty() && repetitionsInt != -1) {
            SQLiteDatabase db = mDbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(HabitContract.HabitEntry.COLUMN_HABIT_NAME, nameString);
            values.put(HabitContract.HabitEntry.COLUMN_REPETITIONS, repetitionsInt);
            long newRowId = db.insert(HabitContract.HabitEntry.TABLE_NAME, null, values);

            if (newRowId == -1) {
                Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Habit saved with id:" + newRowId, Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(this, "Please add a habit", Toast.LENGTH_LONG).show();
        }
    }

}
