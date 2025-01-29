package com.example.aula11.service;

import com.example.aula11.utils.CalcUtils;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CalcService {

    public double soma(double a, double b) {
        return a + b;
    }

    public double subtracao(double a, double b) {
        return a - b;
    }

    public double multiplicacao(double a, double b) {
        return a * b;
    }

    public double divisao(double a, double b) {
        return a / b;
    }

    public double raiz(double a) {
        return Math.sqrt(a);
    }

    public double expoente(double base, double exp) {
        return Math.pow(base, exp);
    }

    public double inverso(double a) {
        return 1 / a;
    }

    public double modulo(double a) {
        return Math.abs(a);
    }

    public long fatorial(int a) {
        return CalcUtils.fatorial(a);
    }

    public double media(List<Double> valores) {
        return CalcUtils.media(valores);
    }

    public double mediana(List<Double> valores) {
        return CalcUtils.mediana(valores);
    }
}
