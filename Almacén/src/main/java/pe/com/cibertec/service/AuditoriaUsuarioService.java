package pe.com.cibertec.service;

import java.util.List;

import pe.com.cibertec.model.entity.AuditoriaUsuarioEntity;

public interface AuditoriaUsuarioService {
	List<AuditoriaUsuarioEntity> buscarAuditoriasUsuario();

	void crearAuditoriaUsuario(AuditoriaUsuarioEntity auditoriausuario);

	AuditoriaUsuarioEntity buscarAuditoriaUsuarioPorId(Integer id);

}
