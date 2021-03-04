package fa.training.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import fa.training.entity.Allowance;

@Repository
public interface AllowanceRepository extends JpaRepository<Allowance, Integer> {
  public List<Allowance> findByTraineeId(int traineeID);

  @Modifying
  @Query(value = "DELETE FROM dbo.allowance WHERE trainee_id = ?1", 
      nativeQuery = true)
  public void deleteByTraineeId(int traineeId);
}
