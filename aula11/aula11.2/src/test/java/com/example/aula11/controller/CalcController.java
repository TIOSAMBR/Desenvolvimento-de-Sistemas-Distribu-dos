package com.example.aula11.controller;

import com.example.aula11.service.CalcService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/calc")
public class CalcController {
    
    private final CalcService calcService;

    public CalcController(CalcService calcService) {
        this.calcService = calcService;
    }

    @GetMapping("/soma")
    public ResponseEntity<Double> soma(@RequestParam double a, @RequestParam double b) {
        return ResponseEntity.ok(calcService.soma(a, b));
    }

    @GetMapping("/subtracao")
    public ResponseEntity<Double> subtracao(@RequestParam double a, @RequestParam double b) {
        return ResponseEntity.ok(calcService.subtracao(a, b));
    }

    @GetMapping("/multiplicacao")
    public ResponseEntity<Double> multiplicacao(@RequestParam double a, @RequestParam double b) {
        return ResponseEntity.ok(calcService.multiplicacao(a, b));
    }

    @GetMapping("/divisao")
    public ResponseEntity<?> divisao(@RequestParam double a, @RequestParam double b) {
        if (b == 0) return ResponseEntity.badRequest().body("Erro: Divisão por zero!");
        return ResponseEntity.ok(calcService.divisao(a, b));
    }

    @GetMapping("/raiz")
    public ResponseEntity<Double> raiz(@RequestParam double a) {
        return ResponseEntity.ok(calcService.raiz(a));
    }

    @GetMapping("/expoente")
    public ResponseEntity<Double> expoente(@RequestParam double base, @RequestParam double exp) {
        return ResponseEntity.ok(calcService.expoente(base, exp));
    }

    @GetMapping("/inverso")
    public ResponseEntity<?> inverso(@RequestParam double a) {
        if (a == 0) return ResponseEntity.badRequest().body("Erro: Inverso de zero não existe!");
        return ResponseEntity.ok(calcService.inverso(a));
    }

    @GetMapping("/modulo")
    public ResponseEntity<Double> modulo(@RequestParam double a) {
        return ResponseEntity.ok(calcService.modulo(a));
    }

    @GetMapping("/fatorial")
    public ResponseEntity<?> fatorial(@RequestParam int a) {
        if (a < 0) return ResponseEntity.badRequest().body("Erro: Fatorial de número negativo não existe!");
        return ResponseEntity.ok(calcService.fatorial(a));
    }

    @GetMapping("/media")
    public ResponseEntity<Double> media(@RequestParam List<Double> valores) {
        return ResponseEntity.ok(calcService.media(valores));
    }

    @GetMapping("/mediana")
    public ResponseEntity<Double> mediana(@RequestParam List<Double> valores) {
        return ResponseEntity.ok(calcService.mediana(valores));
    }
}
