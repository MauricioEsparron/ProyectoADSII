package pe.com.cibertec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	@GetMapping("/login")
	public String mostrarLogin() {
		return "login"; // Asegúrate de que el archivo login.html exista
	}
}
