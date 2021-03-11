package com.example.hemohearth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ActivityDatosHemo extends AppCompatActivity {
    private RadioGroup RGMesAño, RGSexo;
    private EditText edad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_hemo);
        edad = (EditText) findViewById(R.id.editTextEdad);
        RGMesAño = (RadioGroup) findViewById(R.id.RbtnMesAño);
        RGSexo = (RadioGroup) findViewById(R.id.RbtnSexo);
    }

    public void validar (View v){
        String sexo = "";
        String MesAño = "";
        String ed = edad.getText().toString();
        double Edad = Double.parseDouble(ed);
        String docu = getIntent().getStringExtra("docu");

        if(RGSexo.getCheckedRadioButtonId() == R.id.radioButtonMascu){
            sexo = "Masculino";
        }
        if(RGSexo.getCheckedRadioButtonId() == R.id.radioButtonFemeni){
            sexo = "Femenino";
        }

        if (RGMesAño.getCheckedRadioButtonId() == R.id.radioButtonMeses) {
            if (Edad<=12){
                Intent i = new Intent(ActivityDatosHemo.this, ActivityHemoNivel.class);
                i.putExtra("edad", ed+"");
                i.putExtra("tiempo", "meses");
                i.putExtra("sexo", sexo);
                i.putExtra("docu", docu);
                startActivity(i);
            }else {
                Toast.makeText(getApplicationContext(), "Error en los Datos de Edad", Toast.LENGTH_SHORT).show();
            }
        }else{
            Intent i = new Intent(ActivityDatosHemo.this, ActivityHemoNivel.class);
            i.putExtra("edad", ed+"");
            i.putExtra("tiempo", "años");
            i.putExtra("sexo", sexo);
            i.putExtra("docu", docu);
            startActivity(i);
        }
    }
}