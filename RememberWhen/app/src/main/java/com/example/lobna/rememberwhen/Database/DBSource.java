package com.example.lobna.rememberwhen.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.lobna.rememberwhen.Application.RememberWhenApplication;
import com.example.lobna.rememberwhen.Model.Memory;
import com.example.lobna.rememberwhen.Database.DBContract.*;

import java.util.ArrayList;

/**
 * Created by Lobna on 07-Jan-17.
 */

public class DBSource {

    private static DBSource instance;
    private DBHelper memoriesDBHelper;
    private SQLiteDatabase database;

    public static DBSource getInstance() {
        if (instance == null) {
            instance = new DBSource();
            instance.memoriesDBHelper = new DBHelper(RememberWhenApplication.getApp().getApplicationContext());
            instance.open();
        }
        return instance;
    }

    public void open() {
        database = memoriesDBHelper.getWritableDatabase();
    }

    public void close() {
        if (database.isOpen())
            database.close();
    }

    private ContentValues getContentValuesFromMemory(Memory memory) {
        ContentValues movieContentValues = new ContentValues();

        //movieContentValues.put(MemoryTable._ID, memory.getId());
        movieContentValues.put(MemoryTable.DESCRIPTION, memory.getDescription());
        movieContentValues.put(MemoryTable.DATE, memory.getDate());

        return movieContentValues;
    }

    private Memory getMemoryFromCursor(Cursor cursor) {
        Memory memory = new Memory();

        memory.setId(cursor.getInt(cursor.getColumnIndex(MemoryTable._ID)));
        memory.setDescription(cursor.getString(cursor.getColumnIndex(MemoryTable.DESCRIPTION)));
        memory.setDate(cursor.getString(cursor.getColumnIndex(MemoryTable.DATE)));

        return memory;
    }

    public boolean addMemory(Memory memory) {
        ContentValues contentValues = getContentValuesFromMemory(memory);

        long rowID = database.insert(MemoryTable.TABLE_NAME, null, contentValues);
        if (rowID != -1)
            return true;
        return false;
    }

    public boolean editMemory(Memory memory) {
        ContentValues contentValues = getContentValuesFromMemory(memory);

        int nRowsAffected = database.update(MemoryTable.TABLE_NAME, contentValues
                , MemoryTable._ID + " = ?", new String[]{Integer.toString(memory.getId())});
        if (nRowsAffected == 1)
            return true;
        return false;
    }

    public ArrayList<Memory> getMemories() {
        ArrayList<Memory> memories = new ArrayList<Memory>();

        Cursor cursor = database.query(MemoryTable.TABLE_NAME,
                null, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Memory memory = getMemoryFromCursor(cursor);
            memories.add(memory);
            cursor.moveToNext();
        }
        cursor.close();

        return memories;
    }

    public Memory getMemory(int ID) {
        Memory memory;

        Cursor cursor = database.query(MemoryTable.TABLE_NAME,
                null, MemoryTable._ID + " = ?", new String[]{String.valueOf(ID)}, null, null, null);

        cursor.moveToFirst();
        memory = getMemoryFromCursor(cursor);
        cursor.close();

        return memory;
    }

    public boolean deleteMemory(int ID) {
        int nRowsAffected = database.delete(MemoryTable.TABLE_NAME, MemoryTable._ID + " = ?", new String[]{String.valueOf(ID)});
        if (nRowsAffected == 1)
            return true;
        return false;
    }

    public Memory getRandomMemory() {
        ArrayList<Memory> allMemories = DBSource.getInstance().getMemories();
        if(allMemories.size() != 0) {
            int random = 0 + (int) (Math.random() * allMemories.size());
            return allMemories.get(random);
        }
        return null;
    }
}
