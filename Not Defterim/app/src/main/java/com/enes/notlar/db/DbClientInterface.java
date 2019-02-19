package com.enes.notlar.db;

import com.enes.notlar.Kullanici;

import java.util.ArrayList;

public interface DbClientInterface{

    public void kullanicikayit(String ad,String sifre,String soyadi,String isim);
    public Kullanici Kontrol(Kullanici K);
    public void Notkaydetme(int a,String baslik,String notum,int op);
    public ArrayList<String> NotListeleme(int a);
    public String baslikyazdir(String a);
    public String notyazdir(String a);
    public void baslikguncelle(String idim,String a);
    public void notguncelle(String idim,String a);
    public ArrayList<String> kayitgetir();
    public int kayitkontrol(String a);
    public void alarmkayit(int a,String ad);
    public ArrayList<String> alarmlistele(int a);
    public String notgetir(String a);
    public String notid(String a);
    public String isimsoyisim(int id);
    public String kullaniciadigetir(int id);
    public int notbaslikkontrol(String a);
    public ArrayList<String> publicnotlistele(int op);
    public String userid(String id);
}
