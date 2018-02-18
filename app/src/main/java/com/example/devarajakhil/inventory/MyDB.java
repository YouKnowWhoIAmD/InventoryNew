package com.example.devarajakhil.inventory;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import com.example.devarajakhil.inventory.DataObject;
import java.util.ArrayList;
import java.util.List;


import static java.sql.Types.INTEGER;

public class MyDB extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "InventoryDB.db";
    public static final String TABLE_NAME = "InventoryReal";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_NAME = "Name";
    public static final String COLUMN_PRICE = "Price";
    public static final String COLUMN_QUANTITY = "Quantity";
    public MyDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
     //   String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+" ( "+COLUMN_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "+COLUMN_NAME+" TEXT ,"+
      //          COLUMN_PRICE+"INTEGER ,"+COLUMN_QUANTITY+"INTEGER"+")";
        db.execSQL("CREATE TABLE IF NOT EXISTS InventoryReal (ID INTEGER PRIMARY KEY AUTOINCREMENT,Name text,Price int,Quantity int)");

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public List<DataObject> getNotifications() {
        SQLiteDatabase dbd = this.getReadableDatabase();
        List<DataObject> x = new ArrayList<DataObject>();

        Cursor res = dbd.rawQuery("select * from InventoryReal", null);
        if (res != null) {
            if (res.getCount()>0) {


                for (int i = 0; i < res.getCount(); i++) {
                    res.moveToNext();
                DataObject au1 = new DataObject();

                // au1.id = res.getInt(res.getColumnIndex("id"));
                au1.name = res.getString(res.getColumnIndex("Name"));
                au1.price = res.getString(res.getColumnIndex("Price"));
                au1.quantity = res.getString(res.getColumnIndex("Quantity"));

                    x.add(au1);
                }

            }
            dbd.close();
        }


        return x;

    }//getdata

    public void addHandler(String name,String p,String q) {
        SQLiteDatabase dbd = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_PRICE,p);
        values.put(COLUMN_QUANTITY,q);
       // SQLiteDatabase dbd = this.getWritableDatabase();
        dbd.insert(TABLE_NAME, null, values);
        dbd.close();
    }


    public boolean insertMessage(DataObject obj) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues notifyValues = new ContentValues();


        notifyValues.put("Name",obj.name);
        notifyValues.put("Price",obj.price);
        notifyValues.put("Quantity",obj.quantity);



        db.insert("InventoryReal", null, notifyValues);

        db.close();


        return true;
    }



}