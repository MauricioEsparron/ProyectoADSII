package pe.com.cibertec.model.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity
@Table(name = "tb_auditoria_usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuditoriaUsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auditoria_id", nullable = false)
    private Integer auditoriaId;

    @ManyToOne
    @JoinColumn(name = "correo_usuario", nullable = false)
    private UsuarioEntity usuario; // Relación con UsuarioEntity

    @Column(name = "accion", nullable = false)
    private String accion; // Tipo de acción (CREAR, ACTUALIZAR, ELIMINAR)

    @Column(name = "fecha", nullable = false)
    private Date fecha; // Fecha y hora de la acción

    @Column(name = "detalles")
    private String detalles; // Detalles adicionales sobre la acción
}
