package thesonid.com.habittracker.data;

import android.provider.BaseColumns;

/**
 * Created by user on 7/7/17.
 */

public class HabitContract {

    private HabitContract() {
    }

    public static final class HabitEntry implements BaseColumns {

        public final static String TABLE_NAME = "habits";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_HABIT_NAME = "name";
        public final static String COLUMN_REPETITIONS = "repetitions";

    }

}