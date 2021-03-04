package fa.training.service.impl;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fa.training.entity.Allocation;
import fa.training.exception.InvalidDateException;
import fa.training.repository.AllocationRepository;
import fa.training.service.AllocationService;
import fa.training.utils.Log4J;
import fa.training.utils.Message;

/**
 * (C) Copyright 2020 Fresher Academy. All Rights Reserved.
 * 
 * @author Minh Tuan
 * @date Apr 3, 2020
 * @version 1.0
 */
@Service
public class AllocationServiceImpl implements AllocationService {

  @Autowired
  AllocationRepository allocationRepository;

  /**
   * Method allocation of the current trainee.
   * 
   * @param traineeID the id of trainee to get allocation
   * 
   */
  @Override
  public Allocation getByTrainee(int traineeID) throws Exception {
    if (traineeID <= 0) {
      Log4J.getLogger().error("The traineeId is null");
      throw new NullPointerException("The traineeID is null");
    } else {
      return allocationRepository.findByTraineeId(traineeID);
    }
  }

  /**
   * 
   */
  @Override
	public void deleteByTraineeId(int traineeId) {
		allocationRepository.deleteByTraineeId(traineeId);
	}
  /**
   * function to save a list of Gpa to database.
   * 
   * @param allocationToSave list Gpa to save
   * @return list gpa after have been saved
   */
  @Override
  public Allocation saveAllocation(Allocation allocationToSave, 
      LocalDate commitStartDate) throws Exception {
    if (allocationToSave == null) {
      throw new Exception("No Allocation to save");
    } else {
      if (validateAllocation(allocationToSave, commitStartDate) == true) {
        return allocationRepository.save(allocationToSave);
      } else {
        return null;
      }
    }
  }

  /**
   * 
   * @param allocation      allocation of trainee want to validate
   * @param commitStartDate start date trainee have to work
   * @return boolean value
   * @throws InvalidDateException if start date is before commit start date
   */
  private boolean validateAllocation(Allocation allocation, 
      LocalDate commitStartDate)
      throws InvalidDateException {
    if((commitStartDate==null)&&(allocation.getStartDate() != null )) {
      throw new InvalidDateException("The commited start date is empty so can't have allocation start date ");
    }
    if (allocation.getStartDate() != null 
        && (allocation.getStartDate().compareTo(commitStartDate) < 0)) {
      throw new InvalidDateException(Message.MSG21);
    }
    return true;
  }
}
