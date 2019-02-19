package com.enes.notlar.db;

import android.content.Context;

import com.enes.notlar.Kullanici;
import com.enes.notlar.db.sqlite.Sqlite;

import java.util.ArrayList;

public class DbClientSqlite implements DbClientInterface {

    private Sqlite sqlite;

    public DbClientSqlite(Context context) {
        sqlite = new Sqlite(context);
    }

    public void kullanicikayit(String ad,String sifre,String soyadi,String isim)  {
        sqlite.kullanicikayit(ad, sifre, soyadi, isim) ;
    }

    public Kullanici Kontrol(Kullanici K) {
        return sqlite.Kontrol(K);
    }
    public void Notkaydetme(int a,String baslik,String notum,int op) {
        sqlite.Notkaydetme(a, baslik, notum,op);
    }
    public int bul(String a) {
        return sqlite.bul(a);
    }
    public ArrayList<String> NotListeleme(int a)  {
        return sqlite.NotListeleme(a) ;
    }
    public String baslikyazdir(String a) {
        return sqlite.baslikyazdir(a);
    }
    public String notyazdir(String a) {
        return sqlite.notyazdir(a);
    }
    public void baslikguncelle(String idim,String a) {
        sqlite.baslikguncelle(idim, a);
    }
    public void notguncelle(String idim,String a) {
        sqlite.notguncelle(idim, a);
    }
    public ArrayList<String> kayitgetir() {
        return sqlite.kayitgetir();
    }
    public int kayitkontrol(String a) {
        return sqlite.kayitkontrol(a);
    }
    public void alarmkayit(int a,String ad) {
        sqlite.alarmkayit(a, ad);
    }
    public ArrayList<String> alarmlistele(int a)  {
        return sqlite.alarmlistele( a) ;
    }
    public String notgetir(String a) {
        return sqlite.notgetir(a);
    }
    public String notid(String a) {
        return sqlite.notid(a);
    }
    public String isimsoyisim(int id) {
        return sqlite.isimsoyisim(id);
    }
    public String kullaniciadigetir(int id) {
        return sqlite.kullaniciadigetir(id);
    }
    public int notbaslikkontrol(String a) {
        return sqlite.notbaslikkontrol(a);
    }

    @Override
    public ArrayList<String> publicnotlistele(int op) {
        return null;
    }

    @Override
    public String userid(String id) {
        return null;
    }
}
