package com.example.undipssql;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    UniversityDB myDB;
    RecyclerView recyclerView;
    ArrayList<String> universityId, universityName, universityAddress;
    FloatingActionButton addButton;

    AdapterView adapterView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        recyclerView = findViewById(R.id.recyclerView);
        addButton = findViewById(R.id.addButton);

        myDB = new UniversityDB(MainActivity.this);
        universityId = new ArrayList<>();
        universityName = new ArrayList<>();
        universityAddress = new ArrayList<>();

        View();

        adapterView = new AdapterView(MainActivity.this, universityId, universityName, universityAddress);
        recyclerView.setAdapter(adapterView);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this, AddUniversity.class );
                startActivity(intent);
            }
        });

    }
    void View(){
        Cursor cursor = myDB.readData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "Data is empty", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){
                universityId.add(cursor.getString(0));
                universityName.add(cursor.getString(1));
                universityAddress.add(cursor.getString(2));
            }
        }
    }
}