package club.lazyzzz.covid.repository;

import club.lazyzzz.covid.model.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    Optional<List<Attendance>> findByUserId(@NonNull Long userId);
}
