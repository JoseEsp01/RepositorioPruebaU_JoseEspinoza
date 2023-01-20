package com.ista.springboot.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.ista.springboot.web.model.Docente;
import com.ista.springboot.web.repository.IDocenteRepository;


@Service
public class DocenteServiceImpl extends GenericServiceImpl<Docente, Integer> implements IDocenteService{
    @Autowired
    private IDocenteRepository docenteRepository;

    @Override
    public CrudRepository<Docente, Integer> getDao() {
        return docenteRepository;
    }

}
