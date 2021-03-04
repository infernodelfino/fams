package fa.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import fa.training.entity.Commitment;

@Repository
public interface CommitmentRepository
    extends JpaRepository<Commitment, Integer> {
  public Commitment findByTraineeId(int traineeId);

  @Modifying
  @Query(value = "DELETE FROM dbo.commitment WHERE trainee_id = ?1", 
      nativeQuery = true)
  public void deleteByTraineeId(int traineeId);
}
