package com.example.hemohearth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityConsultar extends AppCompatActivity {
    private EditText docConsul;
    private Button btnConsulta;
    String nom, ape, eps, cor, pat, tpat, ane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar);

        docConsul = (EditText) findViewById(R.id.editTextConsulDoc);
        btnConsulta = (Button) findViewById(R.id.btnConsultar);

    }

    public void consultaporcodigo(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "hemohearthDB", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String doc = docConsul.getText().toString();
        Cursor fila = bd.rawQuery("select nombre, apellido, eps, correo, patDiabetes, tipoPat, anemia from pacientes where documento=" + doc, null);
        if (fila.moveToFirst()) {
            nom = fila.getString(0);
            ape = fila.getString(1);
            eps = fila.getString(2);
            cor = fila.getString(3);
            pat = fila.getString(4);
            tpat = fila.getString(5);
            ane = fila.getString(6);

            Intent i = new Intent(ActivityConsultar.this, ActivityResConsulta.class);
            i.putExtra("nom", nom);
            i.putExtra("ape", ape);
            i.putExtra("doc", doc);
            i.putExtra("eps", eps);
            i.putExtra("cor", cor);
            i.putExtra("pat", pat);
            i.putExtra("tpat", tpat);
            i.putExtra("ane", ane);
            startActivity(i);
        } else
            Toast.makeText(this, "No existe un paciente con dicho documento", Toast.LENGTH_SHORT).show();
        bd.close();
    }
}