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

import com.ista.springboot.web.model.Asignatura;
import com.ista.springboot.web.service.IAsignaturaService;
import com.ista.springboot.web.service.IGenericService;


@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api")
public class AsignaturaController {
    @Autowired
    private IAsignaturaService asignaturaService;

    @GetMapping("/asignatura/listar")
    public ResponseEntity<List<Asignatura>> getAll() {
        try {
            return new ResponseEntity<>(asignaturaService.findByAll(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/asignatura/search/{id}")
    public ResponseEntity<Asignatura> getById(@PathVariable("id") Integer id){
        try {
            return  new ResponseEntity<>(asignaturaService.findById(id), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/asignatura/crear")
    public ResponseEntity<Asignatura> createReproducion(@RequestBody Asignatura asignatura){
        try {
            return new ResponseEntity<>(asignaturaService.save(asignatura), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/asignatura/delete/{id}")
    public ResponseEntity<?> deleteReproduction(@PathVariable("id") Integer id) {
        try {
        	asignaturaService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (DataIntegrityViolationException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("404 not found");
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/asignatura/update/{id}")
    public ResponseEntity<Asignatura> updateClient(@RequestBody Asignatura asignatura, @PathVariable("id") Integer id){
        Asignatura listarUp = asignaturaService.findById(id);

        if(listarUp == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            try {

                listarUp.setNombre(asignatura.getNombre());
                listarUp.setCarrera(asignatura.getCarrera());
                listarUp.setHora_ini(asignatura.getHora_ini());
                listarUp.setHora_fin(asignatura.getHora_fin());
                return new ResponseEntity<>(((IGenericService<Asignatura, Integer>) asignatura).save(listarUp), HttpStatus.CREATED);
            }catch (Exception e){
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

    }

}
