package com.enes.notlar.db.rest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.enes.notlar.Kullanici;
import com.enes.notlar.db.DbClientInterface;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DbClientRest implements DbClientInterface {

    public DbClientRest(Context context) {

    }

    public HashMap callRest(String service, String[] params)  {
        HashMap mapResult = new HashMap();
        try {
            HashMap map = new HashMap();
            for (int i=0;i<params.length;i++) {
                String[] parts = params[i].split("=");
                map.put(parts[0].trim(),parts[1].trim());
            }

            AsyncTaskRest x = new AsyncTaskRest(service, map);
            String result = x.execute().get();
            System.out.println(result);

            JSONObject jsonobject = new JSONObject(result);
            //String name = jsonobject.getString("name");
            //System.out.println(name);

            mapResult = (HashMap) JsonUtils.toMap(jsonobject);

        } catch (Exception e) {
            e.printStackTrace();
            mapResult.put("result", "exception");
        }
        return mapResult;
    }

    public void kullanicikayit(String ad,String sifre,String soyadi,String isim)  {
        HashMap map = callRest("kullanicikayit", new String[]{
                "ad=" + ad, "sifre=" + sifre, "soyadi=" + soyadi, "isim=" + isim
        });
    }

    @Override
    public Kullanici Kontrol(Kullanici K) {

        HashMap map = callRest("kontrol", new String[]{ "ad=" + K.ad, "sifre=" + K.sifre });
        if (map.size() > 0) {

            if (map.containsKey("result") == false) {
                Kullanici user1 = new Kullanici(
                        (String) map.get("ad"),
                        (String) map.get("sifre"),
                        (String) map.get("soyad"),
                        (String) map.get("zaman"),
                        Integer.parseInt((String) map.get("id")));
                return user1;
            }
        }
        return null;
    }

    @Override
    public void Notkaydetme(int a, String baslik, String notum,int op) {
        HashMap map = callRest("notkayit", new String[]{
                "user_id=" + a, "baslik=" + baslik, "notlar=" + notum, "op=" + op
        });

    }

    @Override
    public ArrayList<String> NotListeleme(int a) {
        HashMap map = callRest("notlistele", new String[]{
                "id=" + a
        });
        String veriler = (String) map.get("veriler");
        String[] basliklar = veriler.split("#");
        ArrayList<String> list = new ArrayList<>(Arrays.asList(basliklar));

        return list;
    }
    @Override
    public ArrayList<String> publicnotlistele(int op){
        HashMap map = callRest("publicnotlistele", new String[]{
                "op="+op

        });
        String veriler = (String) map.get("veriler");
        String[] basliklar = veriler.split("#");
        ArrayList<String> list = new ArrayList<>(Arrays.asList(basliklar));

        return list;
    }

    @Override
    public String userid(String id) {
        HashMap map = callRest("userid", new String[]{ "id=" + id});

        String userid=(String)map.get("user_id");

        return userid;
    }

    @Override
    public String baslikyazdir(String a) {
        HashMap map = callRest("baslikyazdir", new String[]{ "id=" + a});

        String baslik=(String)map.get("baslik");

        return baslik;
    }

    @Override
    public String notyazdir(String a) {
        HashMap map = callRest("notyazdir", new String[]{ "id=" + a});

        String not=(String)map.get("notlar");

        return not;
    }

    @Override
    public void baslikguncelle(String id, String baslik) {
        HashMap map = callRest("baslikguncelle", new String[]{
                "id=" + id, "baslik=" + baslik
        });

    }

    @Override
    public void notguncelle(String id, String not) {
        HashMap map = callRest("notguncelle", new String[]{
                "id=" + id, "not=" + not
        });
    }

    @Override
    public ArrayList<String> kayitgetir() {
        return null;
    }

    @Override
    public int kayitkontrol(String a) {
        HashMap map = callRest("kayitkontrol", new String[]{ "ad=" + a});
        if (map.size() == 0) {
            return -1;
        } else {
            if (map.containsKey("result")) {
                return 0;
            } else {
                return 1;
            }
        }

    }

    @Override
    public void alarmkayit(int id, String ad) {
        HashMap map = callRest("alarmkayit", new String[]{
                "id=" + id, "alarmad=" + ad
        });

    }

    @Override
    public ArrayList<String> alarmlistele(int a) {
        HashMap map = callRest("alarmlistele", new String[]{
                "id=" + a
        });
        String veriler = (String) map.get("veriler");
        String[] basliklar = veriler.split("#");
        ArrayList<String> list = new ArrayList<>(Arrays.asList(basliklar));

        return list;

    }

    @Override
    public String notgetir(String a) {
        HashMap map = callRest("notgetir", new String[]{ "id=" + a});

        String notum=(String)map.get("baslik")+" \n "+(String)map.get("notlar");

        return notum;
    }

    @Override
    public String notid(String a) {
        HashMap map = callRest("notid", new String[]{ "baslik=" + a});

        String id=(String)map.get("id");

        return id;

    }

    @Override
    public String isimsoyisim(int id) {

        HashMap map = callRest("isimsoyisim", new String[]{ "id=" + id});

            String adsoyad=(String)map.get("ad")+" "+(String)map.get("soyad");

            return adsoyad;

    }

    @Override
    public String kullaniciadigetir(int id) {
        HashMap map = callRest("kullaniciadigetir", new String[]{ "id=" + id});

        String ad=(String)map.get("name");

        return ad;
    }

    @Override
    public int notbaslikkontrol(String a) {
        HashMap map = callRest("notbaslikkontrol", new String[]{ "ad=" + a});

        if (map.size() == 0) {
            return -1;
        } else {
            if (map.containsKey("result")) {
                return 0;
            } else {
                return 1;
            }
        }
    }




}
