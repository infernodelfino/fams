package fa.training.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import fa.training.entity.Classes;
import fa.training.entity.vo.DataVo;

/**
 * This repository to get list class from database.
 *  
 * @author ThanhDT19
 *
 */
public interface ClassRepository extends JpaRepository<Classes, Integer> {
  /**
   * This method to get all class.
   * 
   * @Query sql statement to select data from datbase
   * @return list class
   */
  @Query("select new fa.training.entity.vo.DataVo(c.status , c.sill , count(*))"
      + " from Classes c " + " group by c.status, c.sill")
  public List<DataVo> countClassesGroupBySkill();

  /**
   * This method to get list class with condition location.
   * 
   * @Query sql statement to select data from datbase
   * @param location class's location from brower's input
   * @return list class with condition location
   */
  @Query("select new fa.training.entity.vo.DataVo(c.status , c.sill , count(*))"
      + " from Classes c where c.location = ?1" + " group by c.status, c.sill")
  public List<DataVo> countClassesGroupBySkillByLocation(String location);

  /**
   * This method to get list class with condition status.
   * 
   * @Query sql statement to select data from datbase
   * @param status class's status from brower's input
   * @return list class with condition status
   */
  @Query("select new fa.training.entity.vo.DataVo(c.status , c.sill , count(*))"
      + " from Classes c where c.status = ?1" + " group by c.status, c.sill")
  public List<DataVo> countClassesGroupBySkillByStatus(String status);

  /**
   * This method to get list class with condition status and location.
   * 
   * @Query sql statement to select data from datbase
   * @param status   class's status from brower's input
   * @param location class's location from brower's input
   * @return list class with condition status and location
   */
  @Query("select new fa.training.entity.vo.DataVo(c.status , c.sill , count(*))"
      + " from Classes c where c.status = ?1 AND c.location = ?2"
      + " group by c.status, c.sill")
  public List<DataVo> countClassesGroupBySkillByStatusAndLocation(String status,
      String location);
  
  /**
   * This method to get list distinct location from class table.
   * 
   * @return list location
   */
  @Query("select distinct c.location from Classes c")
  public List<String> getLocations();

}
