package fa.training.service.impl;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fa.training.entity.Allowance;
import fa.training.entity.Commitment;
import fa.training.entity.LearningPath;
import fa.training.entity.Trainee;
import fa.training.repository.CommitmentRepository;
import fa.training.service.CommitmentService;
import fa.training.utils.Constants;
import fa.training.utils.Log4J;

/**
 * (C) Copyright 2020 Fresher Academy. All Rights Reserved.
 * 
 * @author Minh Tuan
 * @date Apr 3, 2020
 * @version 1.0
 */
@Service
@Transactional
public class CommitmentServiceImpl implements CommitmentService {

  @Autowired
  CommitmentRepository commitmentRepository;

  /**
   * 
   */
  @Override
  public Commitment getByTraineeId(int traineeID) throws Exception {
    if (traineeID <= 0) {
      Log4J.getLogger().error("The traineeId is null");
      throw new Exception("The traineeId is null");
    } else {
      return commitmentRepository.findByTraineeId(traineeID);
    }
  }

  /**
   * function to save a list of Gpa to database.
   * 
   * @param commitmentToSave list Gpa to save
   * @return list gpa after have been saved
   */
  @Override
  public Commitment saveCommitment(Commitment commitmentToSave) throws Exception {
    if (commitmentToSave == null) {
      return new Commitment();
    } else {
      return commitmentRepository.save(commitmentToSave);
    }
  }

  @Override
 	public void deleteByTraineeId(int traineeId) {
 		commitmentRepository.deleteByTraineeId(traineeId);
 	}
  /**
   * function to caculate commitment of trainee.
   * 
   * @param setMileStone  the set of learningPath has been group by mileStoneName
   * @param trainee       the current trainee want to save information
   * @param learningPaths the learning path of the trainee
   */
  @Override
  public Commitment caculateCommitment(List<LearningPath> learningPaths, Trainee trainee) {

    if(learningPaths.size()==0||learningPaths==null) {
      return new Commitment();
    }
    Map<String, List<LearningPath>> setMileStone = learningPaths.stream()
        .collect(Collectors.groupingBy(LearningPath::getMilestoneName));

    int workingDuration = 0;
    int totalMilestone = setMileStone.entrySet().size();
    Log4J.getLogger().info(totalMilestone);
    int totalLearnDay = 0;
    double commitedValue = 0;
    // Caculate working duration
    if (totalMilestone <= 1) {
      workingDuration = 4;
    }
    else if (totalMilestone > 1 && totalMilestone <= 2) {
      workingDuration = 6;
    } else {
      workingDuration = 12;
    }
    // Sort list to find the nearest milestone
    Collections.sort(learningPaths, new Comparator<LearningPath>() {

      @Override
      public int compare(LearningPath o1, LearningPath o2) {
        return o1.getMilestoneName().compareTo(o2.getMilestoneName());
      }
    });
    // get the nearest milestone
    LearningPath lastLearningPath = learningPaths.get(learningPaths.size() - 1);

    // get the farest milestone
    LearningPath firstLearningPath = learningPaths.get(0);

    // Caculate the total Learning Day
    totalLearnDay = getTotalLearnDay(firstLearningPath, lastLearningPath);
    commitedValue = caculateCommitedValue(totalLearnDay, trainee);
    LocalDate workingStartDate = null;
    LocalDate workingEndDate = null;
    try {
      workingStartDate = lastLearningPath.getEndMilestoneDate().plusDays(1);
      workingEndDate = workingStartDate.plusMonths(workingDuration);
    } catch (Exception e) {
      // TODO: handle exception
    }
    Commitment commitment;
    if (trainee.getCommitment() == null) {
      commitment = new Commitment((int) commitedValue, workingDuration, workingStartDate, workingEndDate, trainee);
    } else {
      commitment = trainee.getCommitment();
      commitment.setCommitmentValue((int) commitedValue);
      commitment.setWorkingDuration(workingDuration);
      commitment.setStartDate(workingStartDate);
      commitment.setEndDate(workingEndDate);
      commitment.setTrainee(trainee);
    }
    return commitment;
  }

  /**
   * Method get total learn day of the trainee
   * 
   * @param firstLearningPath
   * @param lastLearningPath
   * @return total learning day of trainee from first day study to the last day
   *         study
   */
  public int getTotalLearnDay(LearningPath firstLearningPath, LearningPath lastLearningPath) {
    
    int totalLearningDay = 0;
    try {
      LocalDate startLearnDate = firstLearningPath.getStartMilestoneDate();
      LocalDate endLearnDate = lastLearningPath.getEndMilestoneDate();
      
      
      Calendar startDate = Calendar.getInstance();
      startDate.set(startLearnDate.getYear(), startLearnDate.getMonthValue(), startLearnDate.getDayOfMonth());

      Calendar endDate = Calendar.getInstance();
      endDate.set(endLearnDate.getYear(), endLearnDate.getMonthValue(), endLearnDate.getDayOfMonth());

      while (!startDate.after(endDate)) {
        int day = startDate.get(Calendar.DAY_OF_WEEK);
        if ((day != Calendar.SATURDAY) && (day != Calendar.SUNDAY)) {
          totalLearningDay++;
        }
        // increment start date, otherwise while will give infinite loop
        startDate.add(Calendar.DATE, 1);
      }
      
    } catch (Exception e) {
      // TODO: handle exception
    }
    return totalLearningDay;

  }

  /**
   * Method caculate the commited value.
   * 
   * @param totalLearnDay total learning day of trainee
   * @param trainee       want to set commited value for it and get list allowance
   *                      from it
   * @return the commited value after caculate
   */
  public double caculateCommitedValue(int totalLearnDay, Trainee trainee) {
    double totalAllowance = 0;
    if ((trainee.getAllowances() != null) && (trainee.getAllowances().size() != 0)) {
      for (Allowance oneAllowance : trainee.getAllowances()) {
        totalAllowance = totalAllowance + oneAllowance.getStandardAllowance();
      }
    }
    double commitedValue = totalLearnDay*Constants.VALUE_PER_DAY + totalAllowance;
    return commitedValue;
  }
}
