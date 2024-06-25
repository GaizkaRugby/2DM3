package com.example.examenfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class Actividad_articulo extends AppCompatActivity {
    Button btnRegistrar;
    RadioButton iva4, iva10, iva21;
    EditText articulo,importe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_articulo);

        btnRegistrar=findViewById(R.id.btnRegistrar);
        iva4=findViewById(R.id.rdb4);
        iva10=findViewById(R.id.rdb10);
        iva21=findViewById(R.id.rdb21);
        articulo=findViewById(R.id.edtArticulo);
        importe=findViewById(R.id.edtImporte);
        final DbHelper  dbHelper = new DbHelper(getApplicationContext());

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int iva=0;
                if(iva4.isChecked()){
                    iva=4;
                }else if(iva10.isChecked()){
                    iva=10;
                }else if(iva21.isChecked()){
                    iva=21;
                }
                if (iva!=0){
                    dbHelper.agregarArticulo(articulo.getText().toString(), importe.getText().toString(), iva);

                    Toast.makeText(Actividad_articulo.this, "Linea Insertada", Toast.LENGTH_SHORT).show();
                } else{
                    Toast.makeText(Actividad_articulo.this, "Introduce el IVA.", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}