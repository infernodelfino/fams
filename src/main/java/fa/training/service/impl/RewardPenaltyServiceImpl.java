package fa.training.service.impl;

import java.time.LocalDate;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fa.training.entity.RewardPenalty;
import fa.training.exception.InvalidDateException;
import fa.training.exception.PointOutOfRange;
import fa.training.repository.RewardPenaltyRepository;
import fa.training.service.RewardPenaltyService;
import fa.training.utils.Log4J;
import fa.training.utils.Message;

/**
 * 
 * @author Minh Tuan
 *
 */
@Service
@Transactional
public class RewardPenaltyServiceImpl implements RewardPenaltyService {
  @Autowired
  RewardPenaltyRepository rewardPenaltyRepository;

  /**
   * Method get reward and penalty from trainee id.
   * 
   * @param traineeID
   * @throws Exception
   */
  @Override
  public List<RewardPenalty> getByTraineeId(int traineeID) throws Exception {
    if (traineeID <= 0) {
      Log4J.getLogger().error("The traineeId is null");
      throw new Exception();
    } else {
      return rewardPenaltyRepository.findByTraineeId(traineeID);
    }
  }
  
  /**
   * 
   */
  @Override
 	public void deleteByTraineeId1(int traineeId) {
 		rewardPenaltyRepository.deleteByTraineeId(traineeId);
 	}
  /**
   * @throws PointOutOfRange 
   * @throws InvalidDateException 
   * 
   */
  @Override
  public List<RewardPenalty> saveRewardPenalty(List<RewardPenalty> rpsToSave) 
      throws InvalidDateException, PointOutOfRange {
    if(rpsToSave.size() ==0) {
      return rpsToSave;
    }
    if (validateRewardPenalty(rpsToSave) != true) {
      return null;
    }
    else {
      return rewardPenaltyRepository.saveAll(rpsToSave);
    }
  }

  /**
   * 
   */
  @Override
  public void deleteByTraineeId(int traineeId) {
    // TODO Auto-generated method stub
    rewardPenaltyRepository.deleteByTraineeId(traineeId);
  }

  /**
   * Method validate date and point of reward and penalty.
   * 
   * @param listRp list of reward and penalty of the trainee
   * @return boolean value
   * @throws InvalidDateException when date is future date
   * @throws PointOutOfRange      when point is out of range
   */
  private boolean validateRewardPenalty(List<RewardPenalty> listRp) 
      throws InvalidDateException, PointOutOfRange {
    if (listRp.size() != 0) {
      for (RewardPenalty rewardPenalty : listRp) {
        if ((rewardPenalty.getCreatedDate() != null)
            && (rewardPenalty.getCreatedDate()
                .compareTo(LocalDate.now())) > 1) {
          throw new InvalidDateException(Message.MSG10);
        }
        if (rewardPenalty.getBonusPoint() < 0
            || rewardPenalty.getBonusPoint() > 20) {
          throw new PointOutOfRange(Message.MSG19);
        }
        if (rewardPenalty.getPenaltyPoint() < 0 
            || rewardPenalty.getPenaltyPoint() > 20) {
          throw new PointOutOfRange(Message.MSG20);
        }
      }
    }
    return true;
  }
}
