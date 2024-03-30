package uz.ofs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.ofs.entity.FoodProductEntity;

import java.util.Optional;


public interface FoodProductRepository extends JpaRepository<FoodProductEntity, Long> {

    @Query(value = "SELECT ofsfp.* FROM ofs_food_product ofsfp WHERE ofsfp.id = :foodId AND ofsfp.status<>'DELETED'", nativeQuery = true)
    Optional<FoodProductEntity> getFoodById(@Param("foodId") Long foodId);
}
