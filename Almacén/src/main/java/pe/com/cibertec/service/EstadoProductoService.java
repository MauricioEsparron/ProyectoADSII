package pe.com.cibertec.service;

import java.util.List;

import pe.com.cibertec.model.entity.EstadoProductoEntity;

public interface EstadoProductoService {
	List<EstadoProductoEntity> buscarEstadosProducto();

	void crearEstadoProducto(EstadoProductoEntity estadoproducto);

	EstadoProductoEntity buscarEstadoProductoPorId(Integer id);

	void actualizarEstadoProducto(Integer id, EstadoProductoEntity estadoproducto);

	void eliminarEstadoProducto(Integer id);
}
