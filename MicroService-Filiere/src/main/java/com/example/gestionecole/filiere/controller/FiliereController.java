package com.example.gestionecole.filiere.controller;

import com.example.gestionecole.filiere.model.Filiere;
import com.example.gestionecole.filiere.service.FiliereService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@OpenAPIDefinition(
        info = @Info(
                title = "API de Gestion des Filieres",
                description = "Cette API permet de gérer les informattions des filieres",
                version = "1.0.0"
        )
)

@RestController
@RequestMapping("/api/filieres")
public class FiliereController {

    @Autowired
    private FiliereService filiereService;

    @Operation(summary = "Créer une nouvelle filière", responses = {
            @ApiResponse(responseCode = "201", description = "Filière créée"),
            @ApiResponse(responseCode = "400", description = "Données invalides")
    })
    @PostMapping
    public ResponseEntity<Filiere> createFiliere(@RequestBody Filiere filiere) {
        Filiere newFiliere = filiereService.createFiliere(filiere);
        return ResponseEntity.status(201).body(newFiliere);
    }

    @Operation(summary = "Récupérer toutes les filières")
    @GetMapping
    public ResponseEntity<List<Filiere>> getAllFilieres() {
        List<Filiere> filieres = filiereService.getAllFilieres();
        return ResponseEntity.ok(filieres);
    }

    @Operation(summary = "Récupérer une filière par ID", responses = {
            @ApiResponse(responseCode = "200", description = "Filière trouvée"),
            @ApiResponse(responseCode = "404", description = "Filière non trouvée")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Filiere> getFiliereById(@PathVariable int id) {
        return filiereService.getFiliereById(id)
                .map(filiere -> ResponseEntity.ok(filiere))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Mettre à jour une filière", responses = {
            @ApiResponse(responseCode = "200", description = "Filière mise à jour"),
            @ApiResponse(responseCode = "404", description = "Filière non trouvée")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Filiere> updateFiliere(@PathVariable int id, @RequestBody Filiere filiereDetails) {
        return filiereService.updateFiliere(id, filiereDetails)
                .map(filiere -> ResponseEntity.ok(filiere))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Supprimer une filière", responses = {
            @ApiResponse(responseCode = "204", description = "Filière supprimée"),
            @ApiResponse(responseCode = "404", description = "Filière non trouvée")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFiliere(@PathVariable int id) {
        return filiereService.deleteFiliere(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
