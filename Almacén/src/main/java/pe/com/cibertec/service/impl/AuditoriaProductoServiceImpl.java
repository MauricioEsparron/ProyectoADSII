package pe.com.cibertec.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pe.com.cibertec.model.entity.AuditoriaProductoEntity;
import pe.com.cibertec.repository.AuditoriaProductoRepository;
import pe.com.cibertec.service.AuditoriaProductoService;

@Service
@RequiredArgsConstructor
public class AuditoriaProductoServiceImpl implements AuditoriaProductoService {

	@Autowired
	private AuditoriaProductoRepository auditoriaproductoRepository;

	@Override
	public List<AuditoriaProductoEntity> buscarAuditoriasProducto() {
		return auditoriaproductoRepository.findAll();
	}

	@Override
	public void crearAuditoriaProducto(AuditoriaProductoEntity auditoriaproducto) {
		auditoriaproductoRepository.save(auditoriaproducto);

	}

	@Override
	public AuditoriaProductoEntity buscarAuditoriaProductoPorId(Integer id) {
		return auditoriaproductoRepository.findById(id).orElse(null);
	}

}
