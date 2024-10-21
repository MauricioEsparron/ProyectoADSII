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
import pe.com.cibertec.model.entity.ProveedorEntity;
import pe.com.cibertec.service.ProveedorCategoriaService;
import pe.com.cibertec.service.ProveedorService;

@Controller
@RequestMapping("/proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;
    
    @Autowired 
    private ProveedorCategoriaService proveedorCategoriaService;

    @GetMapping("/")
    public String listarProveedores(Model model) {
        List<ProveedorEntity> listaProveedores = proveedorService.buscarProveedores();
        model.addAttribute("lista_proveedores", listaProveedores);
        return "listar_proveedores"; // Nombre de la vista que muestra la lista de proveedores
    }

    @GetMapping("/registrar_proveedor")
    public String mostrarRegistrarProveedor(Model model) {
        model.addAttribute("proveedorCategoria", new ProveedorEntity());
        List<ProveedorCategoriaEntity> listaProveedorCategoria = proveedorCategoriaService.buscarProveedoresCaregoria();
        model.addAttribute("listarProveedorCategoria", listaProveedorCategoria);
        return "registrar_proveedor"; // Nombre de la vista para registrar un nuevo proveedor
    }

    @PostMapping("/registrar_proveedor")
    public String registrarProveedor(@ModelAttribute("proveedor") ProveedorEntity proveedor) {
        proveedorService.crearProveedor(proveedor);
        return "redirect:/proveedores/"; // Redirige a la lista de proveedores
    }

    @GetMapping("/detalle_proveedor/{id}")
    public String verDetalleProveedor(Model model, @PathVariable("id") Integer id) {
        ProveedorEntity proveedor = proveedorService.buscarProveedorPorId(id);
        model.addAttribute("proveedor", proveedor);
        return "detalle_proveedor"; // Nombre de la vista para ver detalles de un proveedor
    }

    @GetMapping("/editar_proveedor/{id}")
    public String mostrarActualizarProveedor(@PathVariable("id") Integer id, Model model) {
        ProveedorEntity proveedor = proveedorService.buscarProveedorPorId(id);
        model.addAttribute("proveedor", proveedor);
        return "editar_proveedor"; // Nombre de la vista para editar un proveedor
    }

    @PostMapping("/editar_proveedor/{id}")
    public String actualizarProveedor(@PathVariable("id") Integer id,
                                       @ModelAttribute("proveedor") ProveedorEntity proveedor) {
        proveedorService.actualizarProveedor(id, proveedor);
        return "redirect:/proveedores/"; // Redirige a la lista de proveedores
    }

    @GetMapping("/delete/{id}")
    public String eliminarProveedor(@PathVariable("id") Integer id) {
        proveedorService.eliminarProveedor(id);
        return "redirect:/proveedores/"; // Redirige a la lista de proveedores
    }
}
