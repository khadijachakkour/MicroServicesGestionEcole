package com.example.gestionecole.etudiant.controller;

import com.example.gestionecole.etudiant.dto.EtudiantDTO;
import com.example.gestionecole.etudiant.service.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/etudiants")
public class EtudiantController {
    @Autowired
    private EtudiantService etudiantService;

    @GetMapping
    public ResponseEntity<List<EtudiantDTO>> getAllEtudiants() {
        List<EtudiantDTO> etudiants = etudiantService.getAllEtudiants();
        return ResponseEntity.ok(etudiants);
    }

    @PostMapping
    public ResponseEntity<EtudiantDTO> createEtudiant(@RequestBody EtudiantDTO etudiantDTO) {
        EtudiantDTO createdEtudiant = etudiantService.createEtudiant(etudiantDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEtudiant);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EtudiantDTO> updateEtudiant(@PathVariable Integer id, @RequestBody EtudiantDTO etudiantDTO) {
        EtudiantDTO updatedEtudiant = etudiantService.updateEtudiant(id, etudiantDTO);
        return updatedEtudiant != null ? ResponseEntity.ok(updatedEtudiant) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EtudiantDTO> getEtudiantById(@PathVariable Integer id) {
        EtudiantDTO etudiant = etudiantService.getEtudiantById(id);
        return etudiant != null ? ResponseEntity.ok(etudiant) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEtudiant(@PathVariable Integer id) {
        etudiantService.deleteEtudiant(id);
        return ResponseEntity.noContent().build();
    }
}

