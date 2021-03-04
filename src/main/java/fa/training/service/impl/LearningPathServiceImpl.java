package fa.training.service.impl;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fa.training.entity.LearningDetail;
import fa.training.entity.LearningPath;
import fa.training.entity.vo.Milestone;
import fa.training.exception.LearnPathException;
import fa.training.repository.LearningDetailRepository;
import fa.training.repository.LearningPathRepository;
import fa.training.service.LearningPathService;

@Service
public class LearningPathServiceImpl implements LearningPathService {
  @Autowired
  private LearningPathRepository learningPathRepository;
  @Autowired
  private LearningDetailRepository learningDetailRepository;

  @Override
  public List<LearningPath> findByTraineeId(int id) {
    return learningPathRepository.getByTraineeId(id);
  }

  /**
   * . This method to save, update or delete LearningPath
   */
  @Override
  @Transactional(rollbackOn = { LearnPathException.class })
  public boolean saveOrUpdateListLearningPath(int traineeId,
      List<LearningPath> listOfLearningPath) throws LearnPathException {
    if (listOfLearningPath == null) {
      return false;
    }
    List<LearningPath> listLearnPathDatabase = learningPathRepository
        .findAllLearningPathByTraineeId(traineeId);
    listLearnPathDatabase.forEach(x -> {
      if (!listOfLearningPath.contains(x)) {
        learningPathRepository.delete(x); // learing path bi xoa tren giao dien
      }
    });
    for (LearningPath learningPath : listOfLearningPath) {
      if (learningPath == null) { // validate learning path
        return false;
      } else if (this.validateLearnPath(learningPath)) {
        learningPathRepository.save(learningPath); // save or update
      }
    }
    return true;
  }

  /**
   * . this method to get information about milestone
   */
  @Override
  public List<Milestone> getMilestoneByTraineeId(int traineeId) {
    List<Object[]> listObject = learningPathRepository
        .getListMilestoneById(traineeId);
    List<Milestone> listMilestone = new ArrayList<Milestone>();
    for (Object[] object : listObject) {
      Milestone milestone = new Milestone();
      milestone.setMilestoneName(object[0].toString());
      milestone.setSalaryPaid(object[1].toString());
      String dateStr = object[2].toString();
      String[] date = dateStr.split("-");
      milestone.setStartDate(LocalDate.of(Integer.parseInt(date[0]),
          Integer.parseInt(date[1]), Integer.parseInt(date[2])));
      String dateStr2 = object[3].toString();
      String[] date2 = dateStr2.split("-");
      milestone.setEndDate(LocalDate.of(Integer.parseInt(date2[0]),
          Integer.parseInt(date2[1]), Integer.parseInt(date2[2])));
      listMilestone.add(milestone);
    }
    return listMilestone;
  }

  /**
   * . This method to get all LearningPath by TraineeId
   */
  @Override
  public List<LearningPath> getAllLearningPathByTraineeId(int traineeId) {
    return learningPathRepository.findAllLearningPathByTraineeId(traineeId);
  }

  /**
   * . This method to get list LearningDetail by TraineeId
   */
  @Override
  public List<LearningDetail> findLearnDetailByTrainee(int traineeId) {
    List<Object[]> learnCodeObjectList = learningPathRepository
        .findLearnCodeByTraineeId(traineeId);
    String learnCode = "";
    if (learnCodeObjectList.size() > 0) {
      Object[] learnCodeObject = learnCodeObjectList.get(0);
      learnCode = learnCodeObject[0].toString();
    }
    return learningDetailRepository.findByLearnCode(learnCode);
  }

  /**
   * . This method to get list LearningDetail by learnCode
   */
  @Override
  public List<LearningDetail> findLearnDetailByLearCode(String learnCode) {
    return learningDetailRepository.findByLearnCode(learnCode);
  }

  /**.
   *  This method to validate learningPath
   * @param learningPath learningPath need check
   * @return true if data valid
   * @throws LearnPathException when learningPath invalid
   */
  private boolean validateLearnPath(LearningPath learningPath)
      throws LearnPathException {
    Double score = learningPath.getScore();
    Double maxScore = learningPath.getMaxScore();
    Double passScore = learningPath.getPassScore();
    Double weightedNumber = learningPath.getWeightedNumber();
    LocalDate startDate = learningPath.getStartMilestoneDate();
    LocalDate endDate = learningPath.getEndMilestoneDate();
    return checkLearnPath(score, maxScore, passScore, weightedNumber, startDate,
        endDate);
  }

  /**.
   * This method to validate score, maxScore, passScore, weightedNumber,
   * startDate, endDate
   * @param score data need check
   * @param maxScore data need check
   * @param passScore data need check
   * @param weightedNumber data need check
   * @param startDate data need check
   * @param endDate data need check
   * @return true if data valid
   * @throws LearnPathException when data invalid
   */
  private boolean checkLearnPath(Double score, Double maxScore,
      Double passScore, Double weightedNumber, LocalDate startDate,
      LocalDate endDate) throws LearnPathException {
    if (score == null || maxScore == null || passScore == null
        || weightedNumber == null || startDate == null || endDate == null) {
      throw new LearnPathException("please fill file data.");
    }
    if (passScore > maxScore) {
      throw new LearnPathException(
          "“Passing Score” must not be greater than “Max Score”.");
    }
    if (score > maxScore) {
      throw new LearnPathException(
          "“Score” must not be greater than “Max Score”.");
    }
    long days = ChronoUnit.DAYS.between(startDate, endDate);
    if (days < 1) {
      throw new LearnPathException(
          "“Start Date” must not be later than “End Date”.");
    }
    return true;
  }

}