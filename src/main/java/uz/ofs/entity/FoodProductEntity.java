package uz.ofs.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import uz.ofs.constants.TableNames;
import uz.ofs.entity.base.BaseEntity;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = TableNames.OFS_FO0D_PRODUCT)
public class FoodProductEntity extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double quantity;

    @Column(nullable = false)
    private LocalDateTime storagePeriod;

    @Column(name = "category_id", nullable = false)
    private Long categoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private CategoryEntity category;
}
