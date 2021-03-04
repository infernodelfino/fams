package fa.training.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fa.training.entity.Trainee;
import fa.training.entity.vo.TraineeProfileVo;
import fa.training.repository.TraineeRepository;
import fa.training.service.FacultyService;
import fa.training.service.TraineeProfileService;
import fa.training.service.UniversityService;
import fa.training.utils.Log4J;
import fa.training.utils.Message;

/**
 * Trainee's services such as find, deactive, check duplicate and update.
 * 
 * @author Tung Linh.
 */
@Service
public class TraineeProfileServiceImpl implements TraineeProfileService {

  @Autowired
  private TraineeRepository traineeRepository;

  @Autowired
  private UniversityService universityService;

  @Autowired
  private FacultyService facultyService;
  
  @Autowired
  private AccountDetailsServiceImpl userDetailsService;

  /**
   * this is to find trainee by Id.
   */
  @Override
  public Trainee findTrainee(int id) {
    if (traineeRepository.findById(id) != null) {
      return traineeRepository.findById(id);
    } else {
      Log4J.getLogger().error("No such trainee found with id: " + id);
      return null;
    }
  }

  /**
   * This is to deactivate a trainee by setting its active attribute to False.
   */
  @Override
  public boolean deleteTrainee(int id, boolean active) {
    Trainee foundTrainee = null;
    // Found = true, not found = false;
    if (traineeRepository.findById(id) != null) {
      foundTrainee = traineeRepository.findById(id);
      foundTrainee.setActive(active);
      traineeRepository.save(foundTrainee);
      return true;
    }
    return false;
  }

  /**
   * if the trainee doesn't exist have a duplicate with the same name,
   * dateOfBirth, phone and email then update it.
   */
  @Override
  public Trainee updateTrainee(TraineeProfileVo traineeVo) {
    Trainee trainee = updateTraineeInformation(traineeVo);
    if (!findDuplicateTrainee(trainee.getName(), trainee.getDateOfBirth(),
        trainee.getPhone(), trainee.getEmail())) {
      Log4J.getLogger().error(Message.MSG13);
      return null;
    } else {
      trainee.setUniversity(universityService
          .findUniversityByName(traineeVo.getUniversityName()));
      trainee.setFaculty(
          facultyService.findByFacultyName(traineeVo.getFacultyName()));
      return traineeRepository.save(trainee);
    }
  }

  /**
   * This is to update a trainee if the trainee has already existed. Going to
   * use name, gender, dateOfBirth, university...
   */
  private Trainee updateTraineeInformation(TraineeProfileVo traineeVo) {
    Trainee trainee = traineeRepository.findById(traineeVo.getId());
    if (trainee != null) {
      trainee.setAccount(updateAccount(traineeVo.getName()));
      trainee.setName(traineeVo.getName());
      trainee.setGender(traineeVo.getGender() == 1 ? true : false);
      trainee.setDateOfBirth(updateDateOfBirth(traineeVo.getDateOfBirth()));
      trainee.setPhone(traineeVo.getPhone());
      trainee.setEmail(traineeVo.getEmail());
      trainee.setSalaryPaid(traineeVo.getSalaryPaid() == 1 ? true : false);
      trainee.settPBAccount(traineeVo.gettPBAccount());
      trainee.setAllowanceGroup(traineeVo.getAllowanceGroup());
      trainee.setHistory(updateHistory());
    }
    return trainee;
  }

  /**
   * to format a String to LocalDate with yyyy-MM-dd format.
   * 
   * @param dateOfBirth is from JSP page.
   * @return the formatted LocalDate.
   */
  private LocalDate updateDateOfBirth(String dateOfBirth) {
    return LocalDate.parse(dateOfBirth,
        DateTimeFormatter.ofPattern("yyyy-MM-dd"));
  }

  /**
   * to update history after you edit it.
   * 
   * @return history.
   */
  private String updateHistory() {
    StringBuilder history = new StringBuilder();
    history.append(LocalDate.now()).append(" created by - ")
    .append(userDetailsService.getCurrentUsername());
    return history.toString();
  }

  /**
   * to update account after you edit it.
   * 
   * @param name is the name of trainee.
   * @return account.
   */
  private String updateAccount(String name) {
    String[] splitedName = name.split(" ");
    StringBuilder accountName = new StringBuilder();
    for (int i = 0; i < splitedName.length; i++) {
      if (i < splitedName.length - 1) {
        accountName.append(splitedName[i].charAt(0));
      } else {
        accountName.insert(0, splitedName[i]);
      }
    }
    return accountName.toString();
  }

  /**
   * used to find duplicate trainee with the same name, dateOfBirth, phone and
   * email.
   */
  @Override
  public boolean findDuplicateTrainee(String name, LocalDate dateOfBirth,
      String phone, String email) {
    // Found = false, not founds = true
    if (traineeRepository.findByNameAndDateOfBirthAndPhoneAndEmail(name,
        dateOfBirth, phone, email) != null) {
      return false;
    }
    return true;
  }
}