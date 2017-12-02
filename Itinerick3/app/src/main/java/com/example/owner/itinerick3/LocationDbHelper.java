
package com.example.owner.itinerick3;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class LocationDbHelper extends SQLiteOpenHelper {

    private final Context context;
    private static final int DATABASE_VERSION = 1;
    final String SQL_CREATE_TABLE = "CREATE TABLE "
            + LocationContract.LocationEntry.TABLE_NAME
            + "( _ID INTEGER PRIMARY KEY AUTOINCREMENT, "
            + LocationContract.LocationEntry.COL_LOCATION
            + " TEXT NOT NULL PRIMARY KEY, "
            + LocationContract.LocationEntry.COL_KEYWORDS
            + " TEXT NOT NULL, "
            + LocationContract.LocationEntry.COL_DESCRIPTION
            + " TEXT NOT NULL, "
            + LocationContract.LocationEntry.COL_MAP_QUERY
            + " TEXT NOT NULL );";

    final String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS "
            + LocationContract.LocationEntry.TABLE_NAME;

    final String PATH = "";

    LocationDbHelper(Context context) {
        super(context, LocationContract.LocationEntry.TABLE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    public boolean checkForLocalDb() {
        SQLiteDatabase checkDB = null;
        try {
            checkDB = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.OPEN_READONLY);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (checkDB != null) {
            checkDB.close();
        }
        return checkDB != null;
    }

    public SQLiteDatabase getLocalDb() {
        return SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.OPEN_READONLY);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_DELETE_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //sqLiteDatabase.execSQL(SQL_DELETE_TABLE);
        onCreate(sqLiteDatabase);
    }
}
