package com.example.task71p;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {


    public DatabaseHelper(@Nullable Context context) {
        super(context, "items_db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_ITEM_TABLE = "CREATE TABLE ITEMS(ITEMID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, PHONE INTEGER, DESCRIPTION TEXT, DATE TEXT, LOCATION TEXT, LOST BOOLEAN)";
        sqLiteDatabase.execSQL(CREATE_ITEM_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String DROP_ITEMS_TABLE = "DROP TABLE IF EXISTS ITEMS";
        sqLiteDatabase.execSQL(DROP_ITEMS_TABLE);
        onCreate(sqLiteDatabase);
    }


    public long insertItem(Item item)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME", item.getName());
        contentValues.put("PHONE", item.getPhone());
        contentValues.put("DESCRIPTION", item.getDescription());
        contentValues.put("DATE", item.getDate());
        contentValues.put("LOCATION", item.getLocation());
        contentValues.put("LOST", item.isLost());
        long row = db.insert("ITEMS", null, contentValues);

        return row;

    }

//   Return line taken from here: https://stackoverflow.com/questions/7510219/deleting-row-in-sqlite-in-android
    public boolean foundItem(String name)
    {


        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete("ITEMS", "NAME" + "=?", new String[]{name}) > 0;


    }

    public ArrayList<String> fetchAllItems()
    {
        ArrayList<String> itemList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String SELECT_ALL_ITEMS = "SELECT * FROM ITEMS";

        Cursor cursor = db.rawQuery(SELECT_ALL_ITEMS, null);

        if(cursor.moveToFirst())
        {
            do
            {
                String item = cursor.getString(1);
                itemList.add(item);
            }while (cursor.moveToNext());
        }
        return itemList;
    }

    public Cursor queryItem(String name)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor res = db.rawQuery("SELECT * FROM ITEMS WHERE NAME=?", new String[]{name});

        return res;
    }
}
