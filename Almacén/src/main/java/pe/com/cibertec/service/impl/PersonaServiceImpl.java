package pe.com.cibertec.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pe.com.cibertec.model.entity.PersonaEntity;
import pe.com.cibertec.repository.PersonaRepository;
import pe.com.cibertec.service.PersonaService;

@Service
@RequiredArgsConstructor
public class PersonaServiceImpl implements PersonaService {

	@Autowired
	private PersonaRepository personaRepository;

	@Override
	public List<PersonaEntity> buscarPersonas() {
		return personaRepository.findAll();
	}

	@Override
	public void crearPersona(PersonaEntity persona) {
		personaRepository.save(persona);

	}

	@Override
	public PersonaEntity buscarPersonaPorId(Integer id) {
		return personaRepository.findById(id).orElse(null);
	}

	@Override
	public void actualizarPersona(Integer id, PersonaEntity personaActualizada) {
		PersonaEntity personaEncontrada = buscarPersonaPorId(id);
		if (personaEncontrada == null) {
			throw new RuntimeException("Persona no encontrado");
		}
		try {
			personaEncontrada.setNombre(personaActualizada.getNombre());
			personaEncontrada.setApellido(personaActualizada.getApellido());
			personaEncontrada.setDireccion(personaActualizada.getDireccion());
			personaEncontrada.setTelefono(personaActualizada.getTelefono());
			personaEncontrada.setCorreo(personaActualizada.getCorreo());
			personaRepository.save(personaEncontrada);
		} catch (Exception e) {
			throw new RuntimeException("Error al actualizar");
		}
	}

	@Override
	public void eliminarPersona(Integer id) {
		PersonaEntity personaEncontrada = buscarPersonaPorId(id);
		if (personaEncontrada == null) {
			throw new RuntimeException("Persona no encontrado");
		}
		personaRepository.delete(personaEncontrada);
	}
}