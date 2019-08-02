package com.example.testappofferwall.Base;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashMap;

public class SQLiteHandler extends SQLiteOpenHelper {

    private static final String TAG = SQLiteHandler.class.getSimpleName();

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "testofferwall";

    private static final String TABLE_ALLOW = "allowTable";

    private static final String KEY_ID = "id";
    private static final String KEY_ALLOW = "allow";



    public SQLiteHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ALLOW_TABLE = "CREATE TABLE " + TABLE_ALLOW + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+ KEY_ALLOW + " INTEGER NOT NULL UNIQUE"+ ")";

        db.execSQL(CREATE_ALLOW_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ALLOW);

        onCreate(db);
    }

    public void addAllowValue(Integer allow) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ALLOW, allow);

        long id = db.insert(TABLE_ALLOW, null, values);
        db.close();
        Log.d(TAG, "Allow value inserted into sqlite: id = " + id + " value = " + allow);
    }

    public HashMap<String, Integer> getAllowValue() {
        HashMap<String, Integer> valueAllow = new HashMap<String, Integer>();
        String selectQuery = "SELECT * FROM " + TABLE_ALLOW;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            valueAllow.put("allow_value", cursor.getInt(1));
        }
        cursor.close();
        db.close();
        Log.d(TAG, "Fetching allow_value from Sqlite: " + valueAllow.toString());

        return valueAllow;
    }

    public void deleteAllowValue() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ALLOW, null, null);
        db.close();

        Log.d(TAG, "Deleted allow attribute");
    }

}
