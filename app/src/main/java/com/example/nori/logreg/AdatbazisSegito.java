package com.example.nori.logreg;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AdatbazisSegito extends SQLiteOpenHelper {

    //ADATBÁZIS FELÉPÍTÉSE

    public static final String DATABASE_NAME = "Felhasznalok.db";
    public static final String TABLE_NAME = "Felhasznalo";

    public static final String COL_1 = "id";
    public static final String COL_2 = "Felhasznalo_nev";
    public static final String COL_3 = "Jelszo";
    public static final String COL_4 = "Teljes_nev";
    public static final String COL_5 = "Telefonszam";

    //KONSTRUKTOR FELVÉTELE

    public AdatbazisSegito(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
    }

    //LÉTREHOZZUK A TÁBLÁT ÉS A BENNE LÉVŐ OSZLOPOKHOZ TÍPUST ADUNK

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + "(id INTEGER PRIMARY KEY AUTOINCREMENT, " + "Felhasznalo_nev, Jelszo, Teljes_nev, Telefonszam)");
    }

    //DOBJA EL A TÁBLÁT HA MÁR LÉTEZIK ILYEN

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    //ADATFELVÉTEL

    public boolean adatfelvetel(String Felhasznalo_nev, String Jelszo, String Teljes_nev,String Telefonszam) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, Felhasznalo_nev);
        contentValues.put(COL_3, Jelszo);
        contentValues.put(COL_4, Teljes_nev);
        contentValues.put(COL_5, Telefonszam);

        long eredmeny = db.insert(TABLE_NAME, null, contentValues);

        //SIKERTELEN ADATFELVÉTEL

        if (eredmeny == -1) {
            return false;
        }

        //SIKERES ADATFELVÉTEL

        else{
            return true;
        }
    }

    //FELHASZNÁLÓNÉV ÉS JELSZÓ LEKÉRÉSE

    public Cursor adatlekerdezes(String felhasznalonev, String jelszo)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor eredmeny = db.rawQuery("SELECT *FROM " + TABLE_NAME + " WHERE " + COL_2 + "=? AND " + COL_3 + "=?", new String[]{felhasznalonev, jelszo});
        return eredmeny;
    }

    public Cursor felhasznalonevLekerdezes(String felhasznalonev)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor eredmeny = db.rawQuery("SELECT *FROM " + TABLE_NAME + " WHERE " + COL_2 + "=? ", new String[]{felhasznalonev});
        return eredmeny;
    }

}
