package com.example.sqilt.data;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.sqilt.R;
import com.example.sqilt.model.Contact;
import com.example.sqilt.util.Util;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    public DatabaseHandler(Context context) {
        super(context, Util.DATABASE_NAME,null,Util.DATABASE_VERSION);
    }
//we create our table
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

         //create table  _name(id,name,phone_number)
        String CREATE_CONTACE_TABLE = "CREATE TABLE " + Util.TABLE_NAME + "(" + Util.KEY_ID + "INTEGER PRIMARY KEY," + Util.KEY_NAME + "TEXT" + Util.KEY_PHONE_NUMBER +
                "TEXT"+")";

        sqLiteDatabase.execSQL(CREATE_CONTACE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String DROP_TABLE= String.valueOf(R.string.drop_table_if_exists);
        sqLiteDatabase.execSQL(DROP_TABLE , new String[]{Util.DATABASE_NAME});

        onCreate(sqLiteDatabase);

    }
    /*
    CRUD = create,read,update,delete
     */

    //add contacts

    public void addcontact(Contact contact){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(Util.KEY_NAME,contact.getName());
        values.put(Util.KEY_PHONE_NUMBER,contact.getPhonenumber());

//insert to row
        sqLiteDatabase.insert(Util.TABLE_NAME,null,values);
        sqLiteDatabase.close();
    }
 public Contact getContact(int id) {
     SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
     Cursor cursor = sqLiteDatabase.query(Util.TABLE_NAME, new String[]{Util.KEY_ID, Util.KEY_NAME, Util.KEY_PHONE_NUMBER},
             Util.KEY_ID + "=?", new String[]{String.valueOf(id)},
             null, null, null
     );
     if (cursor != null)
         cursor.moveToFirst();

     Contact contact = new Contact();
     contact.setId(Integer.parseInt(cursor.getString(0)));
     contact.setName(cursor.getString(1));
     contact.setPhonenumber(cursor.getString(2));

     return contact;



 }


 public int updatecontact(Contact contact){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(Util.KEY_NAME,contact.getName());
        values.put(Util.KEY_PHONE_NUMBER,contact.getPhonenumber());

        //update (tablrname ,values,where id=43)

        return db.update(Util.TABLE_NAME,values,Util.KEY_ID + "=?",
                new String[]{String.valueOf(contact.getId())});
 }
 public void deletecontact(Contact contact){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(Util.TABLE_NAME,Util.KEY_ID+"=?",
                new String[]{String.valueOf(contact.getId())});
        db.close();
 }
    public List<Contact> getallcontacts() {
        List<Contact> contactList=new ArrayList<>();
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();

        String selectall="SELECT *FROM "+ Util.TABLE_NAME;
        Cursor cursor =sqLiteDatabase.rawQuery(selectall,null);

        if(cursor.moveToFirst()){
            do{
                Contact contact=new Contact();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhonenumber(cursor.getString(2));

                contactList.add(contact);
            }while (cursor.moveToNext());
        }
        return contactList;


 }
 //count the rows
 public int getCount(){
        String countQuery= "SELECT * FROM "+ Util.TABLE_NAME;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(countQuery,null);

        return cursor.getCount();
 }




}

