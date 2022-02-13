package pl.javastart.equipy.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByLastNameContainingIgnoreCase(String lastName);
    Optional<User> findByPesel(String pesel);
    Optional<User> findById(Long id);
}
