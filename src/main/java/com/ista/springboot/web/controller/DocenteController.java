package com.ista.springboot.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ista.springboot.web.model.Docente;
import com.ista.springboot.web.service.IDocenteService;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api")
public class DocenteController {
	
	@Autowired
    private IDocenteService docenteService;

    @GetMapping("/docente/listar")
    public ResponseEntity<List<Docente>> getAll() {
        try {
            return new ResponseEntity<>(docenteService.findByAll(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/docente/search/{id}")
    public ResponseEntity<Docente> getById(@PathVariable("id") Integer id){
        try {
            return  new ResponseEntity<>(docenteService.findById(id), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/docente/crear")
    public ResponseEntity<Docente> createReproducion(@RequestBody Docente docente){
        try {
            return new ResponseEntity<>(docenteService.save(docente), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/docente/delete/{id}")
    public ResponseEntity<?> deletesong(@PathVariable("id") Integer id) {
        try {
        	docenteService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (DataIntegrityViolationException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("404 not found");
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/docente/update/{id}")
    public ResponseEntity<Docente> updateClient(@RequestBody Docente docente, @PathVariable("id") Integer id){
    	Docente canUp = docenteService.findById(id);

        if(canUp == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            try {
                canUp.setNombre(docente.getNombre());
                canUp.setApellido(docente.getApellido());
                canUp.setCelular(docente.getCelular());
                canUp.setEmail(docente.getEmail());
                return new ResponseEntity<>(docenteService.save(docente), HttpStatus.CREATED);
            }catch (Exception e){
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

    }
}