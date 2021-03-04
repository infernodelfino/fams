package fa.training.service;

import java.util.List;
import fa.training.entity.RewardPenalty;

public interface RewardPenaltyService {
  public List<RewardPenalty> getByTraineeId(int traineeID) throws Exception;

  public List<RewardPenalty> saveRewardPenalty(List<RewardPenalty> rpsToSave) 
      throws Exception;
  
  public void deleteByTraineeId(int traineeId) throws Exception;

void deleteByTraineeId1(int traineeId);
  
}
