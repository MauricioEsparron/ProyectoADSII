package pe.com.cibertec.service;

import java.util.List;

import pe.com.cibertec.model.entity.ProveedorCategoriaEntity;

public interface ProveedorCategoriaService {
	List<ProveedorCategoriaEntity> buscarProveedoresCaregoria();

	void crearProveedorCategoria(ProveedorCategoriaEntity proveedorcategoria);

	ProveedorCategoriaEntity buscarProveedorCategoriaPorId(Integer id);

	void actualizarProveedorCategoria(Integer id, ProveedorCategoriaEntity proveedorcategoria);

	void eliminarProveedorCategoria(Integer id);
}
