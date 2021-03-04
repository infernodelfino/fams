package fa.training.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import fa.training.entity.Candidate;

/**
 * This repository to get list candidate from database.
 * 
 * @author ThanhDT19
 *
 */
public interface CandidateRepository extends JpaRepository<Candidate, Integer> {
  /**
   * This method to get all candidate.
   * 
   * @Query sql statement to select data from datbase
   * @return list candidate
   */
  @Query(value = "select c.status, t.skill, count(*) as countData "
      + "from candidate c inner join trainee t on c.trainee_id = t.id"
      + " group by t.skill, c.status", nativeQuery = true)
  public List<Object[]> countCandidateGroupBySkill();

  /**
   * This method to get list candidate with condition location.
   * 
   * @Query sql statement to select data from datbase
   * @param location candidate's location
   * @return list candidate with condition location
   */
  @Query(value = "select c.status, t.skill, count(*) as countData "
      + "from candidate c inner join trainee t on c.trainee_id = t.id"
      + " where c.location = ?1 group by t.skill, c.status", nativeQuery = true)
  public List<Object[]> countCandidateGroupBySkillByLocation(String location);

  /**
   * This method to get list candidate with condition status.
   * 
   * @Query sql statement to select data from datbase
   * @param status candidate's status
   * @return list candidate with condition status
   */
  @Query(value = "select c.status, t.skill, count(*) as countData "
      + "from candidate c inner join trainee t on c.trainee_id = t.id"
      + " where c.status = ?1 group by t.skill, c.status", nativeQuery = true)
  public List<Object[]> countCandidateGroupBySkillByStatus(String status);

  /**
   * This method to get list candidate with condition status and location.
   * 
   * @Query sql statement to select data from datbase
   * @param status   candidate's status
   * @param location candidate's location
   * @return list candidate with condition status and location
   */
  @Query(value = "select c.status, t.skill, count(*) as countData "
      + "from candidate c inner join trainee t on c.trainee_id = t.id"
      + " where c.status = ?1 and c.location = ?2"
      + " group by t.skill, c.status", nativeQuery = true)
  public List<Object[]> countCandidateGroupBySkillByStatusAndLocation(
      String status, String location);

  /**
   * This method to get list candidate with condition status and location when
   * status = {test-fail, test-pass, interview-fail, interview-pass}.
   * 
   * @Query sql statement to select data from datbase
   * @param status1  candidate's status from request
   * @param status2  candidate's status from request
   * @param status3  candidate's status from request
   * @param status4  candidate's status from request
   * @param location candidate's location from request
   * @return list object[]
   */
  @Query(value = "select c.status, t.skill, count(*) as countData "
      + "from candidate c inner join trainee t on c.trainee_id = t.id"
      + " where c.status = ?1 or c.status = ?2 or c.status = ?3 "
      + "or c.status = ?4 group by t.skill, c.status", nativeQuery = true)
  public List<Object[]> countCandidateGroupBySkillByStatusAndLocationOr(
      String status1, String status2, String status3, String status4,
      String location);

  /**
   * This method to get list candidate with condition status and location when
   * status = {test-fail, test-pass, interview-fail, interview-pass}.
   * 
   * @Query sql statement to select data from datbase
   * @param status1 candidate's status from from request
   * @param status2 candidate's status from from request
   * @param status3 candidate's status from from request
   * @param status4 candidate's status from from request
   * @return list object[]
   */
  @Query(value = "select c.status, t.skill, count(*) as countData "
      + "from candidate c inner join trainee t on c.trainee_id = t.id"
      + " where c.status = ?1 or c.status = ?2 or c.status = ?3 "
      + "or c.status = ?4 group by t.skill, c.status", nativeQuery = true)
  public List<Object[]> countCandidateGroupBySkillByStatusOr(String status1,
      String status2, String status3, String status4);

  /**
   * This method to get distinct list location from candidate table.
   * 
   * @Query sql statement to select data from candidate table
   * @return return list location
   */
  @Query("select distinct c.location from Candidate c")
  public List<String> getLocations();

}
