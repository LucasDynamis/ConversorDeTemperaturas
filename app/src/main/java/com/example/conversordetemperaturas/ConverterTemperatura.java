package com.example.conversordetemperaturas;

public class ConverterTemperatura {
    public static double converteTemperatura(double temp, String inputUnit, String outputUnit) {
        double celsius = temp;

        //Converte a entrada para Celsius
        if (inputUnit.equals("Fahrenheit")) {
            celsius = (temp - 32) * 5.0 / 9.0;
        } else if (inputUnit.equals("Kelvin")) {
            celsius = temp - 273.15;
        }


        // Converte de Celsius para a unidade de saída
        if (outputUnit.equals("Fahrenheit")) {
            return celsius * 9.0 / 5.0 + 32;
        } else if (outputUnit.equals("Kelvin")) {
            return celsius + 273.15;
        } else {
            return celsius;
        }
    }
}