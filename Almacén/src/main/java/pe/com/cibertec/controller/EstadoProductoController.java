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

import pe.com.cibertec.model.entity.EstadoProductoEntity;
import pe.com.cibertec.service.EstadoProductoService;

@Controller
@RequestMapping("/estadoproductos")
public class EstadoProductoController {

	@Autowired
	private EstadoProductoService estadoProductoService;

	@GetMapping("/")
	public String listarEstadoProducto(Model model) {
		List<EstadoProductoEntity> listaEstadoProducto = estadoProductoService.buscarEstadosProducto();
		model.addAttribute("lista_estadoproducto", listaEstadoProducto);
		return "listar_estadoproductos"; // Asegúrate de tener esta vista
	}

	@GetMapping("/registrar")
	public String mostrarRegistrarEstadoProducto(Model model) {
		model.addAttribute("estadoProducto", new EstadoProductoEntity());
		return "registrar_estadoproducto"; // Asegúrate de tener esta vista
	}

	@PostMapping("/registrar")
	public String registrarEstadoProducto(@ModelAttribute("estadoProducto") EstadoProductoEntity estadoProducto) {
		estadoProductoService.crearEstadoProducto(estadoProducto);
		return "redirect:/estadoproductos/";
	}

	@GetMapping("/detalle/{id}")
	public String verDetalle(Model model, @PathVariable("id") Integer id) {
		EstadoProductoEntity estadoProducto = estadoProductoService.buscarEstadoProductoPorId(id);
		model.addAttribute("estadoProducto", estadoProducto);
		return "detalle_estadoproducto"; // Asegúrate de tener esta vista
	}

	@GetMapping("/editar/{id}")
	public String mostrarActualizar(@PathVariable("id") Integer id, Model model) {
		EstadoProductoEntity estadoProducto = estadoProductoService.buscarEstadoProductoPorId(id);
		model.addAttribute("estadoProducto", estadoProducto);
		return "editar_estadoproducto"; // Asegúrate de tener esta vista
	}

	@PostMapping("/editar/{id}")
	public String actualizarEstadoProducto(@PathVariable("id") Integer id,
			@ModelAttribute("estadoProducto") EstadoProductoEntity estadoProducto) {
		estadoProductoService.actualizarEstadoProducto(id, estadoProducto);
		return "redirect:/estadoproductos/";
	}

	@GetMapping("/eliminar/{id}")
	public String eliminarEstadoProducto(@PathVariable("id") Integer id) {
		estadoProductoService.eliminarEstadoProducto(id);
		return "redirect:/estadoproductos/";
	}
}
