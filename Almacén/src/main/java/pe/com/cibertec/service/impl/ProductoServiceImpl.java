package pe.com.cibertec.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pe.com.cibertec.model.entity.ProductoEntity;
import pe.com.cibertec.repository.ProductoRepository;
import pe.com.cibertec.service.ProductoService;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {

	@Autowired
	private ProductoRepository productoRepository;

	@Override
	public List<ProductoEntity> buscarProductos() {
		return productoRepository.findAll();
	}

	@Override
	public void crearProducto(ProductoEntity producto) {
		productoRepository.save(producto);
	}

	@Override
	public ProductoEntity buscarProductoPorId(Integer id) {
		return productoRepository.findById(id).orElse(null);
	}

	@Override
	public void actualizarProducto(Integer id, ProductoEntity productoActualizado) {
		ProductoEntity productoEncontrado = buscarProductoPorId(id);
		if (productoEncontrado == null) {
			throw new RuntimeException("Producto no encontrado");
		}
		try {
			productoEncontrado.setDescripcion(productoActualizado.getDescripcion());
			productoEncontrado.setNombre(productoActualizado.getNombre());
			productoEncontrado.setPrecio(productoActualizado.getPrecio());
			productoEncontrado.setEstadoProducto(productoActualizado.getEstadoProducto());
			productoEncontrado.setProveedor(productoActualizado.getProveedor());
			productoRepository.save(productoEncontrado);
		} catch (Exception e) {
			throw new RuntimeException("Error al actualizar");
		}
	}

	@Override
	public void eliminarProducto(Integer id) {
		ProductoEntity productoEncontrado = buscarProductoPorId(id);
		if (productoEncontrado == null) {
			throw new RuntimeException("Producto no encontrado");
		}
		productoRepository.delete(productoEncontrado);
	}
}
