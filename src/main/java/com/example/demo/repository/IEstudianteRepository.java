package com.example.demo.repository;

import java.util.List;

import com.example.demo.repository.modelo.Estudiante;

public interface IEstudianteRepository {

	// crud
	public void insertar(Estudiante estudiante);

	public Estudiante seleccionar(Integer id);

	public void actualizar(Estudiante estudiante);

	public void eliminar(Integer id);

	public List<Estudiante> seleccionarPorGenero(String genero);

	public List<Estudiante> seleccionarTodos();
	
}
