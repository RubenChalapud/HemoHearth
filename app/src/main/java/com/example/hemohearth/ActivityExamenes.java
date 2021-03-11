package com.example.hemohearth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ActivityExamenes extends AppCompatActivity {
    private Button btnGlucemia, btnHemoglo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examenes);
        btnGlucemia = (Button) findViewById(R.id.btnExaGlucemia);
        btnHemoglo = (Button) findViewById(R.id.btnExaHemoglo);

        final String docu = getIntent().getStringExtra("docu");

        btnGlucemia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Analisis de Azucares en la sangre", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(ActivityExamenes.this, ActivityGluSintomas.class);
                i.putExtra("docu", docu);
                startActivity(i);
            }
        });

        btnHemoglo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Analisis para determinar posible Anemia", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(ActivityExamenes.this, ActivityDatosHemo.class);
                i.putExtra("docu", docu);
                startActivity(i);
            }
        });

    }
}