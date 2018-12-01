package example.com.finalproject_android;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class TrainerTableHandler {
    public static final String TABLE_TRAINER = "Trainer";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_SWIMMING = "swimming";
    public static final String COLUMN_RUNNING = "running";

    private static final String DATABASE_CREATE = "create table "
            + TABLE_TRAINER
            + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_NAME + " text not null, "
            + COLUMN_SWIMMING + " integer not null, "
            + COLUMN_RUNNING + " integer not null "
            + ");";

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        Log.w(TrainerTableHandler.class.getName(), "Upgrading database from version "
                + oldVersion + " to " + newVersion + ", which will destroy all old data");
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_TRAINER);
        onCreate(database);
    }

}
