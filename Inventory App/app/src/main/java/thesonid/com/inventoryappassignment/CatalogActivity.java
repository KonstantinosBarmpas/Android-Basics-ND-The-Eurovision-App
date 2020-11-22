package thesonid.com.inventoryappassignment;

import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import thesonid.com.inventoryappassignment.data.ProductContract;

import static thesonid.com.inventoryappassignment.EditorActivity.LOG_TAG;

public class CatalogActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final int URL_LOADER = 0;

    SimpleCursorAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });

        String[] columns = {
                ProductContract.StockEntry.COLUMN_CODE,
                ProductContract.StockEntry.COLUMN_NAME,
                ProductContract.StockEntry.COLUMN_SUPPLIER,
                ProductContract.StockEntry.COLUMN_QTY,
                ProductContract.StockEntry.COLUMN_PICTURE,
                ProductContract.StockEntry.COLUMN_PRICE
        };
        int[] views = {
                R.id.sku_text,
                R.id.name_text,
                R.id.supplier_text,
                R.id.qty_text,
                R.id.picture,
                R.id.price_text
        };
        mAdapter = new SimpleCursorAdapter(this, R.layout.list_item, null, columns, views, 0) {
            @Override
            public void bindView(View view, Context context, final Cursor cursor) {
                super.bindView(view, context, cursor);
                Button button = (Button) view.findViewById(R.id.sale_button);
                button.setTag(cursor.getPosition());
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.d(LOG_TAG, getString(R.string.position_colon) + view.getTag());
                        sellStock(cursor, (int) view.getTag());
                    }
                });
            }
        };

        ListView stockListView = (ListView) findViewById(R.id.list_view);
        stockListView.setAdapter(mAdapter);

        View emptyView = findViewById(R.id.empty_view);
        stockListView.setEmptyView(emptyView);

        stockListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(getBaseContext(), EditorActivity.class);
                ActivityOptionsCompat options = ActivityOptionsCompat
                        .makeSceneTransitionAnimation(CatalogActivity.this, view, "stock_image");
                intent.setData(ContentUris.withAppendedId(ProductContract.StockEntry.CONTENT_URI, id));
                startActivity(intent, options.toBundle());
            }
        });

        getLoaderManager().initLoader(URL_LOADER, null, this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_all:
                new AlertDialog.Builder(this)
                        .setTitle(R.string.delete)
                        .setMessage(R.string.do_you_want_to_delete)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                getContentResolver().delete(ProductContract.StockEntry.CONTENT_URI, null, null);
                            }})
                        .setNegativeButton(android.R.string.no, null).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }

    private void sellStock(Cursor cursor, int position) {
        cursor.moveToPosition(position);
        ContentValues values = new ContentValues();
        int idIndex = cursor.getColumnIndex(ProductContract.StockEntry._ID);
        int qtyIndex = cursor.getColumnIndex(ProductContract.StockEntry.COLUMN_QTY);
        int qty = cursor.getInt(qtyIndex);
        int id = cursor.getInt(idIndex);

        if (--qty <= -1) {
            Toast.makeText(this, "Not enough stock", Toast.LENGTH_SHORT).show();
            return;
        }

        values.put(ProductContract.StockEntry.COLUMN_QTY, qty);
        Uri stockUrl = ContentUris.withAppendedId(ProductContract.StockEntry.CONTENT_URI, id);
        int rowsAffected = getContentResolver().update(stockUrl, values, null, null);
        Toast.makeText(this, rowsAffected + getString(R.string.item_has_been_sold), Toast.LENGTH_SHORT).show();
    }


    @Override
    public Loader<Cursor> onCreateLoader(int loaderID, Bundle bundle) {

        switch (loaderID) {
            case URL_LOADER:
                String[] projection = {
                        ProductContract.StockEntry._ID,
                        ProductContract.StockEntry.COLUMN_CODE,
                        ProductContract.StockEntry.COLUMN_NAME,
                        ProductContract.StockEntry.COLUMN_SUPPLIER,
                        ProductContract.StockEntry.COLUMN_QTY,
                        ProductContract.StockEntry.COLUMN_PICTURE,
                        ProductContract.StockEntry.COLUMN_PRICE
                };
                return new CursorLoader(
                        this,            // Parent activity context
                        ProductContract.StockEntry.CONTENT_URI, // Uri to query
                        projection,      // Projection to return
                        null,            // No selection clause
                        null,            // No selection arguments
                        null             // Default sort order
                );
            default:
                return null;
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        mAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }
}