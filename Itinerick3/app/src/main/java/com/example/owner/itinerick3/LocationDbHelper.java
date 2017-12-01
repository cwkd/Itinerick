
package com.example.owner.itinerick3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class LocationDbHelper extends SQLiteOpenHelper {

    private final Context context;
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase sqLiteDatabase;
    final String SQL_CREATE_TABLE = "CREATE TABLE LocationRecord ( _ID INTEGER PRIMARY KEY AUTOINCREMENT, Location TEXT NOT NULL, Keywords TEXT NOT NULL );";
    final String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS LocationRecord";

    LocationDbHelper(Context context) {
        super(context, LocationContract.LocationEntry.TABLE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_TABLE);
        onCreate(sqLiteDatabase);
    }
}
