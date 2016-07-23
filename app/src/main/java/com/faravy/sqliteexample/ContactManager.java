package com.faravy.sqliteexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by BITM Trainer - 401 on 7/10/2016.
 */
public class ContactManager {

    private DataBaseHelper helper;
    private SQLiteDatabase database;
    private Context context;

    public ContactManager(Context context) {
        this.context = context;
        helper = new DataBaseHelper(context);

    }

    private void open() {
        database = helper.getWritableDatabase();

    }

    private void close() {
        helper.close();
    }

    public boolean addContact(Contact contact) {

        this.open();

        ContentValues cv = new ContentValues();
        cv.put(DataBaseHelper.COL_NAME, contact.getName());
        cv.put(DataBaseHelper.COL_PHONENO, contact.getPhoneNo());

        long inserted = database.insert(DataBaseHelper.TABLE_CONTACT, null, cv);
        this.close();

        database.close();

        if (inserted > 0) {
            return true;
        } else return false;

    }

    public ArrayList<Contact> getAllContacts() {

        this.open();

        ArrayList<Contact> contactList = new ArrayList<>();

        Cursor cursor = database.query(DataBaseHelper.TABLE_CONTACT, null, null, null, null, null, null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();

            for (int i = 0; i < cursor.getCount(); i++) {
                int id = cursor.getInt(cursor.getColumnIndex(DataBaseHelper.COL_ID));
                String name = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_NAME));
                String phoneNo = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_PHONENO));

                Contact contact = new Contact(id, name, phoneNo);
                contactList.add(contact);
                cursor.moveToNext();
            }
            this.close();

        }
        return contactList;
    }

    public Contact getContact(int id) {

        this.open();

        Cursor cursor = database.query(DataBaseHelper.TABLE_CONTACT, new String[]{DataBaseHelper.COL_ID, DataBaseHelper.COL_NAME, DataBaseHelper.COL_PHONENO},
                DataBaseHelper.COL_ID + " = " + id, null, null, null, null);
        cursor.moveToFirst();

        int mId = cursor.getInt(cursor.getColumnIndex(DataBaseHelper.COL_ID));
        String name = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_NAME));
        String phoneNo = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_PHONENO));

        Contact contact = new Contact(mId, name, phoneNo);
        this.close();
        return contact;
    }

    public boolean updateContact(int id, Contact contact) {
        this.open();

        ContentValues cv = new ContentValues();
        cv.put(DataBaseHelper.COL_NAME, contact.getName());
        cv.put(DataBaseHelper.COL_PHONENO, contact.getPhoneNo());

        int updated = database.update(DataBaseHelper.TABLE_CONTACT, cv, DataBaseHelper.COL_ID + " = " + id, null);
        this.close();
        if (updated > 0) {
            return true;
        } else
            return false;
    }

    public boolean deleteContact(int id) {
        this.open();
        int deleted = database.delete(DataBaseHelper.TABLE_CONTACT, DataBaseHelper.COL_ID + " = " + id, null);
        this.close();
        if (deleted > 0) {
            return true;
        } else return false;


    }

}
