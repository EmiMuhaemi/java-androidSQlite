package com.example.undipssql;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class DetailUniversity extends AppCompatActivity {

    EditText addId, addName, addAddress;
    Button btnUpdate, btnDelete;

    String id, name, address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail_kampus);

        addId = findViewById(R.id.addId);
        addName = findViewById(R.id.addName);
        addAddress = findViewById(R.id.addAddress);

        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);

        detail();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UniversityDB myDB = new UniversityDB(DetailUniversity.this);

                id = addId.getText().toString().trim();
                name = addName.getText().toString().trim();
                address = addAddress.getText().toString().trim();


                myDB.updateData(id, name, address);

                Intent intent= new Intent(DetailUniversity.this, MainActivity.class );
                startActivity(intent);

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirm();

            }
        });


    }

    void detail() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("name") && getIntent().hasExtra("address")) {

            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            address = getIntent().getStringExtra("address");


            addId.setText(id);
            addName.setText(name);
            addAddress.setText(address);


        } else {
            Toast.makeText(this, "Data is empty", Toast.LENGTH_SHORT).show();
        }
    }

    void confirm(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + name + " ?");
        builder.setMessage("Are you sure to delete " + name + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                UniversityDB mydb = new UniversityDB(DetailUniversity.this);
                mydb.deleteData(id);
                Intent beck= new Intent(DetailUniversity.this, MainActivity.class );
                startActivity(beck);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}
