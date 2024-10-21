package pe.com.cibertec.service;

import java.util.List;

import pe.com.cibertec.model.entity.EstadoUsuarioEntity;

public interface EstadoUsuarioService {
	List<EstadoUsuarioEntity> buscarEstadoUsuarios();

	void crearEstadoUsuario(EstadoUsuarioEntity estadousuario);

	EstadoUsuarioEntity buscarEstadoUsuarioPorId(Integer id);

	void actualizarEstadoUsuario(Integer id, EstadoUsuarioEntity estadousuario);

	void eliminarEstadoUsuario(Integer Id);
}
