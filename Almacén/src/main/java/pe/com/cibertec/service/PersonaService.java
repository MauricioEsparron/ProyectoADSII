package pe.com.cibertec.service;

import java.util.List;

import pe.com.cibertec.model.entity.PersonaEntity;

public interface PersonaService {
	List<PersonaEntity> buscarPersonas();

	void crearPersona(PersonaEntity persona);

	PersonaEntity buscarPersonaPorId(Integer id);

	void actualizarPersona(Integer id, PersonaEntity persona);

	void eliminarPersona(Integer id);
}
