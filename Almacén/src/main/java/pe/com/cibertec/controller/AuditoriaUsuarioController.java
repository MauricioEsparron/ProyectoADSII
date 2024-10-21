package pe.com.cibertec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.com.cibertec.model.entity.AuditoriaUsuarioEntity;
import pe.com.cibertec.service.AuditoriaUsuarioService;

import java.util.List;

@Controller
@RequestMapping("/auditoriausuarios")
public class AuditoriaUsuarioController {

	@Autowired
	private AuditoriaUsuarioService auditoriaUsuarioService;

	@GetMapping("/")
	public String listarAuditorias(Model model) {
		List<AuditoriaUsuarioEntity> listaAuditorias = auditoriaUsuarioService.buscarAuditoriasUsuario();
		model.addAttribute("lista_auditorias", listaAuditorias);
		return "listar_auditoriausuarios"; // Cambia este nombre por el nombre de tu vista
	}

	@GetMapping("/registrar_auditoria")
	public String mostrarRegistrarAuditoria(Model model) {
		model.addAttribute("auditoriausuario", new AuditoriaUsuarioEntity());
		return "registrar_auditoriausuario"; // Cambia este nombre por el nombre de tu vista
	}

	@PostMapping("/registrar_auditoria")
	public String registrarAuditoria(@ModelAttribute("auditoriausuario") AuditoriaUsuarioEntity auditoriausuario) {
		auditoriaUsuarioService.crearAuditoriaUsuario(auditoriausuario);
		return "redirect:/auditoriausuarios/";
	}

	@GetMapping("/detalle_auditoria/{id}")
	public String verDetalle(@PathVariable("id") Integer id, Model model) {
		AuditoriaUsuarioEntity auditoriausuario = auditoriaUsuarioService.buscarAuditoriaUsuarioPorId(id);
		model.addAttribute("auditoriausuario", auditoriausuario);
		return "detalle_auditoriausuario"; // Cambia este nombre por el nombre de tu vista
	}

	@GetMapping("/delete/{id}")
	public String deleteAuditoria(@PathVariable("id") Integer id) {
		// Considera crear un método en tu servicio para eliminar auditorías si es
		// necesario
		// auditoriaUsuarioService.eliminarAuditoriaUsuario(id);
		return "redirect:/auditoriausuarios/";
	}
}
