package com.example.hemohearth;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ActivityResConsulta extends AppCompatActivity {
    public TextView resnom, resape, rescod, reseps, rescorr, respatdia, restipat, resane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res_consulta);

        resnom = (TextView) findViewById(R.id.textViewResNombre);
        resape = (TextView) findViewById(R.id.textViewResApellido);
        rescod = (TextView) findViewById(R.id.textViewResDoc);
        reseps = (TextView) findViewById(R.id.textViewResEps);
        rescorr = (TextView) findViewById(R.id.textViewResCorreo);
        respatdia = (TextView) findViewById(R.id.textViewResPatDia);
        restipat = (TextView) findViewById(R.id.textViewResTipPat);
        resane = (TextView) findViewById(R.id.textViewResAne);

        String nom = getIntent().getStringExtra("nom");
        String ape = getIntent().getStringExtra("ape");
        String doc = getIntent().getStringExtra("doc");
        String eps = getIntent().getStringExtra("eps");
        String cor = getIntent().getStringExtra("cor");
        String pat = getIntent().getStringExtra("pat");
        String tpat = getIntent().getStringExtra("tpat");
        String ane = getIntent().getStringExtra("ane");

        resnom.setText(nom);
        resape.setText(ape);
        rescod.setText(doc);
        reseps.setText(eps);
        rescorr.setText(cor);
        respatdia.setText(pat);
        restipat.setText(tpat);
        resane.setText(ane);

    }
}