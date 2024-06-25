package com.example.examenfinal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class DbHelper extends SQLiteOpenHelper {

    private static final int VERSION = 8;
    private static final String NOMBRE = "examenGaizka8.db";
    private static final String USUARIOTABLECREATE = "CREATE TABLE USUARIOS(usuario VARCHAR PRIMARY KEY, contrasena VARCHAR)";
    private static final String CLIENTETABLECREATE = "CREATE TABLE CLIENTES(nombre VARCHAR PRIMARY KEY, telefono VARCHAR, correo VARCHAR)";
    private static final String ARTICULOTABLECREATE = "CREATE TABLE ARTICULOS(articulo VARCHAR PRIMARY KEY, importe VARCHAR, iva INTEGER)";


    public DbHelper(@Nullable Context context) {

        super(context, NOMBRE, null, VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


        sqLiteDatabase.execSQL(USUARIOTABLECREATE);
        sqLiteDatabase.execSQL(CLIENTETABLECREATE);
        sqLiteDatabase.execSQL(ARTICULOTABLECREATE);



    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE USUARIOS IF EXISTS "+USUARIOTABLECREATE);
        sqLiteDatabase.execSQL("DROP TABLE CLIENTES IF EXISTS "+CLIENTETABLECREATE);
        sqLiteDatabase.execSQL("DROP TABLE ARTICULOS IF EXISTS "+ARTICULOTABLECREATE);
        onCreate(sqLiteDatabase);
    }

    public void agregarCliente(String name, String phone, String mail){
        SQLiteDatabase db = getWritableDatabase();
        if(db!=null){
            db.execSQL("INSERT INTO CLIENTES VALUES ('"+name+"','"+phone+"','"+mail+"')");
            db.close();
        }

    }
    public void agregarArticulo(String articulo, String importe, int iva){
        SQLiteDatabase db = getWritableDatabase();
        if(db!=null){
            db.execSQL("INSERT INTO ARTICULOS VALUES ('"+articulo+"','"+importe+"','"+iva+"')");
            db.close();
        }

    }



}