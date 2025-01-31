package com.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.service.DocumentoService;

@RestController
@RequestMapping("/")
public class DocumentoController {
    
    private final DocumentoService documentoService;

    public DocumentoController(DocumentoService documentoService) {
        this.documentoService = documentoService;
    }

    @GetMapping("/CPF/{cpf}")
    public ResponseEntity<String> validarCPF(@PathVariable String cpf) {
        if (documentoService.validarCPF(cpf)) {
            return ResponseEntity.ok("CPF Válido");
        } else {
            return ResponseEntity.badRequest().body("CPF Inválido");
        }
    }

    @GetMapping("/CNPJ/{cnpj}")
    public ResponseEntity<String> validarCNPJ(@PathVariable String cnpj) {
        if (documentoService.validarCNPJ(cnpj)) {
            return ResponseEntity.ok("CNPJ Válido");
        } else {
            return ResponseEntity.badRequest().body("CNPJ Inválido");
        }
    }
}
