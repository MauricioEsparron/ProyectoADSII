package pe.com.cibertec.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_persona")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "persona_Id", nullable = false)
	private Integer personaId;

	@Column(name = "nombre", length = 100, nullable = false)
	private String nombre;

	@Column(name = "apellido", length = 100, nullable = false)
	private String apellido;

	@Column(name = "direccion", length = 150)
	private String direccion;

	@Column(name = "telefono", length = 15)
	private String telefono;

	@Column(name = "correo", length = 255, nullable = false, unique = true)
	private String correo;

	@OneToOne
	@JoinColumn(name = "usuario_id", unique = true) // FK referenciando a UsuarioEntity
	private UsuarioEntity usuario; // Relación con UsuarioEntity
}
