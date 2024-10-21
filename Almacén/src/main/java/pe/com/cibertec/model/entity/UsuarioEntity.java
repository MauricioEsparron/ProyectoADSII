package pe.com.cibertec.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // Campo ID autogenerado

    @Column(name = "usuario", nullable = false, unique = true)
    private String usuario; // Nombre de usuario

    @Column(name = "password", nullable = false)
    private String password; // Encriptada usando BCrypt

    @ManyToOne
    @JoinColumn(name = "estado_usuario_id", nullable = false)
    private EstadoUsuarioEntity estadoUsuario;

    @ManyToOne
    @JoinColumn(name = "tipo_usuario_id", nullable = false)
    private TipoUsuarioEntity tipoUsuario;

    @OneToOne(mappedBy = "usuario")
    private PersonaEntity persona; // Relación con PersonaEntity
}
