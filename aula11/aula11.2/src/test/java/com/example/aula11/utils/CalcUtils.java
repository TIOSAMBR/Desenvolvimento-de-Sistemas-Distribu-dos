package com.example.aula11.utils;

import java.util.Collections;
import java.util.List;

public class CalcUtils {

    public static long fatorial(int n) {
        long resultado = 1;
        for (int i = 2; i <= n; i++) {
            resultado *= i;
        }
        return resultado;
    }

    public static double media(List<Double> valores) {
        return valores.stream().mapToDouble(Double::doubleValue).average().orElse(0);
    }

    public static double mediana(List<Double> valores) {
        Collections.sort(valores);
        return valores.get(valores.size() / 2);
    }
}
