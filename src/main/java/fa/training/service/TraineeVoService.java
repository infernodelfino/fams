package fa.training.service;

import java.util.List;
import fa.training.entity.vo.DataVo;

/**
 * This interface to define method to handle business logic about trainee.
 * 
 * @author ThanhDT19
 *
 */
public interface TraineeVoService {

  /**
   * Define method get all trainee.
   * 
   * @return list trainee
   */
  public List<DataVo> countTraineesGroupBySkill(String type);

  /**
   * Define method to get list trainee with condition location.
   * 
   * @param location trainee's location from browser's input
   * @return list trainee with condition location
   */
  public List<DataVo> countTraineesGroupBySkillByLocation(String location,
      String type);

  /**
   * Define method to get list trainee with condition status.
   * 
   * @param status trainee's status from browser's input
   * @return list trainee with condition status
   */
  public List<DataVo> countTraineesGroupBySkillByStatus(String status,
      String type);

  /**
   * Define method to get list trainee with condition status.
   * 
   * @param status   trainee's status from browser's input
   * @param location trainee's location from browser's input
   * @return list trainee with condition status and location
   */
  public List<DataVo> countTraineesGroupBySkillByStatusAndLocation(
      String status, String location, String type);

  /**
   * Define method get location.
   * 
   * @return list location
   */
  public List<String> getLocations();
}
