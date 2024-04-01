package uz.ofs.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.ofs.entity.UserEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query(value = "SELECT tsxu.* FROM ofs_user tsxu WHERE tsxu.username = :username AND tsxu.status <> 'DELETED'", nativeQuery = true)
    Optional<UserEntity> getByUsername(@Param("username") String username);

    @Query(value = "SELECT tsxu.* FROM ofs_user tsxu WHERE tsxu.username = :username AND tsxu.status <> 'DELETED'", nativeQuery = true)
    UserEntity getUserByUsername(@Param("username") String username);

    @Query(value = "SELECT ofsu.* FROM ofs_user ofsu WHERE ofsu.id = :id AND ofsu.status <> 'DELETED'", nativeQuery = true)
    Optional<UserEntity> getByUserId(@Param("id") Long id);

    @Modifying
    @Query(value = "UPDATE ofs_user SET status = 'DELETED' WHERE id = :userId", nativeQuery = true)
    void userDelete(@Param("userId") Long userId);

    @Query(value = "SELECT ofsu.* FROM ofs_user ofsu WHERE ofsu.status <> 'DELETED' "+
            "         AND (:username IS NULL OR ofsu.username = :username) " +
            "         AND (:firstname IS NULL OR ofsu.firstname = :firstname) ", nativeQuery = true)
    Page<UserEntity> getPageUser(@Param("username") String username, @Param("firstname") String firstname, Pageable page);
}
