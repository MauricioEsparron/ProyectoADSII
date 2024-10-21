package pe.com.cibertec.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pe.com.cibertec.model.entity.ProveedorEntity;
import pe.com.cibertec.repository.ProveedorRepository;
import pe.com.cibertec.service.ProveedorService;

@Service
@RequiredArgsConstructor
public class ProveedorServiceImpl implements ProveedorService {

	@Autowired
	private ProveedorRepository proveedorRepository;

	@Override
	public List<ProveedorEntity> buscarProveedores() {
		return proveedorRepository.findAll();
	}

	@Override
	public void crearProveedor(ProveedorEntity proveedor) {
		proveedorRepository.save(proveedor);
	}

	@Override
	public ProveedorEntity buscarProveedorPorId(Integer id) {
		return proveedorRepository.findById(id).orElse(null);
	}

	@Override
	public void actualizarProveedor(Integer id, ProveedorEntity proveedorActualizado) {
		ProveedorEntity proveedorEncontrado = buscarProveedorPorId(id);
		if (proveedorEncontrado == null) {
			throw new RuntimeException("Proveedor no encontrado");
		}
		try {
			proveedorEncontrado.setDireccion(proveedorActualizado.getDireccion());
			proveedorEncontrado.setNombre(proveedorActualizado.getNombre());
			proveedorEncontrado.setTelefono(proveedorActualizado.getTelefono());
			proveedorRepository.save(proveedorEncontrado);
		} catch (Exception e) {
			throw new RuntimeException("Error al actualizar");
		}
	}

	@Override
	public void eliminarProveedor(Integer id) {
		ProveedorEntity proveedorEncontrado = buscarProveedorPorId(id);
		if (proveedorEncontrado == null) {
			throw new RuntimeException("Proveedor no encontrado");
		}
		proveedorRepository.delete(proveedorEncontrado);
	}
}
