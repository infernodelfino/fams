package fa.training.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import fa.training.entity.Gpa;

@Repository
public interface GpaRepository extends JpaRepository<Gpa, Integer> {
  public List<Gpa> findByTraineeId(int traineeId);

  @Modifying
  @Query(value = "DELETE FROM dbo.gpa WHERE trainee_id = ?1", 
      nativeQuery = true)
  public void deleteByTraineeId(int traineeId);
}
