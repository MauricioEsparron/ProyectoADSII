package pe.com.cibertec.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_Estado_usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EstadoUsuarioEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "estado_usuario_id", nullable = false)
	private Integer id;

	@Column(name = "descripcion", length = 100, nullable = false)
	private String descripcion;
}
