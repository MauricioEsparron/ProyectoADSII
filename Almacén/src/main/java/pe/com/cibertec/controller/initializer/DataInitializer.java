package pe.com.cibertec.controller.initializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import pe.com.cibertec.repository.TipoUsuarioRepository;
import pe.com.cibertec.repository.UsuarioRepository;
import pe.com.cibertec.model.entity.EstadoProductoEntity;
import pe.com.cibertec.model.entity.EstadoUsuarioEntity;
import pe.com.cibertec.model.entity.PersonaEntity;
import pe.com.cibertec.model.entity.ProductoEntity;
import pe.com.cibertec.model.entity.ProveedorCategoriaEntity;
import pe.com.cibertec.model.entity.ProveedorEntity;
import pe.com.cibertec.model.entity.TipoUsuarioEntity;
import pe.com.cibertec.model.entity.UsuarioEntity;
import pe.com.cibertec.repository.EstadoProductoRepository;
import pe.com.cibertec.repository.EstadoUsuarioRepository;
import pe.com.cibertec.repository.PersonaRepository;
import pe.com.cibertec.repository.ProductoRepository;
import pe.com.cibertec.repository.ProveedorCategoriaRepository;
import pe.com.cibertec.repository.ProveedorRepository;

@Component
public class DataInitializer implements CommandLineRunner {

	@Autowired
	private TipoUsuarioRepository tipoUsuarioRepository;

	@Autowired
	private ProveedorCategoriaRepository proveedorCategoriaRepository;

	@Autowired
	private EstadoUsuarioRepository estadoUsuarioRepository;

	@Autowired
	private EstadoProductoRepository estadoProductoRepository;

	@Autowired
	private UsuarioRepository usuariRepository;

	@Autowired
	private PersonaRepository personaRepository;

	@Autowired
	private ProveedorRepository proveedorRepository;

	@Autowired
	private ProductoRepository productoRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder; // Inyección del encoder

	@Override
	public void run(String... args) throws Exception {
		insertarDatosIniciales();
	}

	private void insertarDatosIniciales() {
		// Crear e insertar tipos de usuario
		TipoUsuarioEntity admin = new TipoUsuarioEntity();
		admin.setNombre("Administrador");
		admin.setDescripcion("Puede administrar y realizar cambios sin limitaciones");
		TipoUsuarioEntity usuarioComun = new TipoUsuarioEntity();
		usuarioComun.setNombre("Usuario");
		usuarioComun.setDescripcion("Puede administrar de manera limitada");
		tipoUsuarioRepository.save(admin);
		tipoUsuarioRepository.save(usuarioComun);

		// ProveedorCategoria
		ProveedorCategoriaEntity alimentos = new ProveedorCategoriaEntity();
		alimentos.setNombre("Alimentos");
		ProveedorCategoriaEntity tecnologia = new ProveedorCategoriaEntity();
		tecnologia.setNombre("Tecnología");
		proveedorCategoriaRepository.save(alimentos);
		proveedorCategoriaRepository.save(tecnologia);

		// EstadoUsuario
		EstadoUsuarioEntity usuarioActivo = new EstadoUsuarioEntity();
		usuarioActivo.setDescripcion("Usuario Activo");
		EstadoUsuarioEntity usuarioInactivo = new EstadoUsuarioEntity();
		usuarioInactivo.setDescripcion("Usuario Inactivo");
		estadoUsuarioRepository.save(usuarioActivo);
		estadoUsuarioRepository.save(usuarioInactivo);

		// EstadoProducto
		EstadoProductoEntity productoEnStock = new EstadoProductoEntity();
		productoEnStock.setDescripcion("En Stock");
		EstadoProductoEntity productoAgotado = new EstadoProductoEntity();
		productoAgotado.setDescripcion("Agotado");
		estadoProductoRepository.save(productoEnStock);
		estadoProductoRepository.save(productoAgotado);

		// Crear usuarios con contraseñas encriptadas
		UsuarioEntity userPrueba1 = new UsuarioEntity();
		userPrueba1.setUsuario("sofi123");
		userPrueba1.setPassword(passwordEncoder.encode("12345")); // Contraseña encriptada
		userPrueba1.setEstadoUsuario(usuarioInactivo);
		userPrueba1.setTipoUsuario(admin);

		UsuarioEntity userPrueba2 = new UsuarioEntity();
		userPrueba2.setUsuario("mau123");
		userPrueba2.setPassword(passwordEncoder.encode("12345")); // Contraseña encriptada
		userPrueba2.setEstadoUsuario(usuarioActivo);
		userPrueba2.setTipoUsuario(admin);

		// Guardar usuarios
		usuariRepository.save(userPrueba1);
		usuariRepository.save(userPrueba2);

		// Crear personas y asignar el usuario correspondiente
		PersonaEntity persona1 = new PersonaEntity();
		persona1.setNombre("Sofi");
		persona1.setApellido("Castro");
		persona1.setDireccion("La Serena");
		persona1.setTelefono("123123123");
		persona1.setCorreo("sofi@gmail.com");
		persona1.setUsuario(userPrueba1); // Asignar el usuario a la persona

		PersonaEntity persona2 = new PersonaEntity();
		persona2.setNombre("Mauricio");
		persona2.setApellido("Ramirez");
		persona2.setDireccion("Nueva Esperanza");
		persona2.setTelefono("936956726");
		persona2.setCorreo("mau@gmail.com");
		persona2.setUsuario(userPrueba2); // Asignar el usuario a la persona

		// Guardar personas
		personaRepository.save(persona1);
		personaRepository.save(persona2);

		// Proveedor
		ProveedorEntity proveedor1 = new ProveedorEntity();
		proveedor1.setNombre("SAC ABC");
		proveedor1.setDireccion("Av. Lima");
		proveedor1.setTelefono("111111111");
		proveedor1.setProveedorCategoria(alimentos);

		ProveedorEntity proveedor2 = new ProveedorEntity();
		proveedor2.setNombre("SAC EFG");
		proveedor2.setDireccion("Av. Chile");
		proveedor2.setTelefono("222222222");
		proveedor2.setProveedorCategoria(alimentos);

		proveedorRepository.save(proveedor1);
		proveedorRepository.save(proveedor2);

		// Producto
		ProductoEntity producto1 = new ProductoEntity();
		producto1.setNombre("Yogurt");
		producto1.setDescripcion("Líquido");
		producto1.setPrecio(2.0);
		producto1.setCantidad(10);
		producto1.setUrlImagen("https://www.kroger.com/product/images/large/front/0001127400021");
		producto1.setEstadoProducto(productoEnStock);
		producto1.setProveedor(proveedor1);

		ProductoEntity producto2 = new ProductoEntity();
		producto2.setNombre("Cereal");
		producto2.setDescripcion("Sólido");
		producto2.setPrecio(5.0);
		producto2.setCantidad(5);
		producto2.setUrlImagen("https://www.kroger.com/product/images/large/front/0001127400021");
		producto2.setEstadoProducto(productoAgotado);
		producto2.setProveedor(proveedor2);

		productoRepository.save(producto1);
		productoRepository.save(producto2);
	}
}
