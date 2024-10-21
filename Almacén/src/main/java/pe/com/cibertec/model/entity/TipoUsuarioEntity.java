package pe.com.cibertec.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_tipo_usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TipoUsuarioEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tipo_usuario_id", nullable = false)
	private Integer id;

	@Column(name = "nombre", nullable = false, unique = true)
	private String nombre;

	@Column(name = "descripcion", nullable = false)
	private String descripcion;
}
