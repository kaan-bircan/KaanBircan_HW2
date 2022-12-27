package com.ctis487.ifdatabasenotgiven;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class FindActivity extends AppCompatActivity {

    RecyclerView recyclerContact;
    EditText etKey;
    ImageButton btnImgFind;

    DatabaseHelper dbHelper;
    ArrayList<Team> TeamList;
    CustomRecyclerViewAdapter adapter;

    String key="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);

        dbHelper = new DatabaseHelper(this);


        recyclerContact = findViewById(R.id.recylerContact);
        etKey = findViewById(R.id.etKey);
        btnImgFind = findViewById(R.id.btnImgFind);

        LinearLayoutManager layout = new LinearLayoutManager(this);
        layout.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerContact.setLayoutManager(layout);

        TeamList = TeamTable.getAllTeam(dbHelper);

        adapter = new CustomRecyclerViewAdapter(this, TeamList);
        recyclerContact.setAdapter(adapter);

        btnImgFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                key = etKey.getText().toString();

                TeamList.clear();
                TeamList.addAll(TeamTable.findContact(dbHelper, key));
                Log.d("DATABASE OPERATIONS", TeamTable.findContact(dbHelper, key ).toString());
                adapter.notifyDataSetChanged();

            }
        });
    }

}