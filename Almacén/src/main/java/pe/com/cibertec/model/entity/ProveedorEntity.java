package pe.com.cibertec.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_proveedor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProveedorEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "proveedor_id", nullable = false)
	private Integer id;

	@Column(name = "nombre", nullable = false, length = 100)
	private String nombre;

	@Column(name = "direccion", length = 150)
	private String direccion;

	@Column(name = "telefono", length = 15)
	private String telefono;

	@ManyToOne
	@JoinColumn(name = "categoria_id", nullable = false)
	private ProveedorCategoriaEntity proveedorCategoria;
}
