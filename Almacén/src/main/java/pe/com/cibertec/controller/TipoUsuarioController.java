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

import pe.com.cibertec.model.entity.TipoUsuarioEntity;
import pe.com.cibertec.service.TipoUsuarioService;

@Controller
@RequestMapping("/tiposusuarios")
public class TipoUsuarioController {

	@Autowired
	private TipoUsuarioService tipousuarioService;

	@GetMapping("/")
	public String listarTipoUsuario(Model model) {
		List<TipoUsuarioEntity> listaTipoUsuario = tipousuarioService.buscarTiposUsuario();
		model.addAttribute("lista_tipousuario", listaTipoUsuario);
		return "listar_tipousuarios";
	}

	@GetMapping("/registrar_tipousuario")
	public String mostrarRegistarTipoUsuario(Model model) {
		model.addAttribute("tipousuario", new TipoUsuarioEntity());
		return "registrar_tipousuario";
	}

	@PostMapping("/registrar_tipousuario")
	public String registrarTipoUsuario(@ModelAttribute("tipousuario") TipoUsuarioEntity tipousuario, Model model) {
		tipousuarioService.crearTipoUsuario(tipousuario);
		return "redirect:/tiposusuarios/";
	}

	@GetMapping("/detalle_tipousuario/{id}")
	public String verDetalle(Model model, @PathVariable("id") Integer id) {
		TipoUsuarioEntity tipousuario = tipousuarioService.buscarTipoUsuarioPorId(id);
		model.addAttribute("tipousuario", tipousuario);
		return "detalle_tipousuario";
	}

	@GetMapping("/delete/{id}")
	public String deleteTipoUsuario(Model model, @PathVariable("id") Integer id) {
		tipousuarioService.eliminarTipoUsuario(id);
		return "redirect:/tiposusuarios/";
	}

	@GetMapping("/editar_tipousuario/{id}")
	public String mostrarActualizar(@PathVariable("id") Integer id, Model model) {
		TipoUsuarioEntity tipousuario = tipousuarioService.buscarTipoUsuarioPorId(id);
		model.addAttribute("tipousuario", tipousuario);
		return "editar_tipousuario";
	}

	@PostMapping("/editar_tipousuario/{id}")
	public String actualizarTipoUsuario(@PathVariable("id") int id,
			@ModelAttribute("tipousuario") TipoUsuarioEntity tipousuario, Model model) {
		try {
			tipousuarioService.actualizarTipoUsuario(id, tipousuario);
			return "redirect:/tiposusuarios/";
		} catch (Exception e) {
			model.addAttribute("error", "No se pudo actualizar el Tipo Usuario. Intente nuevamente.");
			return "error";
		}
	}
}
