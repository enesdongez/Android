package com.enes.notlar;

public class Kullanici {
    public String ad;
    public String soyad;
    public String sifre;
    public String baslik;
    public String notlar;
    public String zaman;
    public int id;
    public String resim;

    public Kullanici(int id,String baslik,String notlar){
        this.baslik=baslik;
        this.id=id;
        this.notlar=notlar;

    }

    public Kullanici(String name, String password) {
        this.ad = name;
        this.sifre = password;

    }
    public Kullanici(String name, String password,String lastname,String date,int idm) {
        this.ad = name;
        this.sifre = password;
        this.soyad=lastname;
        this.zaman=date;
        this.id=idm;
    }
    public Kullanici(String name){
        this.ad=name;
    }
    public Kullanici(){}

}
