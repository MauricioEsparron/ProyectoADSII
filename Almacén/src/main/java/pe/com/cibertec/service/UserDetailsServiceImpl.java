package pe.com.cibertec.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import pe.com.cibertec.model.entity.UsuarioEntity;
import pe.com.cibertec.repository.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UsuarioEntity usuario = usuarioRepository.findByUsuario(username);
		if (usuario == null) {
			throw new UsernameNotFoundException("Usuario no encontrado: " + username);
		}
		return new org.springframework.security.core.userdetails.User(usuario.getUsuario(), usuario.getPassword(),
				usuario.getEstadoUsuario().getDescripcion().equals("Usuario Activo"), true, true, true,
				new ArrayList<>());
	}
}
