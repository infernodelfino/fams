package fa.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import fa.training.entity.Faculty;

public interface FacultyRepository extends JpaRepository<Faculty, Integer> {

  Faculty findByFacultyName(String name);
}
