package com.example.nori.logreg;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {

    private TextView TextView_udvozlet;
    private Button Button_kijelentkezes;
    private AdatbazisSegito db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        init();
        TextView_udvozlet.setText("Üvdözöllek: " + MainActivity.felhasznalonev);

        Button_kijelentkezes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent kijelentkezes = new Intent(Main3Activity.this, MainActivity.class);
                startActivity(kijelentkezes);
                finish();
            }
        });
    }

    public void init(){

        TextView_udvozlet = (TextView) findViewById(R.id.TextView_udvozlet);
        Button_kijelentkezes = (Button) findViewById(R.id.Button_kijelentkezes);
        db = new AdatbazisSegito(this);
    }
}
