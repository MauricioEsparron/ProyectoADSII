package pe.com.cibertec.service;

import java.util.List;

import pe.com.cibertec.model.entity.AuditoriaProductoEntity;

public interface AuditoriaProductoService {
	List<AuditoriaProductoEntity> buscarAuditoriasProducto();

	void crearAuditoriaProducto(AuditoriaProductoEntity auditoriaproducto);

	AuditoriaProductoEntity buscarAuditoriaProductoPorId(Integer id);

}
