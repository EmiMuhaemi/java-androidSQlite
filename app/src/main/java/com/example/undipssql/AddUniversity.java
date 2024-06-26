package com.example.undipssql;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class AddUniversity extends AppCompatActivity {

    EditText addId, addName, addAddress;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_kampus);

        addId = findViewById(R.id.addId);
        addName = findViewById(R.id.addName);
        addAddress = findViewById(R.id.addAddress);

        btnAdd = findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UniversityDB myDB = new UniversityDB(AddUniversity.this);
                myDB.addData(Integer.valueOf(addId.getText().toString().trim()),
                        addName.getText().toString().trim(),
                        addAddress.getText().toString().trim());
                Intent intent= new Intent(AddUniversity.this, MainActivity.class );
                startActivity(intent);

            }
        });


    }
}