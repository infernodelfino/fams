package fa.training.service;

import java.util.List;
import fa.training.entity.LearningDetail;
import fa.training.entity.LearningPath;
import fa.training.entity.vo.Milestone;
import fa.training.exception.LearnPathException;

public interface LearningPathService {

  public List<LearningPath> findByTraineeId(int id);

  List<LearningPath> getAllLearningPathByTraineeId(int traineeId);

  boolean saveOrUpdateListLearningPath(int traineeId,
      List<LearningPath> listOfLearningPath) throws LearnPathException;

  List<Milestone> getMilestoneByTraineeId(int traineeId);

  List<LearningDetail> findLearnDetailByTrainee(int traineeId);

  public List<LearningDetail> findLearnDetailByLearCode(String learCode);

}