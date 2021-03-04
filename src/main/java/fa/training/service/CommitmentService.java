package fa.training.service;

import java.util.List;
import fa.training.entity.Commitment;
import fa.training.entity.LearningPath;
import fa.training.entity.Trainee;

public interface CommitmentService {
  public Commitment getByTraineeId(int traineeID) throws Exception;

  public Commitment saveCommitment(Commitment commitmentToSave) throws Exception;

  public Commitment caculateCommitment( List<LearningPath> learningPaths,
      Trainee trainee);

	void deleteByTraineeId(int traineeId);
}
