package fa.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import fa.training.entity.University;

public interface UniversityRepository
    extends JpaRepository<University, Integer> {

  University findByUniversityName(String name);
}
