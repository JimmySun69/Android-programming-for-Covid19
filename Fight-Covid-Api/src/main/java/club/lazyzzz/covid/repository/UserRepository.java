package club.lazyzzz.covid.repository;

import club.lazyzzz.covid.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @NonNull
    Optional<User> findByUsername(@NonNull String username);

    @NonNull
    Optional<User> findByEmail(@NonNull String email);
}
