package pe.com.cibertec.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pe.com.cibertec.model.entity.ProveedorCategoriaEntity;
import pe.com.cibertec.repository.ProveedorCategoriaRepository;
import pe.com.cibertec.service.ProveedorCategoriaService;

@Service
@RequiredArgsConstructor
public class ProveedorCategoriaServiceImpl implements ProveedorCategoriaService {

	@Autowired
	private ProveedorCategoriaRepository proveedorcategoriaRepository;

	@Override
	public List<ProveedorCategoriaEntity> buscarProveedoresCaregoria() {
		return proveedorcategoriaRepository.findAll();
	}

	@Override
	public void crearProveedorCategoria(ProveedorCategoriaEntity proveedorcategoria) {
		proveedorcategoriaRepository.save(proveedorcategoria);
	}

	@Override
	public ProveedorCategoriaEntity buscarProveedorCategoriaPorId(Integer id) {
		return proveedorcategoriaRepository.findById(id).orElse(null);
	}

	@Override
	public void actualizarProveedorCategoria(Integer id, ProveedorCategoriaEntity proveedorcategoriaActualizado) {
		ProveedorCategoriaEntity proveedorcategoriaEncontrado = buscarProveedorCategoriaPorId(id);
		if (proveedorcategoriaEncontrado == null) {
			throw new RuntimeException("Proveedor Categoria no encontrado");
		}
		try {
			proveedorcategoriaEncontrado.setNombre(proveedorcategoriaActualizado.getNombre());
			proveedorcategoriaRepository.save(proveedorcategoriaEncontrado);
		} catch (Exception e) {
			throw new RuntimeException("Error al actualizar");
		}
	}

	@Override
	public void eliminarProveedorCategoria(Integer id) {
		ProveedorCategoriaEntity proveedorcategoriaEncontrado = buscarProveedorCategoriaPorId(id);
		if (proveedorcategoriaEncontrado == null) {
			throw new RuntimeException("Proveedor categoria no encontrado");
		}
		proveedorcategoriaRepository.delete(proveedorcategoriaEncontrado);
	}
}