package com.ista.springboot.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ista.springboot.web.model.Asignatura;


@Repository
public interface IAsignaturaRepository extends JpaRepository<Asignatura, Integer> {

}
