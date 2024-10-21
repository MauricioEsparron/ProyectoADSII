package pe.com.cibertec.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_producto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "producto_id", nullable = false)
	private Integer id;

	@Column(name = "nombre", nullable = false, length = 100)
	private String nombre;

	@Column(name = "descripcion", length = 255)
	private String descripcion;

	@Column(name = "precio", nullable = false)
	private Double precio;

	@Column(name = "cantidad", nullable = false)
	private int cantidad;

	@Column(name = "url_imagen")
	private String urlImagen;

	@ManyToOne
	@JoinColumn(name = "estado_producto_id", nullable = false)
	private EstadoProductoEntity estadoProducto;

	@ManyToOne
	@JoinColumn(name = "proveedor_id", nullable = false)
	private ProveedorEntity proveedor;
}
