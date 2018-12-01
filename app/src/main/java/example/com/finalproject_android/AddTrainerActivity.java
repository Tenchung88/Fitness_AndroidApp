package example.com.finalproject_android;

import android.app.Activity;
import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class AddTrainerActivity extends ListActivity implements View.OnClickListener, LoaderManager.LoaderCallbacks<Cursor> {

    public static final String TAG = "MyFitnessTracker";
    private static final int ACTIVITY_CREATE = 0;
    private static final int ACTIVITY_EDIT = 1;
    private static final int DELETE_ID = Menu.FIRST + 1;
    private SimpleCursorAdapter adapter;
    private EditText Name;
    private EditText Swimming;
    private EditText Running;
    private Uri MyfitnessUri;
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.addtrainer);
        this.getListView().setDividerHeight(2);
        fillDatabase();
        registerForContextMenu(getListView());
        Name= (EditText) findViewById(R.id.name_id);
        Swimming = (EditText) findViewById(R.id.running_id);
        Running = (EditText) findViewById(R.id.swimming_id);
        Button Save = (Button) findViewById(R.id.save_id);

        Bundle extras = getIntent().getExtras();
        MyfitnessUri = (bundle == null) ? null : (Uri) bundle.getParcelable(MyContentProvider.CONTENT_ITEM_TYPE);
        if (extras != null) {
            MyfitnessUri = extras.getParcelable(MyContentProvider.CONTENT_ITEM_TYPE);
            fillData(MyfitnessUri);
        }

        Save.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if ((TextUtils.isEmpty(Name.getText().toString())) || (TextUtils.isEmpty(Swimming.getText().toString()))
                || (TextUtils.isEmpty(Running.getText().toString()))){
            makeToast();
        } else {
            setResult(RESULT_OK);



        }
    }
    private void fillDatabase() {

        String[] from = new String[] { TrainerTableHandler.COLUMN_NAME };
        int[] to = new int[] { R.id.label};

        getLoaderManager().initLoader(0, null, this);
        adapter = new SimpleCursorAdapter(this, R.layout.trainer_row, null, from, to, 0);//?????????????

        setListAdapter(adapter);
    }
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, DELETE_ID, 0, R.string.menu_delete);
    }
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case DELETE_ID:
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();//?????????
                Uri uri = Uri.parse(MyContentProvider.CONTENT_URI + "/" + info.id);
                getContentResolver().delete(uri, null, null);
                fillDatabase();
                return true;
        }
        return super.onContextItemSelected(item);
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) // when it called???????
    {
        super.onActivityResult(requestCode, resultCode, intent);
    }
    public Loader<Cursor> onCreateLoader(int id, Bundle args) //what does cursorloader do????
    {
        String[] projection = { TrainerTableHandler.COLUMN_ID, TrainerTableHandler.COLUMN_NAME };
        CursorLoader cursorLoader = new CursorLoader(this, MyContentProvider.CONTENT_URI, projection, null, null, null);
        return cursorLoader;
    }

    //@Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);
    }

    //@Override
    public void onLoaderReset(Loader<Cursor> loader) {
        // data is not available anymore, delete reference
        adapter.swapCursor(null);
    }

    private void fillData(Uri uri) {
        String[] projection = { TrainerTableHandler.COLUMN_NAME, TrainerTableHandler.COLUMN_SWIMMING, TrainerTableHandler.COLUMN_RUNNING};
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
//            String category = cursor.getString(cursor.getColumnIndexOrThrow(TrainerTableHandler.COLUMN_NAME));
//
//            for (int i = 0; i < desi.getCount(); i++) {
//
//                String s = (String) designation.getItemAtPosition(i);
//                if (s.equalsIgnoreCase(category)) {
//                    designation.setSelection(i);
//                }
//            }
            Name.setText(cursor.getString(cursor.getColumnIndexOrThrow(TrainerTableHandler.COLUMN_NAME)));
            Swimming.setText(cursor.getString(cursor.getColumnIndexOrThrow(TrainerTableHandler.COLUMN_SWIMMING)));
            Running.setText(cursor.getString(cursor.getColumnIndexOrThrow(TrainerTableHandler.COLUMN_RUNNING)));
//            String myprovince = cursor.getString(cursor.getColumnIndexOrThrow(AddressTableHandler.COLUMN_PROVINCE));
//            for (int i = 0; i < province.getCount(); i++) {
//
//                String s = (String) province.getItemAtPosition(i);
//                if (s.equalsIgnoreCase(myprovince)) {
//                    province.setSelection(i);
//                }
//            }
//
//            country.setText(cursor.getString(cursor.getColumnIndexOrThrow(AddressTableHandler.COLUMN_COUNTRY)));
//            postalcode.setText(cursor.getString(cursor.getColumnIndexOrThrow(AddressTableHandler.COLUMN_POSTALCODE)));
//
//            // Always close the cursor
            cursor.close();
        }
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        saveState();
        outState.putParcelable(MyContentProvider.CONTENT_ITEM_TYPE, MyfitnessUri);
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveState();
    }
    private void saveState() {

        String myname = Name.getText().toString();
        String mySwimming = Swimming.getText().toString();
        String myRunning = Running.getText().toString();


        if (myname.length() == 0 || mySwimming.length() == 0 || myRunning.length() ==0 ) {
            makeToast();
        }

        ContentValues values = new ContentValues();
        values.put(TrainerTableHandler.COLUMN_NAME, myname);
        values.put(TrainerTableHandler.COLUMN_SWIMMING, mySwimming);
        values.put(TrainerTableHandler.COLUMN_RUNNING, myRunning);



        if (MyfitnessUri == null) {
            // New ToDo
            MyfitnessUri = getContentResolver().insert(MyContentProvider.CONTENT_URI, values);
        } else {
            // Update ToDo
            getContentResolver().update(MyfitnessUri, values, null, null);
        }
    }

    private void makeToast() {
        Toast.makeText(AddTrainerActivity.this, "INCOMPLETE!! ",Toast.LENGTH_LONG).show();
    }


    }

