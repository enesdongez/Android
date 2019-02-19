package com.enes.notlar.db;

import android.content.Context;

import com.enes.notlar.Kullanici;
import com.enes.notlar.db.rest.DbClientRest;
import com.enes.notlar.db.sqlite.Sqlite;

import java.util.ArrayList;

public class DbClient {
    public static DbClientInterface createClient(Context context) {
        //DbClientInterface dbClient = new DbClientSqlite(context);
        DbClientInterface dbClient = new DbClientRest(context);
        return dbClient;
    }

}
