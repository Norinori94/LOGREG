package com.example.nori.logreg;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    private EditText EditText_felhasznaloNev, EditText_jelszo1, EditText_jelszo2, EditText_teljesNev, EditText_telefonszam;
    private Button Button_regisztráció;
    private AdatbazisSegito db;
    private String felhasznalonev;
    private String jelszo1;
    private String jelszo2;
    private String teljesNev;
    private String telefonszam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        init();

        Button_regisztráció.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                adatok();

                if(     !(felhasznalonev.equals("")) &&
                        !(jelszo1.equals("")) &&
                        !(jelszo2.equals("")) &&
                        !(teljesNev.equals("")) &&
                        !(telefonszam.equals("")))
                {
                        if(jelszo1.equals(jelszo2) && !(szabadFelhasznalonev())){

                            adatfelvetel();
                            Toast.makeText(Main2Activity.this, "Adatrögzítés sikeres!", Toast.LENGTH_SHORT).show();
                            Intent regisztracio = new Intent(Main2Activity.this, MainActivity.class);
                            startActivity(regisztracio);
                            finish();
                        }
                        else if (szabadFelhasznalonev()){
                            Toast.makeText(Main2Activity.this, "Már van ilyen felhasználónév!", Toast.LENGTH_SHORT).show();
                        }

                        else if (!(jelszo1.equals(jelszo2))){
                            Toast.makeText(Main2Activity.this, "A jelszavaknak egyezniük kell!", Toast.LENGTH_LONG).show();
                        }
                }
                
                else {
                    Toast.makeText(Main2Activity.this, "Minden mezőt ki kell tölteni!", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    public void init(){

        EditText_felhasznaloNev = (EditText) findViewById(R.id.EditText_felhasznaloNev);
        EditText_jelszo1 = (EditText) findViewById(R.id.EditText_jelszo1);
        EditText_jelszo2 = (EditText) findViewById(R.id.EditText_jelszo2);
        EditText_teljesNev = (EditText) findViewById(R.id.EditText_teljesNev);
        EditText_telefonszam = (EditText) findViewById(R.id.EditText_telefonszam);
        Button_regisztráció = (Button) findViewById(R.id.Button_regisztracio);
        db = new AdatbazisSegito(this);
    }

    public void adatok(){

        felhasznalonev = EditText_felhasznaloNev.getText().toString();
        jelszo1 = EditText_jelszo1.getText().toString();
        jelszo2 = EditText_jelszo2.getText().toString();
        teljesNev = EditText_teljesNev.getText().toString();
        telefonszam = EditText_telefonszam.getText().toString();
    }

    public boolean adatfelvetel(){

        String felhasznaloNev = EditText_felhasznaloNev.getText().toString();
        String jelszo1 = EditText_jelszo1.getText().toString();
        String teljesNev = EditText_teljesNev.getText().toString();
        String telefonszam = EditText_telefonszam.getText().toString();

        boolean eredmeny = db.adatfelvetel(felhasznaloNev, jelszo1, teljesNev, telefonszam);

        return eredmeny;
    }

    public boolean szabadFelhasznalonev()
    {
        Cursor eredmeny = db.felhasznalonevLekerdezes(felhasznalonev);
        boolean egyezes = false;

        if(eredmeny != null && eredmeny.getCount() > 0){
            egyezes = true;
        }

        return egyezes;

    }

}
