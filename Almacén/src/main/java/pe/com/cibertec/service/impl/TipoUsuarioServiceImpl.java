package pe.com.cibertec.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pe.com.cibertec.model.entity.TipoUsuarioEntity;
import pe.com.cibertec.repository.TipoUsuarioRepository;
import pe.com.cibertec.service.TipoUsuarioService;

@Service
@RequiredArgsConstructor
public class TipoUsuarioServiceImpl implements TipoUsuarioService {

	@Autowired
	private TipoUsuarioRepository tipousuarioRepository;

	@Override
	public List<TipoUsuarioEntity> buscarTiposUsuario() {
		return tipousuarioRepository.findAll();
	}

	@Override
	public void crearTipoUsuario(TipoUsuarioEntity tipousuario) {
		tipousuarioRepository.save(tipousuario);
	}

	@Override
	public TipoUsuarioEntity buscarTipoUsuarioPorId(Integer id) {
		return tipousuarioRepository.findById(id).orElse(null);
	}

	@Override
	public void actualizarTipoUsuario(Integer id, TipoUsuarioEntity tipousuarioActualizado) {
		TipoUsuarioEntity tipoUsuarioEncontrado = buscarTipoUsuarioPorId(id);
		if (tipoUsuarioEncontrado == null) {
			throw new RuntimeException("Tipo usuario no encontrado");
		}
		try {
			tipoUsuarioEncontrado.setDescripcion(tipousuarioActualizado.getDescripcion());
			tipoUsuarioEncontrado.setNombre(tipousuarioActualizado.getNombre());
			tipousuarioRepository.save(tipoUsuarioEncontrado);
		} catch (Exception e) {
			throw new RuntimeException("Error al actualizar");
		}
	}

	@Override
	public void eliminarTipoUsuario(Integer id) {
		TipoUsuarioEntity tipousuarioEncontrado = buscarTipoUsuarioPorId(id);
		if (tipousuarioEncontrado == null) {
			throw new RuntimeException("Tipo usuario no encontrado");
		}
		tipousuarioRepository.delete(tipousuarioEncontrado);
	}
}