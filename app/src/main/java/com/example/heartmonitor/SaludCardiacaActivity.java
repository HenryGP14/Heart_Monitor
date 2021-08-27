package com.example.heartmonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SaludCardiacaActivity extends AppCompatActivity {

    //LineChartView chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_saludcardiaca);

        //chart = findViewById(R.id.graficaChartView);
    }

    @Override
    protected void onStart() {
        super.onStart();
        dibujarLineChart();
    }

    private void dibujarLineChart(){
        //Esta lista debe ser un atributo de esta clase
        /*List<PointValue> values = new ArrayList<PointValue>();
        values.add(new PointValue(0, 2));
        values.add(new PointValue(1, 4));
        values.add(new PointValue(2, 10));
        values.add(new PointValue(3, 6));

        //Este tambien debe ser un atributo
        Line line = new Line(values).setColor(Color.WHITE).setCubic(true);
        List<Line> lines = new ArrayList<Line>();
        lines.add(line);

        LineChartData data = new LineChartData();
        data.setLines(lines);

        chart.setLineChartData(data);*/
    }

    public void btnAtras(View view){
        this.finish();
    }

    public void btnConfiguracion(View view){
        Intent puente = new Intent(getApplicationContext(), ConfiguracionActivity.class);
        startActivity(puente);
    }

    public void btnDiaAnterior(View view){
        Toast.makeText(this, "Cambiar al día anterior", Toast.LENGTH_SHORT).show();
    }

    public void btnDiaSiguiente(View view){
        Toast.makeText(this, "Cambiar al día siguiente", Toast.LENGTH_SHORT).show();
    }
}