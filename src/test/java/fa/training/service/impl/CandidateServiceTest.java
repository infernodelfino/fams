package fa.training.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import fa.training.entity.vo.DataVo;
import fa.training.repository.CandidateRepository;
import fa.training.service.impl.CandidateServiceImpl;
import fa.training.utils.Log4J;

@RunWith(MockitoJUnitRunner.class)
public class CandidateServiceTest {
  @InjectMocks
  CandidateServiceImpl candidateService;

  @Mock
  CandidateRepository candidateRepository;

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
  }

  @Before
  public void setUp() throws Exception {
  }

  /**
   * This method to test get candidate by location when exist data.
   */
  @Test
  public void testCountCandidateGroupBySkillByLocationUtc01() {
    Object[] object1 = { "new", "JAVA", 2 };
    Object[] object2 = { "new", "PHP", 5 };
    Object[] object3 = { "transferred", "JAVA", 2 };
    Object[] object4 = { "test-pass", "C#", 2 };
    List<Object[]> candidates = new ArrayList<Object[]>();
    candidates.add(object1);
    candidates.add(object2);
    candidates.add(object3);
    candidates.add(object4);
    String type = "table";
    when(candidateRepository.countCandidateGroupBySkillByLocation("HaNoi"))
        .thenReturn(candidates);
    List<DataVo> dataVos = candidateService
        .countCandidateGroupBySkillByLocation("HaNoi", type);
    Log4J.getLogger().info(dataVos);
    assertEquals(4, dataVos.size());
  }

  /**
   * This method to test get candidate by location when not exist data.
   */
  @Test
  public void testCountCandidateGroupBySkillByLocationUtc02() {
    String type = "table";
    List<Object[]> candidates = new ArrayList<Object[]>();
    when(candidateRepository.countCandidateGroupBySkillByLocation("HaNoi"))
        .thenReturn(candidates);
    List<DataVo> dataVos = candidateService
        .countCandidateGroupBySkillByLocation("HaNoi", type);
    Log4J.getLogger().info(dataVos);
    assertNotEquals(4, dataVos.size());
  }

  /**
   * This method to test get candidate by status when exist data.
   */
  @Test
  public void testCountCandidateGroupBySkillByStatusUtc01() throws Exception {
    Object[] object1 = { "new", "JAVA", 2 };
    Object[] object2 = { "new", "PHP", 5 };
    List<Object[]> candidates = new ArrayList<Object[]>();
    candidates.add(object1);
    candidates.add(object2);
    String type = "table";
    when(candidateRepository.countCandidateGroupBySkillByStatus("new"))
        .thenReturn(candidates);
    String status = "planning";
    List<DataVo> dataVos = candidateService
        .countCandidateGroupBySkillByStatus(status, type);
    Log4J.getLogger().info("dataVos test: " + dataVos);
    assertEquals(2, dataVos.size());
  }

  /**
   * This method to test get candidate by status when exist data.
   */
  @Test
  public void testCountCandidateGroupBySkillByStatusUtc01inprogress()
      throws Exception {
    Object[] object1 = { "test-pass", "JAVA", 2 };
    Object[] object2 = { "interview-pass", "PHP", 5 };
    Object[] object3 = { "test-fail", "PHP", 2 };
    List<Object[]> candidates = new ArrayList<Object[]>();
    candidates.add(object1);
    candidates.add(object2);
    candidates.add(object3);
    String type = "table";
    when(candidateRepository.countCandidateGroupBySkillByStatusOr("test-pass",
        "test-fail", "interview-pass", "interview-fail"))
            .thenReturn(candidates);
    String status = "inprogress";
    List<DataVo> dataVos = candidateService
        .countCandidateGroupBySkillByStatus(status, type);
    Log4J.getLogger().info(dataVos);
    assertEquals(2, dataVos.size());
  }

  
  /**
   * This method to test get candidate by status when not exist data.
   */
  @Test
  public void testCountCandidateGroupBySkillByStatusUtc02() throws Exception {
    String type = "table";
    List<Object[]> candidates = new ArrayList<Object[]>();
    when(candidateRepository.countCandidateGroupBySkillByStatus("new"))
        .thenReturn(candidates);
    String status = "planning";
    List<DataVo> dataVos = candidateService
        .countCandidateGroupBySkillByStatus(status, type);
    Log4J.getLogger().info("dataVos test: " + dataVos);
    assertEquals(0, dataVos.size());
  }

  /**
   * This method to test get candidate by status when not exist data.
   */
  @Test
  public void testCountCandidateGroupBySkillByStatusUtc02inprogress()
      throws Exception {
    String type = "table";
    List<Object[]> candidates = new ArrayList<Object[]>();
    when(candidateRepository.countCandidateGroupBySkillByStatusOr("test-pass",
        "test-fail", "interview-pass", "interview-fail"))
            .thenReturn(candidates);
    String status = "inprogress";
    List<DataVo> dataVos = candidateService
        .countCandidateGroupBySkillByStatus(status, type);
    Log4J.getLogger().info(dataVos);
    assertEquals(0, dataVos.size());
  }

  /**
   * This method to test get candidate by status and location when exist data.
   */
  @Test
  public void testCountCandidateGroupBySkillByStatusAndLocationUtc01() {
    Object[] object1 = { "new", "JAVA", 2 };
    Object[] object2 = { "new", "PHP", 5 };
    List<Object[]> candidates = new ArrayList<Object[]>();
    candidates.add(object1);
    candidates.add(object2);
    when(candidateRepository.countCandidateGroupBySkillByStatus("new"))
        .thenReturn(candidates);
    String status = "planning";
    String location = "HaNoi";
    String type = "table";
    List<DataVo> dataVos = candidateService
        .countCandidateGroupBySkillByStatusAndLocation(status, location, type);
    Log4J.getLogger().info(dataVos);
    assertEquals(2, dataVos.size());
  }
  
  /**
   * This method to test get candidate by status and location when exist data.
   */
  @Test
  public void 
        testCountCandidateGroupBySkillByStatusAndLocationUtc01inprogress() {
    Object[] object1 = { "test-pass", "JAVA", 2 };
    Object[] object2 = { "interview-pass", "PHP", 5 };
    Object[] object3 = { "test-fail", "PHP", 2 };
    List<Object[]> candidates = new ArrayList<Object[]>();
    candidates.add(object1);
    candidates.add(object2);
    candidates.add(object3);
    when(candidateRepository.countCandidateGroupBySkillByStatusOr("test-pass",
        "test-fail", "interview-pass", "interview-fail"))
            .thenReturn(candidates);
    String status = "inprogress";
    String location = "HaNoi";
    String type = "table";
    List<DataVo> dataVos = candidateService
        .countCandidateGroupBySkillByStatusAndLocation(status, location, type);
    Log4J.getLogger().info(dataVos);
    assertEquals(2, dataVos.size());
  }

  /**
   * This method to test get candidate by status and location when not exist
   * data.
   */
  @Test
  public void testCountCandidateGroupBySkillByStatusAndLocationUtc02() {
    String type = "table";
    List<Object[]> candidates = new ArrayList<Object[]>();
    when(candidateRepository.countCandidateGroupBySkillByStatus("new"))
        .thenReturn(candidates);
    String status = "planning";
    String location = "HaNoi";
    List<DataVo> dataVos = candidateService
        .countCandidateGroupBySkillByStatusAndLocation(status, location, type);
    Log4J.getLogger().info(dataVos);
    assertEquals(0, dataVos.size());
  }

  /**
   * This method to test get candidate by status and location when not exist
   * data with status.
   */
  @Test
  public void 
       testCountCandidateGroupBySkillByStatusAndLocationUtc03() {
    String type = "table";
    List<Object[]> candidates = new ArrayList<Object[]>();
    when(candidateRepository.countCandidateGroupBySkillByStatusOr("test-pass",
        "test-fail", "interview-pass", "interview-fail"))
            .thenReturn(candidates);
    String status = "inprogress";
    String location = "HaNoi";
    List<DataVo> dataVos = candidateService
        .countCandidateGroupBySkillByStatusAndLocation(status, location, type);
    Log4J.getLogger().info(dataVos);
    assertEquals(0, dataVos.size());
  }

}
