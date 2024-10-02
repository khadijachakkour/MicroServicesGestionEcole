package com.example.gestionecole.filiere.service;


import com.example.gestionecole.filiere.model.Filiere;
import com.example.gestionecole.filiere.repository.FiliereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FiliereService {

    @Autowired
    private FiliereRepository filiereRepository;

    public Filiere createFiliere(Filiere filiere) {
        return filiereRepository.save(filiere);
    }

    public List<Filiere> getAllFilieres() {
        return filiereRepository.findAll();
    }

    public Optional<Filiere> getFiliereById(int id) {
        return filiereRepository.findById(id);
    }

    public Optional<Filiere> updateFiliere(int id, Filiere filiereDetails) {
        return filiereRepository.findById(id)
                .map(filiere -> {
                    filiere.setCode(filiereDetails.getCode());
                    filiere.setLibelle(filiereDetails.getLibelle());
                    return filiereRepository.save(filiere);
                });
    }

    public boolean deleteFiliere(int id) {
        return filiereRepository.findById(id)
                .map(filiere -> {
                    filiereRepository.delete(filiere);
                    return true;
                })
                .orElse(false);
    }
}
