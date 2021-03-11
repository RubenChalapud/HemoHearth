package com.example.hemohearth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ActivityGluSintomas extends AppCompatActivity {
    private Button btnAnalisis;
    private RadioGroup opcionesSintomas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glu_sintomas);
        btnAnalisis = (Button) findViewById(R.id.btnAnalisisGlu);
        opcionesSintomas = (RadioGroup) findViewById(R.id.OpcionesSintomas);

    }


    public void validarSintomas (View v){
        String mensaje = "";
        String docu = getIntent().getStringExtra("docu");
        String pato = "";
        if (opcionesSintomas.getCheckedRadioButtonId() == R.id.radioButtonConSintomas) {
            pato = "Presenta patologías diabéticas";
            mensaje = "Presenta sintomas patológicos, se debera realizar examen de glicemia.";
            Intent i = new Intent(ActivityGluSintomas.this, ActivityGluNivel.class);
            i.putExtra("docu", docu);
            i.putExtra("pat", pato);
            startActivity(i);
        }
        if (opcionesSintomas.getCheckedRadioButtonId() == R.id.radioButtonSinSintomas) {
            pato = "No presenta";
            mensaje = "No presenta sintomas patológicos, no se realiza el examen de glicemia.";
            modificacionDB(pato);
        }
        Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();
    }

    public void modificacionDB(String v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "hemohearthDB", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String docu = getIntent().getStringExtra("docu");

        ContentValues registro = new ContentValues();
        registro.put("patDiabetes", v);
        registro.put("tipoPat", "No aplica");

        int cant = bd.update("pacientes", registro, "documento=" + docu, null);
        bd.close();
        if (cant == 1)
            Toast.makeText(this, "se almacenó los datos", Toast.LENGTH_SHORT)
                    .show();
        else
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
    }

}