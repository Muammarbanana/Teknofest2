package com.example.ahmadmuammarfanani.teknofest2;

import android.*;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahmadmuammarfanani.teknofest2.tambahan.HorizontalAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Profiletoko extends AppCompatActivity {

    ArrayList<DataSnapshot> ProductMakanan = new ArrayList<>();
    ArrayList<DataSnapshot> ProductMinuman = new ArrayList<>();
    ArrayList<DataSnapshot> ProductLainnya = new ArrayList<>();

    String NamaToko;

    String Pemilik;
    String logo;
    String waktu;
    String lokasi;
    ArrayList<String> hargamulai = new ArrayList<>();
    String deskripsi;


    public String IDLine = "line://ti/p/QjpQSYdbdY";
    public String InstaID = "diva.farisah";
    public String NOWA = "+6281322115161";
    public String NOHP = "08668899021";
    private List<ApplicationInfo> m_appList;
    public static final String PACKAGE_NAME_LINE = "jp.naver.line.android";
    public static final String PACKAGE_NAME_IG = "com.instagram.android";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profiletoko);

        SharedPreferences DataToko = this.getSharedPreferences("DataToko" , Context.MODE_PRIVATE);
        NamaToko = DataToko.getString("Nama_Toko" , "Toko1");


        //menyiapkan data
        settinglayout();

        //mengeluarkan listmakanan dari array list productmakanan
        RecyclerView list = (RecyclerView) findViewById(R.id.listmakanantoko);
        list.setLayoutManager(new LinearLayoutManager(this , LinearLayoutManager.HORIZONTAL , false));
        list.setAdapter(new HorizontalAdapter(ProductMakanan , Profiletoko.this));

        //mengeluarkan listminnuman dari array list productmakanan
        RecyclerView list2 = (RecyclerView) findViewById(R.id.listminumantoko);
        list2.setLayoutManager(new LinearLayoutManager(this , LinearLayoutManager.HORIZONTAL , false));
        list2.setAdapter(new HorizontalAdapter(ProductMinuman , Profiletoko.this));

        //mengeluarkan listlainnya dari arraylist productlainnya
        RecyclerView list3 = (RecyclerView) findViewById(R.id.listlaintoko);
        list3.setLayoutManager(new LinearLayoutManager(this , LinearLayoutManager.HORIZONTAL , false));
        list3.setAdapter(new HorizontalAdapter(ProductLainnya , Profiletoko.this));

    }


    //membuka activity detail
    public void opendetail(View view){
        //Toast.makeText(this , "Wait For Open" , Toast.LENGTH_SHORT).show();
        TextView textView = view.findViewWithTag("namaproduk");
        String produk = (String)textView.getText();
        setsharedpref( NamaToko , produk);
        Intent intent = new Intent(this , Detail.class);
        startActivity(intent);
    }

    //memasukan data kedalam database lokal
    private void setsharedpref(String NamaTokonya , String NamaProduknya){
        SharedPreferences DataToko = this.getSharedPreferences("DataToko" , Context.MODE_PRIVATE);
        SharedPreferences.Editor NowDat = DataToko.edit();
        NowDat.putString("Nama_Toko" , NamaTokonya);
        NowDat.putString("Nama_Produk", NamaProduknya);
        NowDat.commit();

    }

    private void setsharedpref(String NamaTokonya){
        SharedPreferences DataToko = this.getSharedPreferences("DataToko" , Context.MODE_PRIVATE);
        SharedPreferences.Editor NowDat = DataToko.edit();
        NowDat.putString("Nama_Toko" , NamaTokonya);
        //NowDat.putString("Nama_Produk", NamaProduknya);
        NowDat.commit();

    }

    private void settinglayout(){
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(NamaToko);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Pemilik = dataSnapshot.child("Owner").getValue(String.class);
                logo = dataSnapshot.child("Logo").getValue(String.class);
                waktu = dataSnapshot.child("Waktu").getValue(String.class);
                lokasi = dataSnapshot.child("Lokasi").getValue(String.class);
                deskripsi= dataSnapshot.child("Deskripsi").getValue(String.class);

                for(DataSnapshot DS : dataSnapshot.getChildren()){
                    if(DS.child("Nama").getValue() != null){
                        hargamulai.add(DS.child("Harga").getValue(String.class));
                    }

                }

                Collections.sort(hargamulai);
                setTextLayout();


                //untuk memisahkan makanan minman dan yg lainnya
                for(DataSnapshot DS : dataSnapshot.getChildren()){
                    if(DS.child("Nama").getValue() != null){
                        if(DS.child("Jenis").getValue(String.class).equals("Makanan")){
                            ProductMakanan.add(DS);

                        }else if(DS.child("Jenis").getValue(String.class).equals("Minuman")){
                            ProductMinuman.add(DS);

                        }else{
                            ProductLainnya.add(DS);

                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void setTextLayout(){
        TextView namatoko = (TextView) findViewById(R.id.namatoko);
        TextView wkt = (TextView) findViewById(R.id.waktu);
        TextView loksi = (TextView) findViewById(R.id.lokasi);
        TextView dsksi = (TextView) findViewById(R.id.deskripsitoko);
        TextView harga = (TextView) findViewById(R.id.hargamulai);
        ImageView punya = (ImageView) findViewById(R.id.gambarpemilik);
        ImageView logotoko = (ImageView) findViewById(R.id.logotoko);

        namatoko.setText(NamaToko);
        wkt.setText(waktu);
        loksi.setText(lokasi);
        dsksi.setText(deskripsi);
        harga.setText("Starts From Rp."+hargamulai.get(0)+",-");
        Picasso.with(Profiletoko.this).load(Pemilik).error(R.drawable.line).into(punya);
        Picasso.with(Profiletoko.this).load(logo).error(R.drawable.line).into(logotoko);


    }
    public void seemore(View view){
        setsharedpref(NamaToko);
        Intent intent = new Intent(Profiletoko.this , Kategori_Produk_Toko.class);
        Bundle bundle = new Bundle();
        bundle.putString("NamaToko",NamaToko);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void back(View view){
        finish();
    }




    public void lineadd(View view) {

        String sendText = IDLine;
        Intent intent = null;
        if((!IDLine.equals("")) || (IDLine != null)){
            if (checkInstalled(PACKAGE_NAME_LINE)) {
                String lineString = sendText;
                try {
                    intent = Intent.parseUri(lineString, Intent.URI_INTENT_SCHEME);
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
                startActivity(intent);
            }
        }
    }

    public void instaadd(View view) {

        Uri uri = Uri.parse("http://instagram.com/_u/"+InstaID);
        if((!InstaID.equals("")) || (InstaID != null)) {
            if (checkInstalled(PACKAGE_NAME_IG)) {
                Intent i = new Intent(Intent.ACTION_VIEW, uri);

                i.setPackage(PACKAGE_NAME_IG);

                try {
                    startActivity(i);
                } catch (ActivityNotFoundException e) {

                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://instagram.com/xxx")));
                }
            }
        }
    }

    public void waadd(View view) {
        if((NOWA != "") || (NOWA != null)) {
            openWhatsappContact(NOWA);
        }
    }

    void openWhatsappContact(String number) {
        Uri uri = Uri.parse("smsto:" + number);
        Intent i = new Intent(Intent.ACTION_SENDTO, uri);
        i.setPackage("com.whatsapp");
        startActivity(Intent.createChooser(i, ""));
    }

    public void call(View view) {
        if((NOHP != "") || (NOHP != null)) {
            String nomer = "tel:" + NOHP;
            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse(nomer));
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            startActivity(callIntent);
        }
    }



    //Mengecek apakah aplikasi app Ter install

    private boolean checkInstalled(String PACKAGE_NAME){
        PackageManager pm = getPackageManager();
        m_appList = pm.getInstalledApplications(0);
        boolean lineInstallFlag = false;
        for (ApplicationInfo ai : m_appList) {
            if(ai.packageName.equals(PACKAGE_NAME)){
                lineInstallFlag = true;
                break;
            }
        }
        return lineInstallFlag;
    }
}
