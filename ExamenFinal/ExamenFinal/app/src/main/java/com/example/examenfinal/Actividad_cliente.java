package com.example.examenfinal;

import androidx.appcompat.app.AppCompatActivity;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Actividad_cliente extends AppCompatActivity {
    Button btnIns, btnDel, btnAct, btnConsultar, btnSig;
    EditText edtNombre, edtTel, edtMail;
    TextView txtNombre,txtTel,txtMail;
    int cont;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_cliente);
        btnIns=findViewById(R.id.btnInsertar);
        btnDel=findViewById(R.id.btnBorrar);
        btnAct=findViewById(R.id.btnActualizar);
        btnConsultar=findViewById(R.id.btnColsultar);
        btnSig=findViewById(R.id.btnSiguiente);
        edtNombre=findViewById(R.id.edtNombre);
        edtTel=findViewById(R.id.edtTelefono);
        edtMail=findViewById(R.id.edtCorreo);
        txtNombre=findViewById(R.id.txtNom);
        txtTel=findViewById(R.id.txtTel);
        txtMail=findViewById(R.id.txtMail);
        cont=0;


        final DbHelper  dbHelper = new DbHelper(getApplicationContext());

        btnIns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.agregarCliente(edtNombre.getText().toString(), edtTel.getText().toString(), edtMail.getText().toString());

                Toast.makeText(Actividad_cliente.this, "Linea Insertada", Toast.LENGTH_SHORT).show();
            }
        });
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("DELETE FROM CLIENTES WHERE nombre='"+edtNombre.getText().toString()+"'");
                Toast.makeText(Actividad_cliente.this, "Linea eliminada correctamente.", Toast.LENGTH_SHORT).show();
            }
        });

        btnAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("UPDATE CLIENTES SET telefono='"+edtTel.getText().toString()+"', correo='"+edtMail.getText().toString()+"' WHERE nombre='"+edtNombre.getText().toString()+"'");
                Toast.makeText(Actividad_cliente.this, "Linea actualizada correctamente.", Toast.LENGTH_SHORT).show();

            }
        });

        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                Cursor datos = db.rawQuery("SELECT * FROM CLIENTES",null);
                String cor,nom, tel;

                cont=0;
                datos.moveToFirst();

                nom=datos.getString(0);
                tel=datos.getString(1);
                cor=datos.getString(2);


                txtNombre.setText(nom);
                txtTel.setText(tel);
                txtMail.setText(cor);

                datos.close();
            }
        });
        btnSig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                Cursor da = db.rawQuery("SELECT * FROM CLIENTES",null);
                String cor,nom, tel;
                int num;
                cont++;
                da.moveToPosition(cont);
                num=da.getCount();

                if (cont<num) {
                    nom = da.getString(0);
                    tel = da.getString(1);
                    cor = da.getString(2);



                    txtNombre.setText(nom);
                    txtTel.setText(tel);
                    txtMail.setText(cor);
                }else{
                    Toast.makeText(Actividad_cliente.this, "No hay mas lineas.", Toast.LENGTH_SHORT).show();
                }
                da.close();
            }
        });

    }
}