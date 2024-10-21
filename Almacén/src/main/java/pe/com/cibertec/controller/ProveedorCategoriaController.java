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

import pe.com.cibertec.model.entity.ProveedorCategoriaEntity;
import pe.com.cibertec.service.ProveedorCategoriaService;

@Controller
@RequestMapping("/proveedorescategorias")
public class ProveedorCategoriaController {

	@Autowired
	private ProveedorCategoriaService proveedorCategoriaService;

	@GetMapping("/")
	public String listarProveedorCategoria(Model model) {
		List<ProveedorCategoriaEntity> listaProveedorCategoria = proveedorCategoriaService.buscarProveedoresCaregoria();
		model.addAttribute("lista_proveedorcategoria", listaProveedorCategoria);
		return "listar_proveedorecategorias";
	}

	@GetMapping("/registrar_proveedorcategoria")
	public String mostrarRegistrarProveedorCategoria(Model model) {
		model.addAttribute("proveedorCategoria", new ProveedorCategoriaEntity());
		return "registrar_proveedorcategoria";
	}

	@PostMapping("/registrar_proveedorcategoria")
	public String registrarProveedorCategoria(
			@ModelAttribute("proveedorCategoria") ProveedorCategoriaEntity proveedorCategoria) {
		proveedorCategoriaService.crearProveedorCategoria(proveedorCategoria);
		return "redirect:/proveedorescategorias/";
	}

	@GetMapping("/detalle_proveedorcategoria/{id}")
	public String verDetalle(Model model, @PathVariable("id") Integer id) {
		ProveedorCategoriaEntity proveedorCategoria = proveedorCategoriaService.buscarProveedorCategoriaPorId(id);
		model.addAttribute("proveedorCategoria", proveedorCategoria);
		return "detalle_proveedorcategoria";
	}

	@GetMapping("/delete/{id}")
	public String deleteProveedorCategoria(@PathVariable("id") Integer id) {
		proveedorCategoriaService.eliminarProveedorCategoria(id);
		return "redirect:/proveedorescategorias/";
	}

	@GetMapping("/editar_proveedorcategoria/{id}")
	public String mostrarActualizar(@PathVariable("id") Integer id, Model model) {
		ProveedorCategoriaEntity proveedorCategoria = proveedorCategoriaService.buscarProveedorCategoriaPorId(id);
		model.addAttribute("proveedorCategoria", proveedorCategoria);
		return "editar_proveedorcategoria";
	}

	@PostMapping("/editar_proveedorcategoria/{id}")
	public String actualizarProveedorCategoria(@PathVariable("id") Integer id,
			@ModelAttribute("proveedorCategoria") ProveedorCategoriaEntity proveedorCategoria) {
		proveedorCategoriaService.actualizarProveedorCategoria(id, proveedorCategoria);
		return "redirect:/proveedorescategorias/";
	}
}
