package com.ista.springboot.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.ista.springboot.web.model.Asignatura;
import com.ista.springboot.web.repository.IAsignaturaRepository;



@Service
public class AsignaturaServiceImpl extends GenericServiceImpl<Asignatura, Integer> implements IAsignaturaService{
    @Autowired
    private IAsignaturaRepository asignaturaRepository;

    @Override
    public CrudRepository<Asignatura, Integer> getDao() {
        return asignaturaRepository;
    }
}
