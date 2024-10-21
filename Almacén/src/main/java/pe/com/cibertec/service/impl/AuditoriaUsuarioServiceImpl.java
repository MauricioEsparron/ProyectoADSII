package pe.com.cibertec.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pe.com.cibertec.model.entity.AuditoriaUsuarioEntity;
import pe.com.cibertec.repository.AuditoriaUsuarioRepository;
import pe.com.cibertec.service.AuditoriaUsuarioService;

@Service
@RequiredArgsConstructor
public class AuditoriaUsuarioServiceImpl implements AuditoriaUsuarioService {

	@Autowired
	private AuditoriaUsuarioRepository auditoriausuarioRepository;

	@Override
	public List<AuditoriaUsuarioEntity> buscarAuditoriasUsuario() {
		return auditoriausuarioRepository.findAll();
	}

	@Override
	public void crearAuditoriaUsuario(AuditoriaUsuarioEntity auditoriausuario) {
		auditoriausuarioRepository.save(auditoriausuario);
	}

	@Override
	public AuditoriaUsuarioEntity buscarAuditoriaUsuarioPorId(Integer id) {
		return auditoriausuarioRepository.findById(id).orElse(null);
	}

}
