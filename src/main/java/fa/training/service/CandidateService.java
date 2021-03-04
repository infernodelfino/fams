package fa.training.service;

import java.util.List;
import fa.training.entity.vo.DataVo;

/**
 * This interface to define method to handle business logic about candidate.
 * 
 * @author ThanhDT19
 *
 */
public interface CandidateService {
  /**
   * Define method get all candidate.
   * 
   * @return list candidate
   */
  public List<DataVo> countCandidateGroupBySkill(String type);

  /**
   * Define method to get list candidate with condition location.
   * 
   * @param location candidate's location from browser's input
   * @return list candidate with condition location
   */
  public List<DataVo> countCandidateGroupBySkillByLocation(String location,
      String type);

  /**
   * Define method to get list candidate with condition status.
   * 
   * @param status candidate's status from browser's input
   * @return list candidate with condition status
   */
  public List<DataVo> countCandidateGroupBySkillByStatus(String status,
      String type);

  /**
   * Define method to get list candidate with condition status.
   * 
   * @param status   candidate's status from browser's input
   * @param location candidate's location from browser's input
   * @return list candidate with condition status and location
   */
  public List<DataVo> countCandidateGroupBySkillByStatusAndLocation(
      String status, String location, String type);

  /**
   * Define method get location in candidate table.
   * 
   * @return list candidate's location
   */
  public List<String> getLocations();

}
