package com.example.hemohearth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ActivityRecomendacion extends AppCompatActivity {
    private Button btnSalir;
    private TextView ViewRecomend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recomendacion);
        btnSalir = (Button) findViewById(R.id.btnSalirGlu);
        ViewRecomend = (TextView) findViewById(R.id.textViewRecomenda);

        String Tipo = getIntent().getStringExtra("tipo");
        String reco = "";

        if (Tipo.equals("HIPERGLICEMIA AISLADA")){
            reco = "Indicar glucemia en ayunas y TGP en pacientes sin diagnóstico.\n" +
                    "Si deshidratación, rehidratación oral o EV según las demandas.\n" +
                    "Reevaluar conducta terapéutica en diabéticos y cumplimiento de los pilares.\n" +
                    "Reevaluar dosis de hipoglucemiantes.";
        }

        if (Tipo.equals("CETOACIDOSIS DIABÉTICA")){
            reco = "Coordinar traslado y comenzar tratamiento.\n" +
                    "Hidratación con Solución salina 40 ml/Kg en las primeras 4 horas. 1-2 L la primera hora.\n" +
                    "Administrar potasio al restituirse la diuresis o signos de hipopotasemia (depresión del ST, Onda U ≤ 1mv, ondas U≤ T).\n" +
                    "Evitar insulina hasta desaparecer signos de hipopotasemia.\n" +
                    "Administrar insulina simple 0,1 U/kg EV después de hidratar.";
        }

        if (Tipo.equals("ESTADO HIPEROSMOLAR HIPERGLUCÉMICO NO CETÓSICO")){
            reco = "Coordinar traslado y comenzar tratamiento.\n" +
                    "Hidratación con Solución Salina 10-15 ml/Kg/h hasta conseguir estabilidad hemodinámica.\n" +
                    "Administrar potasio al restituirse la diuresis o signos de hipopotasemia (depresión del ST, Onda U ≤ 1mv, ondas U≤ T).";
        }

        ViewRecomend.setText("Se presenta el tipo de cuadro Diabetico: "+Tipo+" de manera que se recomienda:\n\n" + reco);

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent x = new Intent(ActivityRecomendacion.this, MainActivity.class);
                startActivity(x);
            }
        });


    }
}