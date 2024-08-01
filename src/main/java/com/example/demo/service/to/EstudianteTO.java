package com.example.demo.service.to;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.hateoas.RepresentationModel;

public class EstudianteTO extends RepresentationModel<EstudianteTO> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1615634884916396602L;

	private Integer id;

	private String nombre;

	private String apellido;

	//private LocalDateTime fechaNacimiento;

	private String carrera;
	
	private String genero;

	// con hateoas no se pone
	//private List<MateriaTO> materias;
	// SET Y GET

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

//	public LocalDateTime getFechaNacimiento() {
//		return fechaNacimiento;
//	}
//
//	public void setFechaNacimiento(LocalDateTime fechaNacimiento) {
//		this.fechaNacimiento = fechaNacimiento;
//	}

	public String getGenero() {
		return genero;
	}

	public String getCarrera() {
		return carrera;
	}

	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

//	public List<MateriaTO> getMaterias() {
//		return materias;
//	}
//
//	public void setMaterias(List<MateriaTO> materias) {
//		this.materias = materias;
//	}
}
