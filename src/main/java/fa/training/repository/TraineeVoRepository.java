package fa.training.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import fa.training.entity.Trainee;

/**
 * This repository to get list trainee from database.
 * 
 * @author ThanhDT19
 *
 */
public interface TraineeVoRepository extends JpaRepository<Trainee, Integer> {

  @Query(value = "select distinct c.location "
      + "from trainee t inner join classes c on t.class_id = c.id", 
      nativeQuery = true)
  public List<String> getLocations();

  /**
   * This method to get all trainee.
   * 
   * @Query sql statement to select data from datbase
   * @return list trainee
   */
  @Query(value = "select c.status, c.sill, count(*) as countData "
      + "from trainee t inner join classes c on t.class_id = c.id"
      + " group by c.sill, c.status", nativeQuery = true)
  public List<Object[]> countTraineesGroupBySkill();

  /**
   * This method to get list trainee with condition location.
   * 
   * @Query sql statement to select data from datbase
   * @param location trainee's location from brower's input
   * @return list trainee with condition location
   */
  @Query(value = "select c.status, c.sill, count(*) as countData "
      + "from trainee t inner join classes c on t.class_id = c.id"
      + " where c.location = ?1 group by c.sill, c.status", nativeQuery = true)
  public List<Object[]> countTraineesGroupBySkillByLocation(String location);

  /**
   * This method to get list trainee with condition status.
   * 
   * @Query sql statement to select data from datbase
   * @param status trainee's status from brower's input
   * @return list trainee with condition status
   */
  @Query(value = "select c.status, c.sill, count(*) as countData "
      + "from trainee t inner join classes c on t.class_id = c.id"
      + " where c.status = ?1 group by c.sill, c.status", nativeQuery = true)
  public List<Object[]> countTraineesGroupBySkillByStatus(String status);

  /**
   * This method to get list trainee with condition status and location.
   * 
   * @Query sql statement to select data from datbase
   * @param status   trainee's status from brower's input
   * @param location trainee's location from brower's input
   * @return list trainee with condition status and location
   */
  @Query(value = "select c.status, c.sill, count(*) as countData "
      + "from trainee t inner join classes c on t.class_id = c.id"
      + " where c.status = ?1 and c.location = ?2 group by c.sill, c.status", 
      nativeQuery = true)
  public List<Object[]> countTraineesGroupBySkillByStatusAndLocation(
      String status, String location);

}
