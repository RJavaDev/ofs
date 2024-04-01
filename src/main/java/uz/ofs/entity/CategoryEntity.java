package uz.ofs.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import uz.ofs.constants.CategoryType;
import uz.ofs.constants.EntityStatus;
import uz.ofs.constants.TableNames;
import uz.ofs.dto.CategoryDto;
import uz.ofs.entity.base.BaseEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Table(name = TableNames.OFS_CATEGORY)
public class CategoryEntity extends BaseEntity implements Serializable {

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Enumerated(value = EnumType.STRING)
    private CategoryType type;

    @Column(name = "parent_id")
    private Long parentId;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    List<CategoryEntity> children = new ArrayList<>();

    @JsonIgnore
    public CategoryDto getDto(){
        return getDto(false);
    }

    @JsonIgnore
    public CategoryDto getDto(boolean withChildren) {
        CategoryDto dto = new CategoryDto();
        BeanUtils.copyProperties(this, dto);
        if (this.getChildren() != null && withChildren) {
            dto.setChildren(
                    this.getChildren().stream()
                            .map(CategoryEntity::getDto)
                            .filter(p -> p.getStatus() != EntityStatus.DELETED).collect(Collectors.toList()));
        }
        return dto;
    }
}
