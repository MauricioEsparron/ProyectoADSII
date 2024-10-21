package pe.com.cibertec.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pe.com.cibertec.model.entity.EstadoUsuarioEntity;
import pe.com.cibertec.repository.EstadoUsuarioRepository;
import pe.com.cibertec.service.EstadoUsuarioService;

@Service
@RequiredArgsConstructor
public class EstadoUsuarioServiceImpl implements EstadoUsuarioService {

	@Autowired
	private EstadoUsuarioRepository estadousuarioRepository;

	@Override
	public List<EstadoUsuarioEntity> buscarEstadoUsuarios() {
		return estadousuarioRepository.findAll();
	}

	@Override
	public void crearEstadoUsuario(EstadoUsuarioEntity estadousuario) {
		estadousuarioRepository.save(estadousuario);

	}

	@Override
	public EstadoUsuarioEntity buscarEstadoUsuarioPorId(Integer id) {

		return estadousuarioRepository.findById(id).orElse(null);
	}

	@Override
	public void actualizarEstadoUsuario(Integer id, EstadoUsuarioEntity estadousuarioActualizado) {
		EstadoUsuarioEntity estadousuarioEncontrado = buscarEstadoUsuarioPorId(id);
		if (estadousuarioEncontrado == null) {
			throw new RuntimeException("Estado usuario no encontrada");
		}
		try {
			estadousuarioEncontrado.setDescripcion(estadousuarioActualizado.getDescripcion());
			estadousuarioRepository.save(estadousuarioActualizado);
		} catch (Exception e) {
			throw new RuntimeException("Error al actualizar");
		}
	}

	@Override
	public void eliminarEstadoUsuario(Integer id) {
		EstadoUsuarioEntity estadousuarioEncontrado = buscarEstadoUsuarioPorId(id);
		if (estadousuarioEncontrado == null) {
			throw new RuntimeException("Estado usuario no encontrada");
		}
		estadousuarioRepository.delete(estadousuarioEncontrado);
	}
}
