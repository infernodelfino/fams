package fa.training.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import fa.training.entity.LearningDetail;
import fa.training.entity.LearningPath;
import fa.training.entity.Trainee;

@Repository
public interface LearningPathRepository
    extends JpaRepository<LearningPath, Integer> {
  public List<LearningPath> getByTraineeId(int idTrainee);

  @Query("Select distinct e.milestoneName, e.salaryPaid,"
      + " e.startMilestoneDate,e.endMilestoneDate from LearningPath e")
  public List<Object[]> getListMilestone();

  @Query(value = "Select distinct e.milestone_name,"
      + " e.salary_paid, e.start_milestone_date,"
      + "e.end_milestone_date from learning_path e where e.trainee_id=?1",
      nativeQuery = true)
  public List<Object[]> getListMilestoneById(int traineeId);

  @Query(value = "Select * from learning_path e where e.trainee_id=?1",
      nativeQuery = true)
  public List<LearningPath> findAllLearningPathByTraineeId(int traineeId);

  @Query("Select e.learningCode From LearningPath e Where e.trainee=?1")
  List<LearningDetail> findLearnDetailByTrainee(Trainee trainee);

  @Query(value = "select distinct learning_code_learn_code from"
      + " learning_path where trainee_id = ?1", nativeQuery = true)
  List<Object[]> findLearnCodeByTraineeId(int traineeId);

  @Transactional
  @Modifying
  @Query("Delete From LearningPath where learningCode=?1")
  public int deleteByLearnDetail(LearningDetail learningDetail);

  public long countByLearningCode(LearningDetail learningDetail);
}
