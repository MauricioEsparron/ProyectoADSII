package pe.com.cibertec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestParam;

import pe.com.cibertec.model.entity.EstadoProductoEntity;
import pe.com.cibertec.model.entity.ProductoEntity;
import pe.com.cibertec.model.entity.ProveedorEntity;
import pe.com.cibertec.service.EstadoProductoService;
import pe.com.cibertec.service.ProductoService;
import pe.com.cibertec.service.ProveedorService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private EstadoProductoService estadoProductoService;

    @Autowired
    private ProveedorService proveedorService;

    @GetMapping("/")
    public String listarProductos(Model model) {
        List<ProductoEntity> listaProductos = productoService.buscarProductos();
        model.addAttribute("lista_productos", listaProductos);
        return "listar_productos"; // Asegúrate de que esta vista exista
    }

    @GetMapping("/registrar_producto")
    public String mostrarRegistrarProducto(Model model) {
        model.addAttribute("producto", new ProductoEntity());
        List<EstadoProductoEntity> listaEstadoProducto = estadoProductoService.buscarEstadosProducto();
        model.addAttribute("listarEstadoProducto", listaEstadoProducto);
        List<ProveedorEntity> listaProveedor = proveedorService.buscarProveedores();
        model.addAttribute("listarProveedor", listaProveedor);
        return "registrar_producto";
    }

    @PostMapping("/registrar_producto")
    public String registrarProducto(
            @ModelAttribute("producto") ProductoEntity producto,
            @RequestParam("file") MultipartFile file) {

        // Manejo de imagen subida
        if (!file.isEmpty()) {
            String imagenUrl = almacenarImagen(file); // Método para almacenar la imagen
            producto.setUrlImagen(imagenUrl);
        }

        // Guardar el producto en la base de datos
        productoService.crearProducto(producto);
        return "redirect:/productos/";
    }

    // Método para almacenar la imagen en el servidor
    private String almacenarImagen(MultipartFile file) {
        // Define la ruta donde quieres guardar la imagen
        String directorioDestino = "src/main/resources/static/images"; 
        String nombreArchivo = file.getOriginalFilename();
        Path rutaDestino = Paths.get(directorioDestino, nombreArchivo);

        // Verifica si el archivo ya existe y cambia el nombre si es necesario
        int contador = 1;
        while (Files.exists(rutaDestino)) {
            String nuevoNombreArchivo = nombreArchivo.substring(0, nombreArchivo.lastIndexOf('.')) + "_" + contador + nombreArchivo.substring(nombreArchivo.lastIndexOf('.'));
            rutaDestino = Paths.get(directorioDestino, nuevoNombreArchivo);
            contador++;
        }

        try {
            // Crea el directorio si no existe
            Files.createDirectories(rutaDestino.getParent());
            // Copia el archivo
            Files.copy(file.getInputStream(), rutaDestino);
            // Retorna la URL accesible
            return "/images/" + rutaDestino.getFileName().toString(); // Esta URL será accesible en la aplicación
        } catch (IOException e) {
            e.printStackTrace();
            return null; // Manejo de errores
        }
    }

    @GetMapping("/detalle_producto/{id}")
    public String verDetalle(Model model, @PathVariable("id") Integer id) {
        ProductoEntity producto = productoService.buscarProductoPorId(id);
        model.addAttribute("producto", producto);
        return "detalle_producto"; // Asegúrate de que esta vista exista
    }

    @GetMapping("/delete/{id}")
    public String deleteProducto(@PathVariable("id") Integer id) {
        productoService.eliminarProducto(id);
        return "redirect:/productos/";
    }

    @GetMapping("/editar_producto/{id}")
    public String mostrarActualizar(@PathVariable("id") Integer id, Model model) {
        ProductoEntity producto = productoService.buscarProductoPorId(id);
        model.addAttribute("producto", producto);
        return "editar_producto"; // Asegúrate de que esta vista exista
    }

    @PostMapping("/editar_producto/{id}")
    public String actualizarProducto(@PathVariable("id") Integer id,
            @ModelAttribute("producto") ProductoEntity producto) {
        productoService.actualizarProducto(id, producto);
        return "redirect:/productos/";
    }
}
