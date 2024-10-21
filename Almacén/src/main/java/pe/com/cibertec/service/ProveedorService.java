package pe.com.cibertec.service;

import java.util.List;

import pe.com.cibertec.model.entity.ProveedorEntity;

public interface ProveedorService {
	List<ProveedorEntity> buscarProveedores();

	void crearProveedor(ProveedorEntity proveedor);

	ProveedorEntity buscarProveedorPorId(Integer id);

	void actualizarProveedor(Integer id, ProveedorEntity proveedor);

	void eliminarProveedor(Integer id);
}
