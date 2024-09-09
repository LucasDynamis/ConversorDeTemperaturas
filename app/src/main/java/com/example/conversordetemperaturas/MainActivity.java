package com.example.conversordetemperaturas;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private EditText enttemp;
    private RadioButton btcelsius, btfahrenheit, btkelvin;
    private Spinner spinnerout;
    private Button btcalcular;
    private TextView resultado, infoAdicional;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        enttemp = findViewById(R.id.enttemp);
        btcelsius = findViewById(R.id.btcelsius);
        btfahrenheit = findViewById(R.id.btfahrenheit);
        btkelvin = findViewById(R.id.btkelvin);
        spinnerout = findViewById(R.id.spinnerout);
        btcalcular = findViewById(R.id.btcalcular);
        resultado = findViewById(R.id.resultado);
        infoAdicional = findViewById(R.id.infoAdicional);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.temperature_units,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerout.setAdapter(adapter);


        btcalcular.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try {
                    double temperature = Double.parseDouble(enttemp.getText().toString());
                    String inputUnit = btcelsius.isChecked() ? "Celsius" :
                            btfahrenheit.isChecked() ? "Fahrenheit" : "Kelvin";
                    String outputUnit = spinnerout.getSelectedItem().toString();
                    double result = ConverterTemperatura.converteTemperatura(temperature, inputUnit, outputUnit);
                    String formattedResult = String.format("%.2f", result);
                    resultado.setText("Temperatura convertida: " + formattedResult + "° " + outputUnit);
                    updateAdditionalInfo(result, outputUnit);
                } catch (NumberFormatException e) {
                    enttemp.setError("Por favor, insira um valor válido.");
                }
            }
        });
    }

    private void updateAdditionalInfo(double temp, String unit) {
        String info = "";
        switch (unit) {
            case "Celsius":
                info = temp >= 100 ? "A água ferve." : temp <= 0 ? "A água congela." : "Temperatura normal.";
                break;
            case "Fahrenheit":
                info = temp >= 212 ? "A água ferve." : temp <= 32 ? "A água congela." : "Temperatura normal.";
                break;
            case "Kelvin":
                info = temp >= 373.15 ? "A água ferve." : temp <= 273.15 ? "A água congela." : "Temperatura normal.";
                break;
        }
        infoAdicional.setText(info);
    }
}