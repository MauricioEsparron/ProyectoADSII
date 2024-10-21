package pe.com.cibertec.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_proveedor_categoria")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProveedorCategoriaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "proveedor_categoria_id", nullable = false)
	private Integer id;

	@Column(name = "nombre", nullable = false, length = 100)
	private String nombre;

}
