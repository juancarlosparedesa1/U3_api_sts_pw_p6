package com.example.demo.repository;

import java.util.List;

import com.example.demo.repository.modelo.Materia;

public interface IMateriaRepository {
//crud	
	// capacidad que retorna todas las maeterias que tiene un estudiante
	public List<Materia> SeleccionarPorIdEstudiante(Integer id);
}
