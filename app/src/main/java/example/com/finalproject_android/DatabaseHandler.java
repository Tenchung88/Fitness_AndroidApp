package example.com.finalproject_android;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "fitnesstable.db";
    private static final int DATABASE_VERSION = 1;
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase database) { TrainerTableHandler.onCreate(database);
    }
    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        TrainerTableHandler.onUpgrade(database, oldVersion, newVersion);
    }
}


