package effective.mobile.TaskManagmentSytem.repositories;

import effective.mobile.TaskManagmentSytem.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsById(Long id);
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}
