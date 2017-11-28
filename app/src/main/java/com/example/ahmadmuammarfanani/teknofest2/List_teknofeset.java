package com.example.ahmadmuammarfanani.teknofest2;

import android.app.Dialog;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class List_teknofeset extends AppCompatActivity {

    private String db_url = "https://teknofest2.firebaseio.com/";
    private ListView listView;
    private FireBaseClient fireBaseClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_teknofeset);

        listView = findViewById(R.id.listView);
        fireBaseClient = new FireBaseClient(this,db_url,listView);
        fireBaseClient.refreshdata();

    }
}
