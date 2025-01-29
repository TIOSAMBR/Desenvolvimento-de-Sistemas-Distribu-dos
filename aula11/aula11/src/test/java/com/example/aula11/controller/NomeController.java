package com.example.aula11.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api")
public class NomeController {

    @GetMapping("/formatar_nome")
    public ResponseEntity<String> formatarNome(
            @RequestParam(required = false) String pronome,
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String sobrenome) {

        String tratamento = (pronome != null) ? pronome : "Sr.";
        String primeiroNome = (nome != null) ? nome : "Usuário";
        String ultimoNome = (sobrenome != null) ? sobrenome : "Desconhecido";

        String nomeFormatado = String.format("%s; %s, %s", tratamento, ultimoNome, primeiroNome);

        return ResponseEntity.ok(nomeFormatado);
    }
}
