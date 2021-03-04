package fa.training.service;

import java.util.List;
import fa.training.entity.Gpa;
import fa.training.entity.LearningPath;
import fa.training.entity.Trainee;

public interface GpaService {
  public List<Gpa> getById(int traineeId) throws Exception;

  public void deleteByTraineeId(int traineeId) throws Exception;

  List<Gpa> saveGpa(List<Gpa> gpasToSave) throws Exception;
  
  public List<Gpa> getListByLearningPath(List<LearningPath> learningPaths, 
      Trainee trainee) throws Exception;
}
