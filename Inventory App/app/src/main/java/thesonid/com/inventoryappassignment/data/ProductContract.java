package thesonid.com.inventoryappassignment.data;

import android.net.Uri;
import android.provider.BaseColumns;

public final class ProductContract {

    public static final String CONTENT_AUTHORITY = "thesonid.com.inventoryappassignment";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_STOCK = "stock";

    private ProductContract() {}

    public static final class StockEntry implements BaseColumns {
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_STOCK);
        public final static String TABLE_NAME = "stock";
        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_CODE = "sku";
        public final static String COLUMN_SUPPLIER = "supplier";
        public final static String COLUMN_NAME = "name";
        public final static String COLUMN_QTY = "qty";
        public final static String COLUMN_PICTURE = "picture";
        public final static String COLUMN_PRICE = "price";
    }
}
