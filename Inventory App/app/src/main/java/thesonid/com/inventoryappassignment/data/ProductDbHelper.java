package thesonid.com.inventoryappassignment.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import thesonid.com.inventoryappassignment.data.ProductContract.StockEntry;


public class ProductDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "inventory.db";
    private static final int DATABASE_VERSION = 1;

    public ProductDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_PETS_TABLE =  "CREATE TABLE " + StockEntry.TABLE_NAME + " ("
                + StockEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + StockEntry.COLUMN_CODE + " TEXT NOT NULL, "
                + StockEntry.COLUMN_SUPPLIER + " TEXT, "
                + StockEntry.COLUMN_PICTURE + " TEXT, "
                + StockEntry.COLUMN_QTY + " INTEGER, "
                + StockEntry.COLUMN_PRICE + " REAL NOT NULL DEFAULT 0.00, "
                + StockEntry.COLUMN_NAME + " TEXT);";

        db.execSQL(SQL_CREATE_PETS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Still at version 1, no upgrade required
    }
}
