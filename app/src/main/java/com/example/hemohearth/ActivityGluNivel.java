package com.example.hemohearth;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityGluNivel extends AppCompatActivity {
    public EditText nivel;
    public Button btnvalidar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glu_nivel);
        nivel = (EditText) findViewById(R.id.editTextNivelGlu);
        btnvalidar = (Button) findViewById(R.id.btnTipoCDiab);
    }

    public void ValidarTipoCD (View view){
        String TipoCD = "";
        String niv = nivel.getText().toString();
        double N = Double.parseDouble(niv);

        if((N>=7.0) && (N<13.8)){
            TipoCD = "HIPERGLICEMIA AISLADA";
        }
        if((N>=13.8) && (N<=33)){
            TipoCD = "CETOACIDOSIS DIABÉTICA";
        }
        if((N>33)){
            TipoCD = "ESTADO HIPEROSMOLAR HIPERGLUCÉMICO NO CETÓSICO";
        }
        eleccion(TipoCD);
        modificacionDB(TipoCD);
    }

    public void eleccion (final String cadena){
        AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
        alertbox.setMessage("El resultado que tipo de cuadro diabético presenta: "+cadena);
        alertbox.setNeutralButton("Ver Recomendaciones", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent x = new Intent(ActivityGluNivel.this, ActivityRecomendacion.class);
                x.putExtra("tipo", cadena);
                startActivity(x);
            }
        });
        alertbox.show();
    }

    public void modificacionDB(String v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "hemohearthDB", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String docu = getIntent().getStringExtra("docu");
        String pato = getIntent().getStringExtra("pat");

        ContentValues registro = new ContentValues();
        registro.put("patDiabetes", pato);
        registro.put("tipoPat", v);

        int cant = bd.update("pacientes", registro, "documento=" + docu, null);
        bd.close();
        if (cant == 1)
            Toast.makeText(this, "se almacenó los datos", Toast.LENGTH_SHORT)
                    .show();
        else
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
    }

}