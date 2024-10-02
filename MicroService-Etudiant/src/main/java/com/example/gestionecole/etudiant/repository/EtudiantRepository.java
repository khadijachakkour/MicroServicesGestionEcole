package com.example.gestionecole.etudiant.repository;

import com.example.gestionecole.etudiant.model.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtudiantRepository extends JpaRepository<Etudiant, Integer> {
}