package com.example.demo.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import com.example.demo.service.IMateriaService;
import com.example.demo.service.to.EstudianteTO;
import com.example.demo.service.to.MateriaTO;

@RestController
@RequestMapping(path = "/estudiantes")
public class EstudianteController {

	@Autowired
	private IEstudianteService estudianteService;
	@Autowired
	private IMateriaService materiaService;

	// cudr orden jerarquico

	// *******GUARDAR************
	// URL NIVEL0:http://localhost:8080/API/v1.0/Matricula/estudiantes/guardar
	// URL NIVEL1:http://localhost:8080/API/v1.0/Matricula/estudiantes
	// @PostMapping(path = "/guardar") //NIVEL0
//	@PostMapping() // NIVEL 1
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE) // MEDIATYPE
//	public void guardar(@RequestBody Estudiante estudiante) {// capacidades
	// public ResponseEntity<Estudiante> guardar(@RequestBody Estudiante estudiante)
	// {// capacidades-codigos personalizados
//	public ResponseEntity<Estudiante> guardar(@RequestBody Estudiante estudiante) {// MEDIATYPE
	public ResponseEntity<EstudianteTO> guardar(@RequestBody EstudianteTO estudianteTo) {// hateoas

		// RE
		// comento porque estos verbos utilizan en @RequestBody
		/*
		 * Estudiante estu = new Estudiante(); estu.setNombre("Juan Carlos");
		 * estu.setApellido("Paredes"); estu.setFechaNacimiento(LocalDateTime.of(1999,
		 * 05, 10, 0, 0)); estu.setGenero("M");
		 */
		// CONTRUIMOS CABECERAS
//		HttpHeaders 
		this.estudianteService.ingresar(estudianteTo);
		return ResponseEntity.status(HttpStatus.CREATED).body(estudianteTo);
	}

	// *******ACTUALIZAR************
	// URL BASICO NIVEL
	// 0:http://localhost:8080/API/v1.0/Matricula/estudiantes/actualizar
	// URL NIVEL 1:http://localhost:8080/API/v1.0/Matricula/estudiantes/1
	// @PutMapping(path = "/actualizar")
	@PutMapping("/{id}")
//	public void actualizar(@RequestBody Estudiante estudiante, @PathVariable Integer id) {
	public ResponseEntity<Estudiante> actualizar(@RequestBody Estudiante estudiante, @PathVariable Integer id) {// capacidades-codigos
																												// personalizados
		estudiante.setId(id);// NIVEL 1 EL ID YA NO VIENE EN EL BODY COMO NIVEL 0
		/*
		 * Estudiante estu = this.estudianteService.buscar(1);
		 * estu.setNombre("Jose David"); estu.setApellido("Garcia");
		 * estu.setFechaNacimiento(LocalDateTime.of(2022, 05, 07, 0, 0));
		 * estu.setGenero("M");
		 */
		this.estudianteService.actualizar(estudiante);
		return ResponseEntity.status(238).body(estudiante);
	}

	// *******ACTUALIZARPARCIAL PATCH************
	// URL BASICO NIVEL
	// 0:http://localhost:8080/API/v1.0/Matricula/estudiantes/actualizarParcial
	// URL NIVEL 1:http://localhost:8080/API/v1.0/Matricula/estudiantes/1
	// @PatchMapping(path = "/actualizarParcial") //NIVEL 0
	@PatchMapping(path = "/{id}") // NIVEL 1
//	public void actualizaParcial(@RequestBody Estudiante estudiante, @PathVariable Integer id) {
	public ResponseEntity<Estudiante> actualizaParcial(@RequestBody Estudiante estudiante, @PathVariable Integer id) {
		estudiante.setId(id);// seteado en el atributo
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
//		if (estudiante.getFechaNacimiento() != null) {
//			estudiante2.setFechaNacimiento(estudiante.getFechaNacimiento());
//		}
		if (estudiante.getCarrera() != null) {
			estudiante2.setCarrera(estudiante.getCarrera());
		}
		if (estudiante.getGenero() != null) {
			estudiante2.setGenero(estudiante.getGenero());
		}
		this.estudianteService.actualizar(estudiante2);
		return ResponseEntity.status(239).body(estudiante2);
	}

//	**********BORRAR***************
	// URL NIVEL 0
	// URL BASICO:http://localhost:8080/API/v1.0/Matricula/estudiantes/borrar
	// URL BASICO PATH
	// VARIABLE:http://localhost:8080/API/v1.0/Matricula/estudiantes/borrar/1
	// URL BASICO PATH
	// VARIABLE:http://localhost:8080/API/v1.0/Matricula/estudiantes/borrar/2
	// URL BASICO PATH
	// VARIABLE:http://localhost:8080/API/v1.0/Matricula/estudiantes/borrar/3
	// URL BASICO PATH
	// VARIABLE:http://localhost:8080/API/v1.0/Matricula/estudiantes/borrar/4

	// URL NIVEL 1:http://localhost:8080/API/v1.0/Matricula/estudiantes/3
//	@DeleteMapping(path = "/borrar/{id}") //NIVEL 0
	@DeleteMapping(path = "/{id}") // NIVEL 1
//	public void borrar(@PathVariable Integer id) {
	public ResponseEntity<String> borrar(@PathVariable Integer id) {// capacidad-codigos personalizados
		// comento porque esto es basico y no es correcto hacerlo asi debe ser
		// variaalbel el path
		// this.estudianteService.borrar(1);
		this.estudianteService.borrar(id);
		return ResponseEntity.status(240).body("Estudiante borrado!");
	}

	// *************BUSCAR*************GET
	// URL NIVEL 0
	// URL BASICO:http://localhost:8080/API/v1.0/Matricula/estudiantes/buscar
	// URL
	// PATHVARIABLE:http://localhost:8080/API/v1.0/Matricula/estudiantes/buscar/1
	// URL
	// PATHVARIABLE:http://localhost:8080/API/v1.0/Matricula/estudiantes/buscar/2
	// URL
	// PATHVARIABLE:http://localhost:8080/API/v1.0/Matricula/estudiantes/buscar/3
	// URL
	// PATHVARIABLE:http://localhost:8080/API/v1.0/Matricula/estudiantes/buscar/3/nuevo----funiciona

	// URL NIVEL 1:http://localhost:8080/API/v1.0/Matricula/estudiantes/1
//	@GetMapping(path = "/buscar/{id}") //NIVEL 0
//  @GetMapping(path = "/{id}") // NIVEL 1
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE) // NIVEL 2 mediatype

//	public Estudiante buscarPorId(@PathVariable Integer id) {
	public ResponseEntity<Estudiante> buscarPorId(@PathVariable Integer id) {// capaciadad-codigos personalizados

		// CONTRUIMOS CABECERAS
		HttpHeaders cabeceras = new HttpHeaders();
		cabeceras.add("Mensaje_236", "Corresponde a la consulta de un recurso");
		// AGREGRO MAS CABECERAS
		cabeceras.add("Clave", "Estudiante Encontrado");
//		return ResponseEntity.status(236).body(this.estudianteService.buscar(id));//metodo estatico
		return new ResponseEntity<>(this.estudianteService.buscar(id), cabeceras, 236);// metodo generico

	}

	// *****************BUSCAR POR GENERO**********
	// MÉTODO PARA FILTRAR ESTUDIANTES POR GENERO FEMENINO
	// URL NIVEL
	// 0:http://localhost:8080/API/v1.0/Matricula/estudiantes/buscarPorGenero?genero=F
	// URL NIVEL
	// 1:http://localhost:8080/API/v1.0/Matricula/estudiantes/genero?genero=F
	// MÉTODO PARA FILTRAR ESTUDIANTES POR GENERO FEMENINO
	// URL NIVEL
	// 0:http://localhost:8080/API/v1.0/Matricula/estudiantes/buscarPorGenero?genero=M
	// URL NIVEL
	// 1:http://localhost:8080/API/v1.0/Matricula/estudiantes/genero?genero=M
//	@GetMapping(path = "/buscarPorGenero")//NIVEL 0
	@GetMapping(path = "/genero") // NIVEL 1
	public List<Estudiante> buscarPorGenero(@RequestParam String genero) {
		List<Estudiante> lista = this.estudianteService.buscarPorGenero(genero);
		return lista;
	}

	// buscarMixto
	// URL NIVEL
	// 0:http://localhost:8080/API/v1.0/Matricula/estudiantes/buscarMixto/1?prueba=?indica
	// requestaParam
	// URL NIVEL
	// 1:http://localhost:8080/API/v1.0/Matricula/estudiantes/prueba/1?prueba=?indica
	// requestaParam
//	@GetMapping("/buscarMixto/{id}")
	@GetMapping("/prueba/{id}")
	public Estudiante buscarMixto(@PathVariable Integer id, @RequestParam String prueba) {

		System.out.println("Dato: " + id);
		System.out.println("Dato: " + prueba);
		return this.estudianteService.buscar(id);
	}

	// NO SON COMUNES PERO ES BUENO CONOCERLO (falta el nivel 1)
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

	// MEDIATYPE EJEMPLOS
	// NO SE RECOMIENDA PORQUE ES UN TEXTO CUALQUIERA
	// UN HTML EN APIS TAMPOCO EL HTML
	/// URL: http://localhost:8080/API/v1.0/Matricula/estudiantes/texto/plano
	@GetMapping("/texto/plano")
	public String prueba() {
		String prueba = "texto de prueba";
		return prueba;
	}

	// HATEOAS
	// excepciones /hateoas/{id} solo si hay choques de endpoints
//	URL: http://localhost:8080/API/v1.0/Matricula/estudiantes/hateoas/1
	@GetMapping(path = "/hateoas/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public EstudianteTO buscarhateoas(@PathVariable Integer id) {
		// consulto el estudiante por id
		EstudianteTO estudianteTO = this.estudianteService.buscarPorId(id);
		/*
		 * // HATEOAS P1. // consulto la lista de estudiantes con la capacidad del
		 * service,y lo alamceno // en una lista List<MateriaTO> lista
		 * =this.materiaService.buscarPorIdEstudiante(id); //seteo las materias al
		 * estudiante estudianteTO.setMaterias(lista);
		 */
		// HATEOAS P2.
		// consulto la lista de estudiantes con la capacidad del service,y lo alamceno
		// en una lista
		// HATEOAS PARTE 2 VAMOS A COMENTAR LA PARTE 1 PORQUE ESO NO SE DEBE HACER
		// ESTO ES UNA CARGA EAGER(PA2)
		/*
		 * List<MateriaTO> lista = this.materiaService.buscarPorIdEstudiante(id);
		 * //seteo las materias al estudiante estudianteTO.setMaterias(lista);
		 */

		// creamos el hipervinculo
		Link myLink = linkTo(methodOn(EstudianteController.class).buscarMateriasPorIdEstudiante(id))
				.withRel("sus materias");

		Link myLink2 = linkTo(methodOn(EstudianteController.class).buscarPorId(id)).withSelfRel();
		// agrego el hipervinculo
		estudianteTO.add(myLink);
		estudianteTO.add(myLink2);

		// retorno el estudiante con las materias
		return estudianteTO;
	}

	// HATEOAS PARTE 2 HIPERVICULOS
	// ejemplo nos olvidamos de hateoas
	// http://localhost:8080/API/v1.0/Matricula/estudiantes/7/materias GET
	@GetMapping(path = "/{id}/materias", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<MateriaTO> buscarMateriasPorIdEstudiante(@PathVariable Integer id) {
		return this.materiaService.buscarPorIdEstudiante(id);
	}

	// TRAER TODOS LOS ESTUDIANTES CON SUS MATERIAS
	// URL: http://localhost:8080/API/v1.0/Matricula/estudiantes/todos
	@GetMapping("/todos")
	public List<EstudianteTO> buscarTodosHateoas() {

		// busco todos los estudiantes y los almaceno en una lista
		List<EstudianteTO> lista = this.estudianteService.buscarTodosHateoas();

		// con un for voy añadiendo el link a los estudiantes
		for (EstudianteTO estudianteTO : lista) {
			estudianteTO.add(
					linkTo(methodOn(EstudianteController.class).buscarMateriasPorIdEstudiante(estudianteTO.getId()))
							.withRel("sus materias"));
		}

		return lista;

	}

}
