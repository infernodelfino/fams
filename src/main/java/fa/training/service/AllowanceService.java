package fa.training.service;

import java.util.List;
import fa.training.entity.Allowance;
import fa.training.entity.Gpa;
import fa.training.entity.LearningPath;
import fa.training.entity.Trainee;

public interface AllowanceService {
  public List<Allowance> getByTraineeId(int traineeID) throws Exception;
  
  public List<Allowance> saveAllowance(List<Allowance> allowanceToSave) 
      throws Exception;

  public void deleteByTraineeId(int traineeId);

  public List<Allowance> getAllowanceByGpa(List<Gpa> gpas,
      List<LearningPath> learningPaths, Trainee trainee) throws Exception;
}
