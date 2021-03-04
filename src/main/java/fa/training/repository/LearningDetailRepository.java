package fa.training.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import fa.training.entity.LearningDetail;

public interface LearningDetailRepository
    extends JpaRepository<LearningDetail, Integer> {

  @Query("Select distinct e.learnCode From LearningDetail e")
  public List<Object[]> getLearningCodeDistinct();

  public LearningDetail findByLearnCodeAndTopic(String code, String topic);

  @Modifying
  @Transactional
  @Query(value = "delete learning_path where trainee_id = ?1",
      nativeQuery = true)
  public void deleteLearningPathByTraineeId(int traineeId);

  public List<LearningDetail> findByLearnCode(String learnCode);

}
