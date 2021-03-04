package fa.training.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import fa.training.entity.RewardPenalty;

@Repository
public interface RewardPenaltyRepository
    extends JpaRepository<RewardPenalty, Integer> {
  public List<RewardPenalty> findByTraineeId(int traineeId);

  @Modifying
  @Query(value = "DELETE FROM dbo.reward_penalty WHERE trainee_id = ?1",
      nativeQuery = true)
  public void deleteByTraineeId(int traineeId);
}
