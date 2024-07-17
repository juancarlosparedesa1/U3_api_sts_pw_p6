package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repository.modelo.Estudiante;
import com.example.demo.service.IEstudianteService;

@RestController
@RequestMapping(path = "/estudiantes")
public class EstudianteController {

	@Autowired
	private IEstudianteService estudianteService;

	// cudr orden jerarquico

	// URL BASICO:http://localhost:8080/API/v1.0/Matricula/estudiantes/guardar
	@PostMapping(path = "/guardar")
	public void guardar(@RequestBody Estudiante estudiante) {// capacidades
		// comento porque estos verbos utilizan en @RequestBody
		/*
		 * Estudiante estu = new Estudiante(); estu.setNombre("Juan Carlos");
		 * estu.setApellido("Paredes"); estu.setFechaNacimiento(LocalDateTime.of(1999,
		 * 05, 10, 0, 0)); estu.setGenero("M");
		 */
		this.estudianteService.ingresar(estudiante);
	}

	// URL BASICO:http://localhost:8080/API/v1.0/Matricula/estudiantes/actualizar
	@PutMapping(path = "/actualizar")
	public void actualizar(@RequestBody Estudiante estudiante) {
		/*
		 * Estudiante estu = this.estudianteService.buscar(1);
		 * estu.setNombre("Jose David"); estu.setApellido("Garcia");
		 * estu.setFechaNacimiento(LocalDateTime.of(2022, 05, 07, 0, 0));
		 * estu.setGenero("M");
		 */
		this.estudianteService.actualizar(estudiante);
	}

	// URL
	// BASICO:http://localhost:8080/API/v1.0/Matricula/estudiantes/actualizarParcial
	@PatchMapping(path = "/actualizarParcial")
	public void actualizaParcial(@RequestBody Estudiante estudiante) {
		// COMENTO POR QUE VOY A USAR EL REQUESTBODY
		/*
		 * Estudiante estu = this.estudianteService.buscar(1);
		 * estu.setNombre("Jose David"); estu.setApellido("Zambrano");
		 */
		// ESTO HAGO PARA SIN DEJAR DE UTILIZAR EL MERGE ME ACTUALICE EL DATO QUE
		// NECESITO
		// UTILIZANDO UNA CONDICIÓN.
//		1.mando a buscar
		Estudiante estudiante2 = this.estudianteService.buscar(estudiante.getId());// mando a buscar
//		2.setear cuando si venga el dato
		if (estudiante.getNombre() != null) {
			estudiante2.setNombre(estudiante.getNombre());
		}
		if (estudiante.getApellido() != null) {
			estudiante2.setApellido(estudiante.getApellido());
		}
		if (estudiante.getFechaNacimiento() != null) {
			estudiante2.setFechaNacimiento(estudiante.getFechaNacimiento());
		}
		if (estudiante.getGenero() != null) {
			estudiante2.setGenero(estudiante.getGenero());
		}
		this.estudianteService.actualizar(estudiante2);
	}

	// URL BASICO:http://localhost:8080/API/v1.0/Matricula/estudiantes/borrar
	// URL BASICO PATH
	// VARIABLE:http://localhost:8080/API/v1.0/Matricula/estudiantes/borrar/1
	// URL BASICO PATH
	// VARIABLE:http://localhost:8080/API/v1.0/Matricula/estudiantes/borrar/2
	// URL BASICO PATH
	// VARIABLE:http://localhost:8080/API/v1.0/Matricula/estudiantes/borrar/3
	// URL BASICO PATH
	// VARIABLE:http://localhost:8080/API/v1.0/Matricula/estudiantes/borrar/4

	@DeleteMapping(path = "/borrar/{id}")
	public void borrar(@PathVariable Integer id) {
		// comento porque esto es basico y no es correcto hacerlo asi debe ser
		// variaalbel el path
		// this.estudianteService.borrar(1);
		this.estudianteService.borrar(id);
	}

	// URL BASICO:http://localhost:8080/API/v1.0/Matricula/estudiantes/buscar
	// URL
	// PATHVARIABLE:http://localhost:8080/API/v1.0/Matricula/estudiantes/buscar/1
	// URL
	// PATHVARIABLE:http://localhost:8080/API/v1.0/Matricula/estudiantes/buscar/2
	// URL
	// PATHVARIABLE:http://localhost:8080/API/v1.0/Matricula/estudiantes/buscar/3
	// URL
	// PATHVARIABLE:http://localhost:8080/API/v1.0/Matricula/estudiantes/buscar/3/nuevo----funiciona
	@GetMapping(path = "/buscar/{id}")
	public Estudiante buscar(@PathVariable Integer id) {
		return this.estudianteService.buscar(id);
	}

	// MÉTODO PARA FILTRAR ESTUDIANTES POR GENERO FEMENINO
	// URL:http://localhost:8080/API/v1.0/Matricula/estudiantes/buscarPorGenero?genero=F
	// MÉTODO PARA FILTRAR ESTUDIANTES POR GENERO FEMENINO
	// URL:http://localhost:8080/API/v1.0/Matricula/estudiantes/buscarPorGenero?genero=M
	@GetMapping(path = "/buscarPorGenero")
	public List<Estudiante> buscarPorGenero(@RequestParam String genero) {
		List<Estudiante> lista = this.estudianteService.buscarPorGenero(genero);
		return lista;
	}

	// NO SON COMUNES PERO ES BUENO CONOCERLO
	// METODO PARA PROBAR VARIOS PATHVARIABLES
	// URL
	// PATHVARIABLE:http://localhost:8080/API/v1.0/Matricula/estudiantes/buscartest/3/hola
	@GetMapping(path = "/buscartest/{id}/{dato}")
	public Estudiante buscarTest(@PathVariable Integer id, @PathVariable String dato) {
		System.out.println("Dato:" + dato);
		return this.estudianteService.buscar(id);
	}
	// METODO PARA PROBAR VARIOS RequestParam

	// URL:http://localhost:8080/API/v1.0/Matricula/estudiantes/buscarPorGenerotest?genero=F&edad=35
	// MÉTODO PARA FILTRAR ESTUDIANTES POR GENERO FEMENINO
	// URL:http://localhost:8080/API/v1.0/Matricula/estudiantes/buscarPorGenerotest?genero=M&edad=35
	@GetMapping(path = "/buscarPorGenerotest")
	public List<Estudiante> buscarPorGenerotest(@RequestParam String genero, @RequestParam Integer edad) {
		System.out.println("Edad:" + edad);
		List<Estudiante> lista = this.estudianteService.buscarPorGenero(genero);
		return lista;
	}

}
