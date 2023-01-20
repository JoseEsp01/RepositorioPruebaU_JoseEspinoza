package com.ista.springboot.web.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity
@Table(name ="asignaturas")
public class Asignatura {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "id_asignatura")
	    private Integer id_asignatura;

	    @Column(name = "nombre")
	    private String nombre;

	    @Column(name = "carrera")
	    private String carrera;

	    @Column(name = "hora_ini")
	    private String hora_ini;

	    @Column(name = "hora_fin")
	    private String hora_fin;

	    @ManyToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name = "id_docente", referencedColumnName = "id_docente")
	    private Docente docente;

}


