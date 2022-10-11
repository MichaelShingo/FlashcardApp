package com.michaelshingo.flashcards;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {


    public static final String FLASHCARD_TABLE = "FLASHCARD_TABLE";
    public static final String COLUMN_TERM = "TERM";
    public static final String COLUMN_DEFINITION = "DEFINITION";
    public static final String COLUMN_ID = "ID";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "flashcard.db", null, 1);
    }

    //for first time you try to access the database, must create new database here
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + FLASHCARD_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_TERM + " STRING, " + COLUMN_DEFINITION + " STRING)";
        db.execSQL(createTableStatement);

    }

    //this is called if the database version number changes, for old users, It prevents previous users apps from breaking when you change the database design
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addOne(Flashcard flashcard){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues(); //associative array, pairs of values
        // you don't need ID because it's auto-increment
        cv.put(COLUMN_TERM, flashcard.getTerm());
        cv.put(COLUMN_DEFINITION, flashcard.getDefinition());
        long insert = db.insert(FLASHCARD_TABLE, null, cv);//null column hack has to do with inserting empty rows...which you can't have in SQL
        if (insert == -1){
            return false;
        }
        else {
            return true;
        }
    }
}
