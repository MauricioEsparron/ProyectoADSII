package pe.com.cibertec.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import pe.com.cibertec.service.UserDetailsServiceImpl;

@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder; // Inyectar desde PasswordEncoderConfig

	@Lazy
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(authorize -> authorize.requestMatchers("/login").permitAll() // Permitir acceso a la
																								// página de login
				.anyRequest().authenticated()) // Cualquier otra solicitud requiere autenticación
				.formLogin(
						form -> form.loginPage("/login").permitAll().defaultSuccessUrl("/registrar_producto", false)); // Redirige
		// a
		// /productos
		// después
		// del
		// inicio
		// de
		// sesión
		return http.build();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}
}
