package com.ctis487.ifdatabasenotgiven;



import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper dbHelper;
    private GestureDetectorCompat gestureDetector;
    EditText etId, etName, etSup;
    Button btnFind,btnAdd,btnUpdate;

    String name, supporter;
    int id;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.activity_main);

        etId = findViewById(R.id.MainTeamId);
        etName = findViewById(R.id.etNameTeam);
        etSup = findViewById(R.id.etSupporter);
        btnFind = findViewById(R.id.btnFind);
        btnAdd = findViewById(R.id.btnAdd);
        btnUpdate = findViewById(R.id.btnUpdate);


        dbHelper = new DatabaseHelper(this);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTeamDetail();
                boolean res = TeamTable.insert(dbHelper, id, name, supporter);
                if(res) {
                    Toast.makeText(getBaseContext(), "insert done", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getBaseContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTeamDetail();
                boolean res = TeamTable.update(dbHelper, id, name, supporter);
                if(res) {
                    Toast.makeText(MainActivity.this, "update done", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FindActivity.class);
                startActivity(intent);
            }
        });


    }

    public void getTeamDetail(){
        id = Integer.parseInt(etId.getText().toString());
        name = etName.getText().toString();
        supporter = etSup.getText().toString();
    }

}