package com.enes.notlar;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


import com.enes.notlar.db.DbClient;
import com.enes.notlar.db.DbClientInterface;

import java.util.ArrayList;

public class Main3Activity extends AppCompatActivity

        implements NavigationView.OnNavigationItemSelectedListener {

    FragmentManager fr=getSupportFragmentManager();
    final DbClientInterface dbClient = DbClient.createClient(this);
    ArrayList<String> list;

    TextView kad;
    int idtut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       // final ImageView profil=(ImageView) findViewById(R.id.imageView);

        //fr.beginTransaction().replace(R.id.content_frame,new Notlarim()).commit();    //Başlangıç sayfası çağırma kodu!!

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerview = navigationView.getHeaderView(0);
        kad=(TextView) headerview.findViewById(R.id.kadadi);

        if(getIntent().getExtras().getString("veri")!=null){
            String a=getIntent().getExtras().getString("veri");

            kad.setText(a);
        } //Girilen kullanıcıadı ataması!!


        //list=veritabani.NotListele();


        //veritabani2.Notkaydetme("Enes","Döngez");
        String f=getIntent().getExtras().getString("veri");
        //int h=veritabani2.Kontrol2(f.toString());

        ListView liste=(ListView) findViewById(R.id.notgoster);

             idtut = getIntent().getExtras().getInt("id");


         String adsoyad=dbClient.isimsoyisim(idtut);
         TextView adisoyadi=(TextView) headerview.findViewById(R.id.kullaniciadi);
         adisoyadi.setText(adsoyad);


        final ArrayList<String> veri = dbClient.NotListeleme(idtut);

        ArrayAdapter<String> adapter = new  ArrayAdapter<>(Main3Activity.this, android.R.layout.simple_list_item_1,android.R.id.text1,veri);
        liste.setAdapter(adapter);
        liste.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                  String itemim=(String) parent.getItemAtPosition(position);
                 String a= dbClient.notid(itemim);
               // Toast.makeText(getApplicationContext(),a,Toast.LENGTH_LONG).show();

                Intent intent=new Intent(Main3Activity.this,Main5Activity.class);
                  intent.putExtra("gonder",a);
                  intent.putExtra("id",idtut);
                  intent.putExtra("veri",kad.getText());
                  startActivity(intent);

            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ikincisayfagecis=new Intent(Main3Activity.this, Main4Activity.class);
                ikincisayfagecis.putExtra("veri2",kad.getText());
                ikincisayfagecis.putExtra("id",idtut);
                startActivity(ikincisayfagecis);
            }
        });

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main3, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.nav_yeninot) {
            Intent ikincisayfagecis=new Intent(Main3Activity.this, Main4Activity.class);
            ikincisayfagecis.putExtra("veri2",kad.getText());
            ikincisayfagecis.putExtra("id",idtut);
            startActivity(ikincisayfagecis);
            //fr.beginTransaction().replace(R.id.content_frame,new Yeninot()).commit();


        } else if (id == R.id.nav_hatirlat) {
            //Toast.makeText(getApplicationContext(),"Hatırlatıcı",Toast.LENGTH_LONG).show();
            Intent ikincisayfagecis=new Intent(Main3Activity.this, Main8Activity.class);
            ikincisayfagecis.putExtra("veri2",kad.getText());
            ikincisayfagecis.putExtra("id",idtut);
            startActivity(ikincisayfagecis);

        } else if (id == R.id.nav_hatirlaticiekle) {
            Intent ikincisayfagecis=new Intent(Main3Activity.this, Main7Activity.class);
            ikincisayfagecis.putExtra("veri2",kad.getText());
            ikincisayfagecis.putExtra("id",idtut);
            startActivity(ikincisayfagecis);


        }else if (id == R.id.nav_wshap) {
           // Toast.makeText(getApplicationContext(),"mesaj gönderme",Toast.LENGTH_LONG).show();
            Intent ikincisayfagecis=new Intent(Main3Activity.this, Main9Activity.class);
            ikincisayfagecis.putExtra("veri2",kad.getText());
            ikincisayfagecis.putExtra("id",idtut);
            startActivity(ikincisayfagecis);


        } else if (id == R.id.nav_paylasilan) {
            // Toast.makeText(getApplicationContext(),"mesaj gönderme",Toast.LENGTH_LONG).show();
            Intent ikincisayfagecis=new Intent(Main3Activity.this, Main10Activity.class);
            ikincisayfagecis.putExtra("veri",kad.getText());
            ikincisayfagecis.putExtra("id",idtut);
            startActivity(ikincisayfagecis);


        }else if (id == R.id.nav_notlarim) {
            Intent ikincisayfagecis=new Intent(Main3Activity.this, Main3Activity.class);
            ikincisayfagecis.putExtra("veri",kad.getText());
            ikincisayfagecis.putExtra("id",idtut);
            startActivity(ikincisayfagecis);
            //fr.beginTransaction().replace(R.id.content_frame,new Notlarim()).commit();



        } else if (id == R.id.nav_cikis) {

            Intent cikis=new Intent(Main3Activity.this, MainActivity.class);
            startActivity(cikis);

        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }






    /*public void notkaydet(View view) {
        veritabani.kullanicikayit(notbaslik.getText().toString(),notyazi.getText().toString());
        Toast.makeText(getApplicationContext(),"Not kayıt edildi.",Toast.LENGTH_LONG).show();
        fr.beginTransaction().replace(R.id.content_frame,new Notlarim()).commit();
    }*/
}
