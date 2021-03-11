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

public class ActivityHemoNivel extends AppCompatActivity {
    private EditText nivelH;
    private Button btnVerificar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hemo_nivel);
        nivelH = (EditText) findViewById(R.id.editTextNivelHemo);
        btnVerificar = (Button) findViewById(R.id.btnVerificarAnemia);

    }

    public void validar(View view){
        String Resultado = "";
        String mensaje = "";
        String niv = nivelH.getText().toString();
        double N = Double.parseDouble(niv);
        String edad = getIntent().getStringExtra("edad");
        double E = Double.parseDouble(edad);
        String mesaño = getIntent().getStringExtra("tiempo");
        String sexo = getIntent().getStringExtra("sexo");

        if (mesaño.equals("meses")){
            if(E>=0 && E<=1){
                if(N<13.0){
                    Resultado = "Positivo";
                }
                if(N>=13.0 && N<= 26.0){
                    Resultado = "Negativo";
                }
            }
            if(E>1 && E<=6){
                if(N<10.0){
                    Resultado = "Positivo";
                }
                if(N>=10.0 && N<= 18.0){
                    Resultado = "Negativo";
                }
            }
            if(E>6 && E<=12){
                if(N<11.0){
                    Resultado = "Positivo";
                }
                if(N>=11.0 && N<= 15.0){
                    Resultado = "Negativo";
                }
            }
        }
        if (mesaño.equals("años")){
            if(E>=1 && E<=5){
                if(N<11.5){
                    Resultado = "Positivo";
                }
                if(N>=11.5 && N<= 15.0){
                    Resultado = "Negativo";
                }
            }
            if(E>=5 && E<=10){
                if(N<12.6){
                    Resultado = "Positivo";
                }
                if(N>=12.6 && N<= 15.5){
                    Resultado = "Negativo";
                }
            }

            if (E>15 && sexo.equals("Femenino")){
                if(N<12.0){
                    Resultado = "Positivo";
                }
                if(N>=12.0 && N<= 16.0){
                    Resultado = "Negativo";
                }
            }
            if (E>15 && sexo.equals("Masculino")){
                if(N<14.0){
                    Resultado = "Positivo";
                }
                if(N>=14.0 && N<= 18.0){
                    Resultado = "Negativo";
                }
            }
        }

        if(Resultado.equals("Positivo")){
            mensaje = "Resultado: "+Resultado+ ". "+" El paciente presenta Anemia.";
        }
            if(Resultado.equals("Negativo")){
            mensaje = "Resultado: "+Resultado+". "+ " El paciente NO presenta Anemia.";
        }

            eleccion(mensaje);
            modificacionDB(Resultado);
    }

    public void eleccion (String cadena){
        AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
        alertbox.setMessage(cadena);
        alertbox.setNeutralButton("Salir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent x = new Intent(ActivityHemoNivel.this, MainActivity.class);
                startActivity(x);
            }
        });
        alertbox.show();
    }

    public void modificacionDB(String v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "hemohearthDB", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String docu = getIntent().getStringExtra("docu");

        ContentValues registro = new ContentValues();
        registro.put("anemia", v);

        int cant = bd.update("pacientes", registro, "documento=" + docu, null);
        bd.close();
        if (cant == 1)
            Toast.makeText(this, "se almacenó los datos", Toast.LENGTH_SHORT)
                    .show();
        else
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
    }

}