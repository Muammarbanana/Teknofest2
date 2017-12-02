package com.example.ahmadmuammarfanani.teknofest2;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahmadmuammarfanani.teknofest2.tambahan.HorizontalAdapter;
import com.example.ahmadmuammarfanani.teknofest2.tambahan.SlidePage;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Detail extends AppCompatActivity {
    private ViewPager viewpage;
    private Integer[] ImageTop = null;


    public static final String PACKAGE_NAME_LINE = "jp.naver.line.android";
    public static final String PACKAGE_NAME_IG = "com.instagram.android";
    private List<ApplicationInfo> m_appList;

    ArrayList<String> Namaproduct;
    ArrayList<String> Hargaproduct;
    ArrayList<String> IMGPproduct;
    ArrayList<DataSnapshot> Product;

    String NamaToko;
    String NamaProduk;

    public String Nama="Hello Donut";
    public String pemilik = " ";
    public String lokasi=" ";
    public String jam=" ";
    public String harga=" ";
    public String deskripsi=" ";



    public String IDLine = "line://ti/p/QjpQSYdbdY";
    public String InstaID = "diva.farisah";
    public String NOWA = "+6281322115161";
    public String NOHP = "08668899021";


    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        dialog = new ProgressDialog(Detail.this);
        dialog.setMessage("Loading...");
        dialog.setCancelable(false);
        dialog.setInverseBackgroundForced(false);
        dialog.show();

        SharedPreferences DataToko = this.getSharedPreferences("DataToko" , Context.MODE_PRIVATE);

        NamaToko = DataToko.getString("Nama_Toko" , "Toko1");
        NamaProduk = DataToko.getString("Nama_Produk" , "HelloDonut");


        InsertImg();


        //GetImgStorage();
        GetText();



        //SetText();




    }

    private void InsertImg() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(NamaToko).child(NamaProduk).child("IMGUrl");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final int[] i = {0};
                viewpage = (ViewPager) findViewById(R.id.iklanatas);

                SlidePage ViewSP = new SlidePage(Detail.this , (int) dataSnapshot.getChildrenCount());

                for(DataSnapshot DS : dataSnapshot.getChildren()){
                    ViewSP.STRAdd(DS.getValue(String.class));
                }


                viewpage.setAdapter(ViewSP);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    //Barujuga
    private void setsharedpref(String NamaTokonya , String NamaProduknya){
        SharedPreferences DataToko = this.getSharedPreferences("DataToko" , Context.MODE_PRIVATE);
        SharedPreferences.Editor NowDat = DataToko.edit();
        NowDat.putString("Nama_Toko" , NamaTokonya);
        NowDat.putString("Nama_Produk", NamaProduknya);
        NowDat.commit();

    }

    //Baru
    private void GetText() {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference(NamaToko);
                //myRef.setValue("Hello, World");
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        DataSnapshot Datas = dataSnapshot.child(NamaProduk);

                        Nama = Datas.child("Nama").getValue(String.class);
                        harga = Datas.child("Harga").getValue(String.class);
                        deskripsi= Datas.child("Deskripsi").getValue(String.class);


                        pemilik = dataSnapshot.child("Owner").getValue(String.class);
                        lokasi = dataSnapshot.child("Lokasi").getValue(String.class);
                        jam= dataSnapshot.child("Waktu").getValue(String.class);

                        IDLine = dataSnapshot.child("IDLine").getValue(String.class);
                        InstaID = dataSnapshot.child("IGID").getValue(String.class);
                        NOWA = dataSnapshot.child("NoWA").getValue(String.class);
                        NOHP = dataSnapshot.child("NoHP").getValue(String.class);
                        if((Nama != null ) &(lokasi != null)&(jam != null)&(harga!=null)&(deskripsi != null)) {
                            SetText();
                        }

                Namaproduct = new ArrayList<>();
                Hargaproduct = new ArrayList<>();
                IMGPproduct = new ArrayList<>();
                Product = new ArrayList<>();

                for(DataSnapshot DS : dataSnapshot.getChildren()){
                        if(DS.child("Nama").getValue() != null){
                            if(DS.child("Nama").getValue() != Nama){
                                Product.add(DS);
                            }
                        }
                    }

                int i = Product.size();
                Collections.shuffle(Product);

                RecyclerView list = (RecyclerView) findViewById(R.id.listproduk);
                list.setLayoutManager(new LinearLayoutManager(Detail.this , LinearLayoutManager.HORIZONTAL , false));
                list.setAdapter(new HorizontalAdapter(Product , Detail.this));

                dialog.dismiss();
            }




            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Tag", "Failed to read value.", error.toException());
            }
        });


    }



    public void opendetail(View view){
        Toast.makeText(this , "Wait For Open" , Toast.LENGTH_SHORT).show();
        TextView textView = view.findViewWithTag("namaproduk");
        String produk = (String)textView.getText();
        setsharedpref(NamaToko , produk);
        Intent intent = new Intent(this , Detail.class);
        startActivity(intent);


    }

    public void opentoko(View view){
        Toast.makeText(this , "Wait For Open" , Toast.LENGTH_SHORT).show();
        //view.setBackground(Drawable.createFromPath("#FFFFFF"));

        setsharedpref(NamaToko , NamaProduk);
        Intent intent = new Intent(Detail.this , Profiletoko.class);
        Bundle bundle = new Bundle();
        bundle.putString("NamaToko",NamaToko);
        intent.putExtras(bundle);
        startActivity(intent);

    }


    public void see_more(View view) {
        setsharedpref(NamaToko , NamaProduk);
        Intent intent = new Intent(Detail.this , Kategori_Produk_Toko.class);
        startActivity(intent);
    }

    public void back(View view) {
        finish();
    }

    private void SetText(){
        TextView Namamkn = (TextView) findViewById(R.id.namamkn);
        TextView Hargamkn = (TextView) findViewById(R.id.hargamkn);
        TextView Deskripsi = (TextView) findViewById(R.id.deskripsi);
        TextView Lokasi = (TextView) findViewById(R.id.lokasimkn);
        TextView Waktu = (TextView) findViewById(R.id.waktumkn);


        ImageView img = (ImageView) findViewById(R.id.pemilik);

        Namamkn.setText(Nama);
        Hargamkn.setText("Rp."+harga+",-");
        Deskripsi.setText(deskripsi);
        Lokasi.setText(lokasi);
        Waktu.setText(jam);


        if(pemilik!= null) {
            Picasso.with(this)
                    .load(pemilik)
                    .error(R.drawable.line)      // optional
                    .into(img);
        }
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
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
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
