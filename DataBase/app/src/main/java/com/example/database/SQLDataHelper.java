package com.example.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class SQLDataHelper extends SQLiteOpenHelper {
    private static final String TABLE_NAME = "DanhBa";
    private static final int VERSION_TABLE = 3;
    private static final String ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_NUMBER = "phone_number";

    public SQLDataHelper(Context context) {
        super(context, TABLE_NAME, null, VERSION_TABLE);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + "(" + ID + " INTEGER PRIMARY KEY, " +
                COLUMN_NAME + " TEXT, "
                + COLUMN_NUMBER + " TEXT"
                + ")";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE EXISTS" + TABLE_NAME;
        db.execSQL(query);
        onCreate(db);
    }

    public void addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, contact.getName());
        contentValues.put(COLUMN_NUMBER, contact.getPhone());
        long x = db.insert(TABLE_NAME, null, contentValues);
        db.close();
    }

    public List<Contact> getAllContact() {
        List<Contact> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{ID, COLUMN_NAME, COLUMN_NUMBER}, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                list.add(new Contact(cursor.getInt(0), cursor.getString(1), cursor.getString(2)));
            } while (cursor.moveToNext());
        }
        db.close();
        return list;
    }
    public void deleteContactBy(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,ID+" =?",new String[]{String.valueOf(id)});
        db.close();
    }
    public void updateContactByID(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME,contact.getName());
        contentValues.put(COLUMN_NUMBER,contact.getPhone());
        db.update(TABLE_NAME,contentValues,ID+" =?",new String[]{String.valueOf(contact.getId())});
        db.close();
    }
}
