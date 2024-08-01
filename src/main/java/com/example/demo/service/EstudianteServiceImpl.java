package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.IEstudianteRepository;
import com.example.demo.repository.modelo.Estudiante;
import com.example.demo.service.to.EstudianteTO;

@Service
public class EstudianteServiceImpl implements IEstudianteService {

	@Autowired
	private IEstudianteRepository estudianteRepository;

	@Override
	public void ingresar(Estudiante estudiante) {
		// TODO Auto-generated method stub
		this.estudianteRepository.insertar();
	}

	@Override
	public Estudiante buscar(Integer id) {
		// TODO Auto-generated method stub
		return this.estudianteRepository.seleccionar(id);
	}

	@Override
	public void actualizar(Estudiante estudiante) {
		// TODO Auto-generated method stub
		this.estudianteRepository.actualizar(estudiante);
	}

	@Override
	public void borrar(Integer id) {
		// TODO Auto-generated method stub
		this.estudianteRepository.eliminar(id);
	}

	@Override
	public List<Estudiante> buscarPorGenero(String genero) {
		// TODO Auto-generated method stub
		return this.estudianteRepository.seleccionarPorGenero(genero);
	}

	//HATEOAS CONVERTIR DE TO A ENTIDAD
	
	public Estudiante convertirAEntidad(EstudianteTO estudianteTo) {
		Estudiante estudiante=new Estudiante();
		estudiante.setId(estudianteTo.getId());
		estudiante.setNombre(estudianteTo.getNombre());
		estudiante.setApellido(estudianteTo.getApellido());
		estudiante.setCarrera(estudianteTo.getCarrera());
		estudiante.setGenero(estudianteTo.getGenero());
		return estudiante;
	}
	
	
	// hateoas metodos CONVERTIR DE ENTIDAD A TO
	public EstudianteTO convertirTo(Estudiante estudiante) {
		EstudianteTO estudianteTO = new EstudianteTO();
		estudianteTO.setId(estudiante.getId());
		estudianteTO.setNombre(estudiante.getNombre());
		estudianteTO.setApellido(estudiante.getApellido());
		//estudianteTO.setFechaNacimiento(estudiante.getFechaNacimiento());
		estudianteTO.setCarrera(estudiante.getCarrera());
		estudianteTO.setGenero(estudiante.getGenero());
		return estudianteTO;
	}

	public EstudianteTO buscarPorId(Integer id) {
		Estudiante estudiante = this.estudianteRepository.seleccionar(id);

		// APORTE A MANERA DE PRUEBA SE UTILIZA EN TRY Y CATCH
		/*
		 * try { return this.convertirTo(estudiante) ;//me retorna un estudianteTO
		 * 
		 * } catch (Exception e) { System.out.println("Estudiante no encontrado");
		 * return null;// TODO: handle exception }
		 */
		return this.convertirTo(estudiante);// me retorna un estudianteTO
	}

	@Override
	public List<EstudianteTO> buscarTodosHateoas() {
		// TODO Auto-generated method stub
		List<Estudiante> lista = this.estudianteRepository.seleccionarTodos();
		List<EstudianteTO> listaFinal = new ArrayList();
		for (Estudiante estudiante : lista) {
			listaFinal.add(this.convertirTo(estudiante));
		}
		return listaFinal;
	}

}
