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
import pe.com.cibertec.service.EstadoUsuarioService;

@Controller
@RequestMapping("/estadousuarios")
public class EstadoUsuarioController {

	@Autowired
	private EstadoUsuarioService estadoUsuarioService;

	@GetMapping("/")
	public String listarEstadoUsuario(Model model) {
		List<EstadoUsuarioEntity> listaEstadoUsuario = estadoUsuarioService.buscarEstadoUsuarios();
		model.addAttribute("lista_estadousuario", listaEstadoUsuario);
		return "listar_estadousuarios";
	}

	@GetMapping("/registrar")
	public String mostrarRegistrarEstadoUsuario(Model model) {
		model.addAttribute("estadoUsuario", new EstadoUsuarioEntity());
		return "registrar_estadousuario";
	}

	@PostMapping("/registrar")
	public String registrarEstadoUsuario(@ModelAttribute("estadoUsuario") EstadoUsuarioEntity estadoUsuario) {
		estadoUsuarioService.crearEstadoUsuario(estadoUsuario);
		return "redirect:/estadousuarios/";
	}

	@GetMapping("/detalle/{id}")
	public String verDetalle(Model model, @PathVariable("id") Integer id) {
		EstadoUsuarioEntity estadoUsuario = estadoUsuarioService.buscarEstadoUsuarioPorId(id);
		model.addAttribute("estadoUsuario", estadoUsuario);
		return "detalle_estadousuario";
	}

	@GetMapping("/editar/{id}")
	public String mostrarActualizar(@PathVariable("id") Integer id, Model model) {
		EstadoUsuarioEntity estadoUsuario = estadoUsuarioService.buscarEstadoUsuarioPorId(id);
		model.addAttribute("estadoUsuario", estadoUsuario);
		return "editar_estadousuario";
	}

	@PostMapping("/editar/{id}")
	public String actualizarEstadoUsuario(@PathVariable("id") Integer id,
			@ModelAttribute("estadoUsuario") EstadoUsuarioEntity estadoUsuario) {
		estadoUsuarioService.actualizarEstadoUsuario(id, estadoUsuario);
		return "redirect:/estadousuarios/";
	}

	@GetMapping("/eliminar/{id}")
	public String eliminarEstadoUsuario(@PathVariable("id") Integer id) {
		estadoUsuarioService.eliminarEstadoUsuario(id);
		return "redirect:/estadousuarios/";
	}
}
