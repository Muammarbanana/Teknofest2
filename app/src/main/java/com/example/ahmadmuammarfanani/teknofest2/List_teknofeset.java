package com.example.ahmadmuammarfanani.teknofest2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.widget.ListView;

import com.example.ahmadmuammarfanani.teknofest2.tambahan.FireBaseClient;

public class List_teknofeset extends AppCompatActivity {

    private String db_url = "https://teknofest2.firebaseio.com/";
    private ListView listView;
    private FireBaseClient fireBaseClient;
    private CardView cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_teknofeset);

        listView = findViewById(R.id.listView);
        fireBaseClient = new FireBaseClient(this,db_url,listView);
        fireBaseClient.refreshdata();

    }
}
