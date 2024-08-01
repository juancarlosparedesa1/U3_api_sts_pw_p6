package com.example.demo.repository.modelo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "materia")
@JsonIgnoreProperties(value = "estudiante")
public class Materia {
	// SECUENCIAS
	@Id

	@GeneratedValue(generator = "seq_materia", strategy = GenerationType.SEQUENCE)

	@SequenceGenerator(name = "seq_materia", sequenceName = "seq_materia", allocationSize = 1)

	@Column(name = "mate_id")
	private Integer id;
	@Column(name = "mate_nombre")
	private String nombre;
	@Column(name = "mate_numero_creditos")
	private Integer numeroCreditos;

	@ManyToOne
	@JoinColumn(name = "mate_id_estudiante")
	private Estudiante estudiante;

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

	public Integer getNumeroCreditos() {
		return numeroCreditos;
	}

	public void setNumeroCreditos(Integer numeroCreditos) {
		this.numeroCreditos = numeroCreditos;
	}

	public Estudiante getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}

}
