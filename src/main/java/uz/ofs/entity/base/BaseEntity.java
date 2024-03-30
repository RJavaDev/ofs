package uz.ofs.entity.base;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import uz.ofs.constants.EntityStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status", length = 32, columnDefinition = "varchar(32) default 'CREATED'")
    @Enumerated(value = EnumType.STRING)
    private EntityStatus status = EntityStatus.CREATED;

    @Column(name = "createdDate")
    private LocalDateTime createdDate;

    @Column(name = "createdBy")
    private Long createdBy;

    public void forCreate() {
        forCreate(null);
    }

    public void forCreate(Long creatorId) {
        this.setCreatedBy(creatorId);
        this.setCreatedDate(LocalDateTime.now());
        this.setStatus(EntityStatus.CREATED);
    }
}
