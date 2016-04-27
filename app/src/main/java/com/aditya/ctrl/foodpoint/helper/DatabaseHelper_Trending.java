package com.aditya.ctrl.foodpoint.helper;
/**
 * Created by Aditya on 4/26/2016.
 */

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DatabaseHelper_Trending extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "trend.db";
    private static final int DATABASE_VERSION = 2;

    public DatabaseHelper_Trending(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        // you can use an alternate constructor to specify a database location
        // (such as a folder on the sd card)
        // you must ensure that this folder is available and you have permission
        // to write to it
        //super(context, DATABASE_NAME, context.getExternalFilesDir(null).getAbsolutePath(), null, DATABASE_VERSION);

        // call this method to force a database overwrite every time the version number increments:
        //setForcedUpgrade();

        // call this method to force a database overwrite if the version number
        // is below a certain threshold:
        //setForcedUpgrade(2);
    }

    public Cursor getDataMeatLover() {
        SQLiteDatabase db = getReadableDatabase();
        String sqlTables = "TREND_MEATLOVER";
        Cursor c = db.query(sqlTables, null, null, null, null, null, null);
        c.moveToFirst();
        return c;
    }

    public Cursor getDataSushi() {
        SQLiteDatabase db = getReadableDatabase();
        String sqlTables = "TREND_SUSHI";
        Cursor c = db.query(sqlTables, null, null, null, null, null, null);
        c.moveToFirst();
        return c;
    }

}