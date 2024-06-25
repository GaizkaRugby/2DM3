package com.example.examenfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnValidar;
    EditText edtUsuario, edtContra;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnValidar=findViewById(R.id.btnvalidar);
        edtUsuario=findViewById(R.id.edtUsuario);
        edtContra=findViewById(R.id.edtPass);
        final DbHelper  dbHelper = new DbHelper(getApplicationContext());



        btnValidar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usuariodb="admin";
                String passdb="1234";
                String user,pass;
                user=edtUsuario.getText().toString();
                pass=edtContra.getText().toString();
                if(user.equals(usuariodb)){
                    if(pass.equals(passdb)){
                        abrirActivity2();
                    }else{
                        Toast.makeText(MainActivity.this, "Contraseña incorrecta.", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(MainActivity.this, "Usuario incorrecto.", Toast.LENGTH_SHORT).show();
                }

            }

        });


    }
    public void abrirActivity2() {
        Intent intent= new Intent(this, Actividad_Segunda_Gaizka.class);
        startActivity(intent);
    }
}