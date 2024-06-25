package com.example.examenfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Actividad_Segunda_Gaizka extends AppCompatActivity {
    Button btnArt,btnCli;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_segunda_gaizka);
        btnArt=findViewById(R.id.btnArt);
        btnCli=findViewById(R.id.btnCli);
        btnArt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirArticulos();
            }
        });
        btnCli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirClientes();
            }
        });
    }
    public void abrirClientes() {
        Intent intent= new Intent(this, Actividad_cliente.class);
        startActivity(intent);
    }
    public void abrirArticulos() {
        Intent intent= new Intent(this, Actividad_articulo.class);
        startActivity(intent);
    }
}