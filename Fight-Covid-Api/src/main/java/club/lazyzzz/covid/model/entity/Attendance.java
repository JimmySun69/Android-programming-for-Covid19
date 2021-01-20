package club.lazyzzz.covid.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "t_attendance")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String issuer;

    @Column
    private Boolean healthy = true;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @Column
    private Long userId;

    @PrePersist
    protected void prePersist() {
        if (createTime == null) {
            createTime = new Date();
        }
    }
}
