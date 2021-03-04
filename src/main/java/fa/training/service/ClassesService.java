package fa.training.service;

import java.util.List;
import fa.training.entity.vo.DataVo;

/**
 * This interface to define method to handle business logic about class.
 * 
 * @author ThanhDT19
 *
 */
public interface ClassesService {
  /**
   * Define method get all class.
   * 
   * @return list class
   */
  public List<DataVo> countClassesGroupBySkill(String type);

  /**
   * Define method to get list class with condition location.
   * 
   * @param location class's location from browser's input
   * @return list class with condition location
   */
  public List<DataVo> countClassesGroupBySkillByLocation(String location,
      String type);

  /**
   * Define method to get list class with condition status.
   * 
   * @param status class's status from browser's input
   * @return list class with condition status
   */
  public List<DataVo> countClassesGroupBySkillByStatus(String status,
      String type);

  /**
   * Define method to get list class with condition status.
   * 
   * @param status   class's status from browser's input
   * @param location class's location from browser's input
   * @return list class with condition status and location
   */
  public List<DataVo> countClassesGroupBySkillByStatusAndLocation(String status,
      String location, String type);

  /**
   * This method to get list distinct location from class table.
   * 
   * @return list location
   */
  public List<String> getLocations();

}
