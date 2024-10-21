package pe.com.cibertec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.com.cibertec.model.entity.AuditoriaProductoEntity;
import pe.com.cibertec.service.AuditoriaProductoService;

import java.util.List;

@Controller
@RequestMapping("/auditoriaproductos")
public class AuditoriaProductoController {

	@Autowired
	private AuditoriaProductoService auditoriaProductoService;

	@GetMapping("/")
	public String listarAuditorias(Model model) {
		List<AuditoriaProductoEntity> auditorias = auditoriaProductoService.buscarAuditoriasProducto();
		model.addAttribute("lista_auditoriasproducto", auditorias);
		return "listar_auditoriaproductos"; // Vista para listar auditorías de productos
	}

	@GetMapping("/detalle/{id}")
	public String verDetalle(@PathVariable("id") Integer id, Model model) {
		AuditoriaProductoEntity auditoria = auditoriaProductoService.buscarAuditoriaProductoPorId(id);
		model.addAttribute("auditoria", auditoria);
		return "detalle_auditoriaproducto"; // Vista para detalles de una auditoría
	}

	@GetMapping("/registrar")
	public String mostrarRegistrarAuditoria(Model model) {
		model.addAttribute("auditoria", new AuditoriaProductoEntity());
		return "registrar_auditoriaproducto"; // Vista para registrar nueva auditoría
	}

	@PostMapping("/registrar")
	public String registrarAuditoria(@ModelAttribute("auditoria") AuditoriaProductoEntity auditoriaProducto) {
		auditoriaProductoService.crearAuditoriaProducto(auditoriaProducto);
		return "redirect:/auditoriaproductos/"; // Redirigir a la lista después de registrar
	}
}
