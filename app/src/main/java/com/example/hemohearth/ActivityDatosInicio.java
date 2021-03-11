package com.example.hemohearth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityDatosInicio extends AppCompatActivity {
    private EditText ETnombre, ETapellido, ETdocumento, ETeps, ETcorreo;
    private Button btnContinuar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_inicio);

        ETnombre = (EditText) findViewById(R.id.editTextNombre);
        ETapellido = (EditText) findViewById(R.id.editTextApellido);
        ETdocumento = (EditText) findViewById(R.id.editTextDocumento);
        ETeps = (EditText) findViewById(R.id.editTextEPS);
        ETcorreo = (EditText) findViewById(R.id.editTextCorreo);

        btnContinuar = (Button) findViewById(R.id.btnContinuarDatos);
    }

    public void almacenar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "hemohearthDB", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String nom = ETnombre.getText().toString();
        String ape = ETapellido.getText().toString();
        String doc = ETdocumento.getText().toString();
        String eps = ETeps.getText().toString();
        String cor = ETcorreo.getText().toString();

        ContentValues registro = new ContentValues();

        registro.put("documento", doc);
        registro.put("nombre", nom);
        registro.put("apellido", ape);
        registro.put("eps", eps);
        registro.put("correo", cor);
        //registro.put("patDiabetes", "nada");
        //registro.put("tipoPat", "nada");
        //registro.put("anemia", "nada");

        bd.insert("pacientes", null, registro);
        bd.close();
        ETdocumento.setText("");
        ETnombre.setText("");
        ETapellido.setText("");
        ETeps.setText("");
        ETcorreo.setText("");
        Toast.makeText(this, "Se cargaron los datos del paciente",Toast.LENGTH_SHORT).show();
        Intent i = new Intent(ActivityDatosInicio.this, ActivityExamenes.class);
        i.putExtra("docu", doc);
        startActivity(i);

    }
}