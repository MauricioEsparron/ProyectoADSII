package pe.com.cibertec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/home")
	public String mostrarHome(Model model) {
		// Puedes agregar atributos al modelo si es necesario
		return "home"; // Asegúrate de que el archivo HTML se llame home.html
	}
}
