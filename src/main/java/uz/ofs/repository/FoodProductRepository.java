package uz.ofs.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.ofs.entity.FoodProductEntity;

import java.util.List;
import java.util.Optional;


public interface FoodProductRepository extends JpaRepository<FoodProductEntity, Long> {

    @Query(value = "SELECT ofsfp.* FROM ofs_food_product ofsfp WHERE ofsfp.id = :foodId AND ofsfp.status<>'DELETED'", nativeQuery = true)
    Optional<FoodProductEntity> getFoodById(@Param("foodId") Long foodId);

    @Query(value = "SELECT ofsfp.* FROM ofs_food_product ofsfp WHERE ofsfp.status <> 'DELETED' "+
            "         AND (:categoryId IS NULL OR ofsfp.category_id = :categoryId) " +
            "         AND (:name IS NULL OR ofsfp.name = :name) ", nativeQuery = true)
    Page<FoodProductEntity> getPageFoodProduct(@Param("categoryId") Long categoryId, @Param("name") String name, Pageable page);

    @Modifying
    @Query(value = "UPDATE ofs_food_product SET status = 'DELETED' WHERE id=:id", nativeQuery = true)
    void delete(@Param("id") Long id);
}
