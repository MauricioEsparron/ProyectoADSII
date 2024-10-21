package pe.com.cibertec.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pe.com.cibertec.model.entity.EstadoProductoEntity;
import pe.com.cibertec.repository.EstadoProductoRepository;
import pe.com.cibertec.service.EstadoProductoService;

@Service
@RequiredArgsConstructor
public class EstadoProductoServiceImpl implements EstadoProductoService {

	@Autowired
	private EstadoProductoRepository estadoproductoRepository;

	@Override
	public List<EstadoProductoEntity> buscarEstadosProducto() {
		return estadoproductoRepository.findAll();
	}

	@Override
	public void crearEstadoProducto(EstadoProductoEntity estadoproducto) {
		estadoproductoRepository.save(estadoproducto);
	}

	@Override
	public EstadoProductoEntity buscarEstadoProductoPorId(Integer id) {
		return estadoproductoRepository.findById(id).orElse(null);
	}

	@Override
	public void actualizarEstadoProducto(Integer id, EstadoProductoEntity estadoproductoActualizado) {
		EstadoProductoEntity estadoproductoEncontrado = buscarEstadoProductoPorId(id);
		if (estadoproductoEncontrado == null) {
			throw new RuntimeException("Estado Producto no encontrado");
		}
		try {
			estadoproductoEncontrado.setDescripcion(estadoproductoActualizado.getDescripcion());
			estadoproductoRepository.save(estadoproductoEncontrado);
		} catch (Exception e) {
			throw new RuntimeException("Error al actualizar");
		}
	}

	@Override
	public void eliminarEstadoProducto(Integer id) {
		EstadoProductoEntity estadoproductoEncontrado = buscarEstadoProductoPorId(id);
		if (estadoproductoEncontrado == null) {
			throw new RuntimeException("Estado Producto no encontrado");
		}
		estadoproductoRepository.delete(estadoproductoEncontrado);
	}
}
