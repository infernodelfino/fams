package fa.training.service;

import java.util.List;
import fa.training.entity.Trainee;

public interface TraineeResultService {
  public String saveTraineeResult(Trainee trainee) throws Exception;

  // find all trainee by active
  List<Trainee> findActiveTrainee(boolean active);

}
