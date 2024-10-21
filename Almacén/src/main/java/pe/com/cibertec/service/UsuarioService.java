package pe.com.cibertec.service;

import java.util.List;

import pe.com.cibertec.model.entity.UsuarioEntity;

public interface UsuarioService {
	List<UsuarioEntity> buscarUsuarios();

	void crearUsuario(UsuarioEntity usuario);

	UsuarioEntity buscarUsuarioPorId(Integer id);

	void actualizarUsuario(Integer id, UsuarioEntity usuario);

	void eliminarUsuario(Integer id);
}
