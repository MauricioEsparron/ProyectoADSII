package pe.com.cibertec.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_estado_producto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EstadoProductoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "estado_producto_id", nullable = false)
    private Integer id;

    @Column(name = "descripcion", nullable = false, length = 50)
    private String descripcion;
}
