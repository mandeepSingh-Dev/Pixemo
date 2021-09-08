package com.example.unsplash.MEMOApp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;


public class MySqLiteOpenHelper extends SQLiteOpenHelper
{
    public MySqLiteOpenHelper( Context context)
    {
        super(context,Paramm.DB_NAME, null, Paramm.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create="Create Table " + Paramm.Table_Name + " ( " + Paramm.KEY_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, " + Paramm.KEY_TITLE + " TEXT, " + Paramm.KEY_NOTE+ " TEXT)";
        db.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void AddData(NoteData noteData)
    {
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(Paramm.KEY_TITLE,noteData.getTitle());
        values.put(Paramm.KEY_NOTE,noteData.getNotes());

        db.insert(Paramm.Table_Name,null,values);
        db.close();

    }
   public List<NoteData> getData()
    {
        List<NoteData> list=new ArrayList<>();

        SQLiteDatabase db=this.getReadableDatabase();
        String read=" SELECT * FROM " + Paramm.Table_Name;

        Cursor cursor=db.rawQuery(read,null);
        if(cursor.moveToFirst())
       {
            do {
                NoteData noteData=new NoteData();
                noteData.id=cursor.getInt(0);
                noteData.title = cursor.getString(1);
                noteData.notes = cursor.getString(2);
                list.add(noteData);
            }while(cursor.moveToNext());
        }
        return list;
    }
    public void updateData(NoteData noteData)
    {
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues contentValues=new ContentValues();
        contentValues.put(Paramm.KEY_TITLE,noteData.title);
        contentValues.put(Paramm.KEY_NOTE,noteData.notes);

        db.update(Paramm.Table_Name,contentValues,Paramm.KEY_ID+"=?",new String[]{String.valueOf(noteData.id)});
    }
    public void deleteData(int id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(Paramm.Table_Name,Paramm.KEY_ID + " =? ",new String[]{String.valueOf(id)});
    }


}

