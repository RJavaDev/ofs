package uz.ofs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.ofs.entity.CategoryEntity;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    @Query(value = "SELECT ofsc.* FROM ofs_category ofsc WHERE ofsc.id=:categoryId AND ofsc.status <> 'DELETED'", nativeQuery = true)
    Optional<CategoryEntity> findByCategoryId(@Param("categoryId") Long categoryId);

    @Query(value = "SELECT ofsc.* FROM ofs_category ofsc WHERE ofsc.name=:name", nativeQuery = true)
    CategoryEntity getCategoryName(@Param("name") String name);

    @Query(value = "SELECT ofsc.* FROM ofs_category ofsc WHERE ofsc.status <> 'DELETED'", nativeQuery = true)
    Optional<CategoryEntity> getAllCategory();

    @Modifying
    @Query(value = "WITH RECURSIVE sub_category AS (\n" +
            "        SELECT * FROM ofs_category WHERE id = :categoryId\n" +
            "        UNION ALL\n" +
            "        SELECT child.* FROM ofs_category child\n" +
            "        INNER JOIN\n" +
            "        sub_category parent ON parent.id=child.parent_id\n" +
            ")UPDATE ofs_category SET status = 'DELETED' WHERE id IN(SELECT id FROM sub_category)", nativeQuery = true)
    void delete(@Param("categoryId") Long categoryId);
}
