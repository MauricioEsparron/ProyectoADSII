package pe.com.cibertec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.com.cibertec.model.entity.EstadoUsuarioEntity;
import pe.com.cibertec.model.entity.TipoUsuarioEntity;
import pe.com.cibertec.model.entity.UsuarioEntity;
import pe.com.cibertec.service.EstadoUsuarioService;
import pe.com.cibertec.service.TipoUsuarioService;
import pe.com.cibertec.service.UsuarioService;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private TipoUsuarioService tipoUsuarioService;

	@Autowired
	private EstadoUsuarioService estadoUsuarioService;

	@GetMapping("/")
	public String listarUsuario(Model model) {
		List<UsuarioEntity> listaUsuario = usuarioService.buscarUsuarios();
		model.addAttribute("lista_usuario", listaUsuario);
		return "listar_usuarios";
	}

	@GetMapping("/registrar_usuario")
	public String mostrarRegistrarUsuario(Model model) {
		model.addAttribute("user", new UsuarioEntity());
		List<EstadoUsuarioEntity> listaEstadoUsuario = estadoUsuarioService.buscarEstadoUsuarios();
		model.addAttribute("listarEstadoUsario", listaEstadoUsuario);
		List<TipoUsuarioEntity> listaTipoUsuario = tipoUsuarioService.buscarTiposUsuario();
		model.addAttribute("listarTipoUsuario", listaTipoUsuario);

		return "registrar_usuario";
	}

	@PostMapping("/registrar_usuario")
	public String registrarUsuario(@ModelAttribute("user") UsuarioEntity usuario) {
		usuarioService.crearUsuario(usuario);
		return "redirect:/usuarios/";
	}

	@GetMapping("/detalle_usuario/{id}")
	public String verDetalle(Model model, @PathVariable("id") Integer id) {
		UsuarioEntity user = usuarioService.buscarUsuarioPorId(id);
		model.addAttribute("user", user);
		return "detalle_usuario";
	}

	@GetMapping("/delete/{id}")
	public String deleteUsuario(@PathVariable("id") Integer id) {
		usuarioService.eliminarUsuario(id);
		return "redirect:/usuarios/";
	}

	@GetMapping("/editar_usuario/{id}")
	public String mostrarActualizar(@PathVariable("id") Integer id, Model model) {
		UsuarioEntity user = usuarioService.buscarUsuarioPorId(id);
		model.addAttribute("user", user);
		List<EstadoUsuarioEntity> listaEstadoUsuario = estadoUsuarioService.buscarEstadoUsuarios();
		model.addAttribute("listarEstadoUsario", listaEstadoUsuario);
		List<TipoUsuarioEntity> listaTipoUsuario = tipoUsuarioService.buscarTiposUsuario();
		model.addAttribute("listarTipoUsuario", listaTipoUsuario);

		return "editar_usuario";
	}

	@PostMapping("/editar_usuario/{id}")
	public String actualizarUsuario(@PathVariable("id") Integer id, @ModelAttribute("user") UsuarioEntity user) {
		usuarioService.actualizarUsuario(id, user);
		return "redirect:/usuarios/";
	}
}
