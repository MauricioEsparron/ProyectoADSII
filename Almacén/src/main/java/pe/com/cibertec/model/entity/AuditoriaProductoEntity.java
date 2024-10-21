package pe.com.cibertec.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "tb_auditoria_producto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuditoriaProductoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auditoria_id", nullable = false)
    private Integer id;

    @Column(name = "accion", nullable = false, length = 50)
    private String accion;

    @Column(name = "descripcion", length = 250)
    private String descripcion;

    @Column(name = "fecha", nullable = false)
    private Date fecha;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private UsuarioEntity usuario;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = true) // Relación opcional con producto
    private ProductoEntity producto;
}
