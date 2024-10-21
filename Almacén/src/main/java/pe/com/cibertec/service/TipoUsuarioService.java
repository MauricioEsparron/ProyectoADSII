package pe.com.cibertec.service;

import java.util.List;

import pe.com.cibertec.model.entity.TipoUsuarioEntity;

public interface TipoUsuarioService {
	List<TipoUsuarioEntity> buscarTiposUsuario();

	void crearTipoUsuario(TipoUsuarioEntity tipousuario);

	TipoUsuarioEntity buscarTipoUsuarioPorId(Integer id);

	void actualizarTipoUsuario(Integer id, TipoUsuarioEntity tipousuario);

	void eliminarTipoUsuario(Integer id);
}
