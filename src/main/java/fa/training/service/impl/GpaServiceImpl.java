package fa.training.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fa.training.entity.AttendantStatus;
import fa.training.entity.Gpa;
import fa.training.entity.LearningPath;
import fa.training.entity.RewardPenalty;
import fa.training.entity.Trainee;
import fa.training.exception.MatchException;
import fa.training.repository.GpaRepository;
import fa.training.service.GpaService;
import fa.training.utils.Constants;
import fa.training.utils.Log4J;

/**
 * 
 * @author Minh Tuan
 *
 */
@Service
@Transactional
public class GpaServiceImpl implements GpaService {

  @Autowired
  GpaRepository gpaRepository;

  /**
   * function to get Gpa score of a certain trainee by his/her id.
   * 
   * @param traineeId id of trainee want to get his/her Gpa score
   */
  @Override
  public List<Gpa> getById(int traineeId) throws Exception {
    if (traineeId <= 0) {
      Log4J.getLogger().error("The traineeId is null");
      throw new NullPointerException();
    } else {
      return gpaRepository.findByTraineeId(traineeId);
    }
  }

  /**
   * function to save a list of Gpa to database.
   * 
   * @param gpasToSave list Gpa to save
   * @return list gpa after have been saved
   */
  @Override
  public List<Gpa> saveGpa(List<Gpa> gpasToSave) throws MatchException {
    if(gpasToSave == null) {
      return null;
    }
    if (gpasToSave.size() == 0) {
      return gpasToSave;
    }
    for (Gpa gpa : gpasToSave) {
      if (Collections.frequency(gpasToSave, gpa) > 1) {
        throw new 
        MatchException("Have more than one GPA with the same milestoneName");
      }
    }
    return gpaRepository.saveAll(gpasToSave);
  }

  @Override
  public void deleteByTraineeId(int traineeId) throws Exception {
    if (traineeId <= 0) {
      Log4J.getLogger().error("The traineeId is null");
      throw new Exception();
    } else {
      gpaRepository.deleteByTraineeId(traineeId);
    }
  }

  /**
   * function caculate Gpa score from Learning Path.
   * 
   * @param setMileStone the set of learningPath has been group by mileStoneName
   * @param trainee      the current trainee want to save infomation
   */
  @Override
  public List<Gpa> getListByLearningPath(List<LearningPath> learningPaths, 
      Trainee trainee)
      throws Exception {
    
    if (learningPaths == null) {
      return null;
    }
    Map<String, List<LearningPath>> setMileStone = learningPaths.stream()
        .collect(Collectors.groupingBy(LearningPath::getMilestoneName));
    
    List<Gpa> gpas = new ArrayList<>();
    if (learningPaths.size()==0) {
      return gpas;
    }
    // Retrieve every milestone
    setMileStone.entrySet().forEach(l -> {
      double disciplinePoint = 0;
      if (trainee.getAttendantStatus() != null) {
        disciplinePoint = getDisciplinePoint(l.getKey(), 
            trainee.getAttendantStatus());
      }
      // Get the reward penalty from view that match the milestone name
      double bonus = caculateBonusPoint(trainee.getRewardPenalty(), l.getKey());
      double penalty = caculatePenaltyPoint(trainee.getRewardPenalty(), l.getKey());

      // assume we already get the value from above
      Gpa gpa = new Gpa();
      gpa.setMilestoneName(l.getKey());
      gpa.setDisciplinaryPoint(disciplinePoint);
      gpa.setBonus(bonus);
      gpa.setPenalty(penalty);

      // Caculate academic mark from topic
      double totalScore = 0.0;
      double totalMaxScore = 0.0;
      // The size of the set milestone from above
      int totalSize = l.getValue().size();
      for (int i = 0; i < totalSize; i++) {
        totalScore = totalScore + (l.getValue().get(i).getScore()) * (l.getValue().get(i).getWeightedNumber());
        totalMaxScore = totalMaxScore + (l.getValue().get(i).getMaxScore()) * (l.getValue().get(i).getWeightedNumber());
      }

      double academicMark = 0.0;
      academicMark = totalScore / totalMaxScore;
      Log4J.getLogger().info(academicMark);

      // set value for Gpa
      gpa.setAcacdemicMark(academicMark);
      gpa.setTrainee(trainee);

      double gpaScoreNotRound = 0;
      if (penalty == 0) {
        gpaScoreNotRound = (academicMark * Constants.X + disciplinePoint / 100 * Constants.Y + bonus * Constants.Z);
      }
      if (penalty != 0) {
        gpaScoreNotRound = academicMark * Constants.X + disciplinePoint / 100 * Constants.Y - penalty * Constants.W;
      }

      double gpaScore = 0.0;
      gpaScore = Math.round(gpaScoreNotRound * 1000.0) / 1000.0;
      gpa.setGpa(gpaScore);
      gpas.add(gpa);
    });
    gpas.forEach(g -> Log4J.getLogger().info(g));

    return gpas;
  }

  /**
   * 
   * @param mileStoneName   name of milestone to search by that in the attendant
   *                        list
   * @param attendantStatus list of attendant get from trainee
   * @return disciplinePoint
   * @throws Exception throw when the parameter passed are null or empty
   */
  private double getDisciplinePoint(String mileStoneName, List<AttendantStatus> attendantStatus) {
    double disciplinePoint = 0;
    if (mileStoneName == "") {
      return 0;
    }
    if (attendantStatus == null || attendantStatus.size() == 0) {
      return 0;
    }
    for (AttendantStatus oneAttendant : attendantStatus) {
      if ((oneAttendant.getDisciplinaryPoint() != null) && (mileStoneName.equals(oneAttendant.getMilestoneName()))) {

        disciplinePoint = oneAttendant.getDisciplinaryPoint();
      }
    }
    return disciplinePoint;
  }
  
  /**
   * function caculate the bonus point from the list reward input
   * @param rewardPenalties list reward penalty input
   * @param mileStoneName milestone name to find the match milestone name in list input
   * @return bonus point
   */
  private int caculateBonusPoint(List<RewardPenalty> rewardPenalties, String mileStoneName) {
    int bonus = 0;
    try {
      Map<String, List<RewardPenalty>> setMileStone = rewardPenalties.stream()
          .collect(Collectors.groupingBy(RewardPenalty::getMilestoneName));
      List<RewardPenalty> rpMatchMilestone = setMileStone.get(mileStoneName);
      for (RewardPenalty rewardPenalty : rpMatchMilestone) {
        bonus = bonus + rewardPenalty.getBonusPoint();
      }
    } catch (NullPointerException e) {
      Log4J.getLogger().error(e);
    }
    return bonus;
  }
  
  /**
   * function caculate the penalty point from the list reward input
   * @param rewardPenalties list reward penalty input
   * @param mileStoneName milestone name to find the match milestone name in list input
   * @return penalty point
   */
  private int caculatePenaltyPoint(List<RewardPenalty> rewardPenalties, String mileStoneName) {
    int penalty = 0;
    try {
      Map<String, List<RewardPenalty>> setMileStone = rewardPenalties.stream()
          .collect(Collectors.groupingBy(RewardPenalty::getMilestoneName));
      List<RewardPenalty> rpMatchMilestone = setMileStone.get(mileStoneName);
      for (RewardPenalty rewardPenalty : rpMatchMilestone) {
        penalty = penalty + rewardPenalty.getPenaltyPoint();
      }
    } catch (NullPointerException e) {
      Log4J.getLogger().error(e);
    }
    return penalty;
  }
}
