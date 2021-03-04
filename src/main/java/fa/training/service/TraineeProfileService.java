package fa.training.service;

import java.time.LocalDate;

import fa.training.entity.Trainee;
import fa.training.entity.vo.TraineeProfileVo;

public interface TraineeProfileService {

  // find by ID
  public Trainee findTrainee(int id);

  // delete with findBy
  boolean deleteTrainee(int id, boolean active);

  // update Trainee by save
  Trainee updateTrainee(TraineeProfileVo traineeProfileVo);

  // find by name, dateOfBirth, phone, email
  boolean findDuplicateTrainee(String name, LocalDate dateOfBirth, String phone,
      String email);
}
