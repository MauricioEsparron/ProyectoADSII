package pe.com.cibertec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pe.com.cibertec.model.entity.PersonaEntity;
import pe.com.cibertec.model.entity.UsuarioEntity;
import pe.com.cibertec.service.EstadoUsuarioService;
import pe.com.cibertec.service.PersonaService;
import pe.com.cibertec.service.TipoUsuarioService;
import pe.com.cibertec.service.UsuarioService;

@Controller
@RequestMapping("/personas")
public class PersonaController {

	@Autowired
	private PersonaService personaService;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private TipoUsuarioService tipoUsuarioService;

	@Autowired
	private EstadoUsuarioService estadoUsuarioService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@GetMapping("/")
	public String listarPersonas(Model model) {
		List<PersonaEntity> listaPersonas = personaService.buscarPersonas();
		model.addAttribute("lista_personas", listaPersonas);
		return "listar_personas";
	}

	@GetMapping("/registrar_persona")
	public String mostrarRegistrarPersona(Model model) {
		model.addAttribute("persona", new PersonaEntity());
		return "registrar_persona";
	}

	@PostMapping("/registrar_persona")
	public String registrarPersona(@ModelAttribute("persona") PersonaEntity persona) {
		// Crear el usuario con los datos del formulario
		UsuarioEntity usuario = new UsuarioEntity();
		usuario.setUsuario(persona.getCorreo()); // Asigna el correo como usuario
		usuario.setPassword(passwordEncoder.encode("defaultPassword")); // Puedes establecer una contraseña por defecto
		usuario.setEstadoUsuario(estadoUsuarioService.buscarEstadoUsuarioPorId(1)); // Asigna estado predeterminado
		usuario.setTipoUsuario(tipoUsuarioService.buscarTipoUsuarioPorId(1)); // Asigna tipo predeterminado

		// Guardar el usuario primero
		usuarioService.crearUsuario(usuario);

		// Ahora guardar la persona
		persona.setUsuario(usuario); // Asocia el usuario a la persona
		personaService.crearPersona(persona);

		return "redirect:/personas/";
	}

	@GetMapping("/detalle_persona/{id}")
	public String verDetalle(Model model, @PathVariable("id") Integer id) {
		PersonaEntity persona = personaService.buscarPersonaPorId(id);
		model.addAttribute("persona", persona);
		return "detalle_persona";
	}

	@GetMapping("/delete/{id}")
	public String deletePersona(@PathVariable("id") Integer id) {
		personaService.eliminarPersona(id);
		return "redirect:/personas/";
	}

	@GetMapping("/editar_persona/{id}")
	public String mostrarActualizar(@PathVariable("id") Integer id, Model model) {
		PersonaEntity persona = personaService.buscarPersonaPorId(id);
		model.addAttribute("persona", persona);
		return "editar_persona";
	}

	@PostMapping("/editar_persona/{id}")
	public String actualizarPersona(@PathVariable("id") Integer id, @ModelAttribute("persona") PersonaEntity persona,
			@RequestParam("usuarioId") Integer usuarioId) {
		UsuarioEntity usuario = usuarioService.buscarUsuarioPorId(usuarioId);
		persona.setUsuario(usuario); // Establecer el usuario asociado a la persona
		personaService.actualizarPersona(id, persona);
		return "redirect:/personas/";
	}
}
