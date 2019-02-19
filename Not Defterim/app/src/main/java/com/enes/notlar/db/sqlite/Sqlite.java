package com.enes.notlar.db.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.enes.notlar.Kullanici;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Sqlite extends SQLiteOpenHelper {
    //
    public Sqlite(Context context){
        super(context,"kullanici",null,3);
    }
    @Override
        public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create TABLE kisiler (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL,password TEXT NOT NULL,date TEXT,lastname TEXT,isim TEXT)");
        db.execSQL("Create TABLE notum (id INTEGER PRIMARY KEY AUTOINCREMENT , baslik TEXT NOT NULL, notlar TEXT NOT NULL, user_id INTEGER not null,FOREIGN KEY(user_id) REFERENCES kisiler(id))");
        db.execSQL("Create TABLE alarm (id INTEGER PRIMARY KEY AUTOINCREMENT , alarmad TEXT NOT NULL, alarmtarih TEXT, alarmkuran INTEGER not null,FOREIGN KEY(alarmkuran) REFERENCES kisiler(id))");
        db.execSQL("Create TABLE profilresim (id INTEGER PRIMARY KEY AUTOINCREMENT, resim TEXT NOT NULL,user int NOT NULL,FOREIGN KEY(user) REFERENCES kisiler(id))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop TABLE kisiler");
        db.execSQL("Drop TABLE notum");
        db.execSQL("Drop TABLE alarm");
        db.execSQL("Drop TABLE profilresim");
    }

    public void copyDb() {
        SQLiteDatabase db=this.getReadableDatabase();
        String path = db.getPath();
        System.out.println(path);
        File f=new File(path);
        FileInputStream fis=null;
        FileOutputStream fos=null;

        try
        {
            fis=new FileInputStream(f);
            fos=new FileOutputStream("/storage/0000-0000/temp/enes_db_dump.db");
            while(true)
            {
                int i=fis.read();
                if(i!=-1)
                {fos.write(i);}
                else
                {break;}
            }
            fos.flush();
            //Toast.makeText(this, "DB dump OK", Toast.LENGTH_LONG).show();
        }
        catch(Exception e)
        {
            e.printStackTrace();
           // Toast.makeText(this, "DB dump ERROR", Toast.LENGTH_LONG).show();
        }
        finally
        {
            try
            {
                fos.close();
                fis.close();
            }
            catch(IOException ioe)
            {}
        }
    }

    public void kullanicikayit(String ad,String sifre,String soyadi,String isim) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", ad);
        cv.put("password", sifre);
        cv.put("lastname", soyadi);
        cv.put("isim",isim);
        cv.put("date",java.lang.System.currentTimeMillis());
        db.insert("kisiler", null, cv);
    }


   /* public String resimid(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT resim FROM profilresim";
        Cursor cursor = db.rawQuery(query,null);
        cursor.moveToNext();
        return cursor.getString(0);
    }*/

    public Kullanici Kontrol(Kullanici K){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.query("kisiler",
                new String[]{"name","password","lastname","date","id"},
                "name" + "=?",
                new String[]{K.ad},
                null, null, null);

        if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {
            Kullanici user1 = new Kullanici(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getInt(4));

            if (K.sifre.equalsIgnoreCase(user1.sifre)) {
                return user1;
            }
        }
        return null;
    }


    public void Notkaydetme(int a,String baslik,String notum,int op){

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv2=new ContentValues();
        cv2.put("user_id",a);
        cv2.put("baslik",baslik);
        cv2.put("notlar",notum);
        db.insert("notum",null,cv2);
        db.close();
    }


    public int bul(String a){

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT id FROM kisiler where name='"+a;
        Cursor cursor = db.rawQuery(query,null);
        int kk=cursor.getColumnIndex("user_id");

        return kk;

    }


    public ArrayList<String> NotListeleme(int a) {
        Kullanici K=new Kullanici();
        ArrayList<String> veriler = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT id,baslik,notlar FROM notum where user_id="+a;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){

            veriler.add(0,cursor.getString(1)


            );
           }
        db.close();
        return veriler;

    }
    public String baslikyazdir(String a){
        SQLiteDatabase db = this.getReadableDatabase();
        int id=Integer.parseInt(a);
        String query = "SELECT baslik FROM notum where id="+id;
        Cursor cursor = db.rawQuery(query,null);
        String baslik="";
        cursor.moveToNext();
        baslik=cursor.getString(0);
        db.close();
        return baslik;
    }
    public String notyazdir(String a){
        SQLiteDatabase db = this.getReadableDatabase();
        int id=Integer.parseInt(a);
        String query = "SELECT notlar FROM notum where id="+id;
        Cursor cursor = db.rawQuery(query,null);
        String not="";
        cursor.moveToNext();
        not=cursor.getString(0);
        db.close();
        return not;
    }

    public void baslikguncelle(String idim,String a){
        SQLiteDatabase db = this.getWritableDatabase();
        int id=Integer.parseInt(idim);
        /*String query = "UPDATE notum set baslik='"+a.toString()+"'where user_id="+id;
        Cursor cursor = db.rawQuery(query,null);
        cursor.moveToNext();*/
        ContentValues cv=new ContentValues();
        cv.put("baslik",a);
        db.update("notum",cv,"id"+"="+id,null);

        db.close();
    }
    public void notguncelle(String idim,String a){
        SQLiteDatabase db = this.getWritableDatabase();
        int id=Integer.parseInt(idim);
        /*String query = "UPDATE notum set notlar='"+a.toString()+"'where user_id="+id;
        Cursor cursor = db.rawQuery(query,null);
        cursor.moveToNext();*/
        ContentValues cv=new ContentValues();
        cv.put("notlar",a);
        db.update("notum",cv,"id"+"="+id,null);

        db.close();
    }
    public ArrayList<String> kayitgetir(){
        SQLiteDatabase db=this.getReadableDatabase();
        String query="SELECT name FROM kisiler";
        Cursor cursor=db.rawQuery(query,null);
        ArrayList<String> veritut=new ArrayList<>();
        while(cursor.moveToNext()){
            veritut.add(cursor.getString(0));
        }
        return veritut;
    }

    public int kayitkontrol(String a){
        SQLiteDatabase db=this.getReadableDatabase();
        String query="SELECT name FROM kisiler";
        Cursor cursor=db.rawQuery(query,null);
        String b="";
        while(cursor.moveToNext() && cursor.getCount()>0){
            b=cursor.getString(0);
            if(b.equals(a)) {
                return 1;
            }
        }
        return 0;
    }
    DateFormat df = new SimpleDateFormat("HH:mm:ss / dd:MM:yy");
    Date now = new Date();
    public void alarmkayit(int a,String ad){

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv2=new ContentValues();
        cv2.put("alarmkuran",a);
        cv2.put("alarmad",ad);
        cv2.put("alarmtarih",df.format(now));
        db.insert("alarm",null,cv2);
        db.close();
    }

    public ArrayList<String> alarmlistele(int a) {

        ArrayList<String> veriler = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT id,alarmad,alarmtarih FROM alarm where alarmkuran="+a;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){

            veriler.add(0,cursor.getString(2)+" - "+cursor.getString(1)

            );
        }
        db.close();
        return veriler;

    }

    public String notgetir(String a){
        String veriler = "";
        int id=Integer.parseInt(a.toString());
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT notlar,baslik FROM notum where id="+id;
        Cursor cursor = db.rawQuery(query,null);

        while(cursor.moveToNext()){
            veriler=(cursor.getString(1)+"\n"+cursor.getString(0));
        }
        return veriler;
    }

    public String notid(String a){

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT id,notlar FROM notum where baslik='"+a+"'";
        Cursor cursor = db.rawQuery(query,null);
        cursor.moveToNext();
        return cursor.getString(0);
    }

    public String isimsoyisim(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT isim,lastname FROM kisiler where id="+id;
        Cursor cursor = db.rawQuery(query,null);
        cursor.moveToNext();
        String tut=cursor.getString(0)+" "+cursor.getString(1);
        return tut;
    }
    public String kullaniciadigetir(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT name FROM kisiler where id="+id;
        Cursor cursor = db.rawQuery(query,null);
        cursor.moveToNext();
        String tut=cursor.getString(0);
        return tut;
    }

    public int notbaslikkontrol(String a){
        SQLiteDatabase db=this.getReadableDatabase();
        String query="SELECT baslik FROM notum";
        Cursor cursor=db.rawQuery(query,null);
        String b="";
        while(cursor.moveToNext() && cursor.getCount()>0){
            b=cursor.getString(0);
            if(b.equals(a)) {
                return 1;
            }
        }
        return 0;
    }

   /* public void verisil(){
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("");
    }*/

}
