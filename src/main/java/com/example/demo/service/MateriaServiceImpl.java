package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.IMateriaRepository;
import com.example.demo.repository.modelo.Materia;
import com.example.demo.service.to.MateriaTO;

@Service
public class MateriaServiceImpl implements IMateriaService {

	@Autowired
	private IMateriaRepository materiaRepository;

	@Override
	public List<MateriaTO> buscarPorIdEstudiante(Integer id) {
		// TODO Auto-generated method stub
		// busco la materia por id
		List<Materia> lista = this.materiaRepository.SeleccionarPorIdEstudiante(id);
		// creo un lista vacia que almacenará las materias
		List<MateriaTO> listaFinal = new ArrayList<>();

		// Recorro la lista de materias
		// y por cada materia en la lista añado a la lista final  convertidas pero
		//la lista final es de MateriTO

		for (Materia materia : lista) {
			listaFinal.add(this.convertidor(materia));
		}
		return listaFinal;
	}

	// convertidor
	public MateriaTO convertidor(Materia materia) {
		MateriaTO materiaTO = new MateriaTO();
		materiaTO.setId(materia.getId());
		materiaTO.setNombre(materia.getNombre());
		materiaTO.setNumeroCreditos(materia.getNumeroCreditos());
		return materiaTO;
	}

}
