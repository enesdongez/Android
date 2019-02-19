package com.enes.notlar.db;

import android.content.Context;

import com.enes.notlar.Kullanici;
import com.enes.notlar.db.sqlite.Sqlite;

import java.util.ArrayList;
import com.google.firebase.*;

public class DbClientFirebase  {
    public DbClientFirebase(Context context) {
        //database = FirebaseDatabase.getInstance().getReference();
    }

/*
    private static final String DATABASE_URL = "https://notlar-e6068.firebaseio.com/";
    private DatabaseReference database;



    private static void updateStarCount(DatabaseReference postRef) {
        // [START post_stars_transaction]
        postRef.runTransaction(new Transaction.Handler() {
            public Transaction.Result doTransaction(MutableData mutableData) {
                Post post = mutableData.getValue(Post.class);
                if (post != null) {
                    // Update the starCount to be the same as the number of members in the stars map.
                    if (post.stars != null) {
                        post.starCount = post.stars.size();
                    } else {
                        post.starCount = 0;
                    }

                    mutableData.setValue(post);
                    return Transaction.success(mutableData);
                } else {
                    return Transaction.success(mutableData);
                }
            }

            public void onComplete(DatabaseError databaseError, boolean complete, DataSnapshot dataSnapshot) {
                System.out.println("updateStarCount:onComplete:" + complete);
            }
        });
        // [END post_stars_transaction]
    }


    public void kullanicikayit(String ad,String sifre,String soyadi,String isim)  {
        sqlite.kullanicikayit(ad, sifre, soyadi, isim) ;
    }

    public Kullanici Kontrol(Kullanici K) {
        return sqlite.Kontrol(K);
    }
    public void Notkaydetme(int a,String baslik,String notum) {
        sqlite.Notkaydetme(a, baslik, notum);
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
    public boolean kayitkontrol(String a) {
        return sqlite.kayitkontrol(a);
    }
    public void alarmkayit(int a,String ad) {
        sqlite.alarmkayit(a, ad);
    }
    public ArrayList<String> alarmlistele(int a)  {
        return sqlite.alarmlistele( a) ;
    }
    public ArrayList<String> notgetir(String a) {
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
    public Boolean notbaslikkontrol(String a) {
        return sqlite.notbaslikkontrol(a);
    }
    */
}
