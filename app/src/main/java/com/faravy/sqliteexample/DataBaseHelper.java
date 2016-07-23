package com.faravy.sqliteexample;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by BITM Trainer - 401 on 7/10/2016.
 */
public class DataBaseHelper extends SQLiteOpenHelper {

   private static final String DATABASE_NAME = "batch_14_db";
   private static final int DATABASE_VERSION = 1;

    public static final String COL_ID = "id";
    public static final String COL_NAME = "name";
    public static final String COL_PHONENO = "phoneNo";
    public static final String TABLE_CONTACT = "contacts_info";


   private static final String CREATE_CONTACT_TABLE = " CREATE TABLE " + TABLE_CONTACT +
            "( " + COL_ID + " INTEGER PRIMARY KEY," + COL_NAME + " TEXT, "
            + COL_PHONENO + " TEXT )";


    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_CONTACT_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {


    }
}
