package com.example.enesd_000.sqllitekayit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Sqlite extends SQLiteOpenHelper {
    public Sqlite(Context context){
        super(context,"kullanici",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create TABLE kisiler (name TEXT NOT NULL,password TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop TABLE kisiler");
    }

    public void kullanicikayit(String ad,String sifre){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("name",ad);
        cv.put("password",sifre);
        db.insert("kisiler",null,cv);
        db.close();
    }

   public Kullanici Kontrol(Kullanici K){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.query("kisiler",
                new String[]{"name", "password"},
                "name" + "=?",
                new String[]{K.ad},
                null, null, null);

        if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {
            Kullanici user1 = new Kullanici(cursor.getString(0), cursor.getString(1));

            if (K.sifre.equalsIgnoreCase(user1.sifre)) {
                return user1;
            }
        }
        return null;
    }

}
