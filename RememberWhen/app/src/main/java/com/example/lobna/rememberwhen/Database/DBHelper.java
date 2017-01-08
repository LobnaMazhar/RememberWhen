package com.example.lobna.rememberwhen.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.lobna.rememberwhen.Database.DBContract.*;

/**
 * Created by Lobna on 07-Jan-17.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "rememberWhen.db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        createMemoriesTable(sqLiteDatabase);
    }

    private void createMemoriesTable(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + MemoryTable.TABLE_NAME + " ( " +
                MemoryTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                MemoryTable.DESCRIPTION + " TEXT NOT NULL UNIQUE, " +
                MemoryTable.DATE + " DATE NOT NULL " +
                ");";

        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // TODO implement when needed.
    }
}
