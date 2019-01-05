package com.example.nori.logreg;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static EditText EditText_nev, EditText_jelszo;
    private Button Button_belepes, Button_regisztracio;
    private AdatbazisSegito db;
    public static String felhasznalonev;
    public String jelszo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        EditText_nev.setText("");
        EditText_jelszo.setText("");


        Button_belepes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                felhasznalonev = EditText_nev.getText().toString();
                jelszo = EditText_jelszo.getText().toString();

                adatlekeres();
            }
        });

        Button_regisztracio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent regisztracio = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(regisztracio);
                finish();
            }
        });
    }

    public void init(){

        EditText_nev = (EditText) findViewById(R.id.EditText_nev);
        EditText_jelszo = (EditText) findViewById(R.id.EditText_jelszo);
        Button_belepes = (Button) findViewById(R.id.Button_belepes);
        Button_regisztracio = (Button) findViewById(R.id.Button_regisztracio);
        db = new AdatbazisSegito(this);

    }

    public void adatlekeres()
    {
        Cursor eredmeny = db.adatlekerdezes(felhasznalonev, jelszo);
        
        if (eredmeny != null){
            
            if (eredmeny.getCount() > 0){

                Intent regisztracio = new Intent(MainActivity.this, Main3Activity.class);
                startActivity(regisztracio);
                finish();
                
                Toast.makeText(this, "Adat sikeresen lekérve!", Toast.LENGTH_SHORT).show();
            }

            else{
                Toast.makeText(this, "Hibás felhasználónév vagy jelszó!", Toast.LENGTH_SHORT).show();
            }
        }

    }

}
