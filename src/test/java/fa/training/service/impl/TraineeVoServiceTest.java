package fa.training.service.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
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
import fa.training.repository.TraineeVoRepository;
import fa.training.service.impl.TraineeVoServiceImpl;
import fa.training.utils.Log4J;

@RunWith(MockitoJUnitRunner.class)
public class TraineeVoServiceTest {

  @InjectMocks
  TraineeVoServiceImpl traineeService;

  @Mock
  TraineeVoRepository traineeRepository;

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
  }

  @Before
  public void setUp() throws Exception {
  }

  /**
   * This method to test get trainee by location when exist data.
   */
  @Test
  public void testCountTraineesGroupBySkillByLocationUtc01() {
    Object[] object1 = { "planning", "JAVA", 2 };
    Object[] object2 = { "planning", "PHP", 5 };
    Object[] object3 = { "planned", "JAVA", 2 };
    Object[] object4 = { "inprogress", "C#", 2 };
    List<Object[]> trainees = new ArrayList<Object[]>();
    trainees.add(object1);
    trainees.add(object2);
    trainees.add(object3);
    trainees.add(object4);
    String type = "table";
    when(traineeRepository.countTraineesGroupBySkillByLocation("HaNoi"))
        .thenReturn(trainees);
    List<DataVo> dataVos = traineeService
        .countTraineesGroupBySkillByLocation("HaNoi", type);
    Log4J.getLogger().info(dataVos);
    assertTrue(dataVos.size() == 4);
  }

  /**
   * This method to test get trainee by location when not exist data.
   */
  @Test
  public void testCountTraineesGroupBySkillByLocationUtc02() {
    String type = "table";
    List<Object[]> trainees = new ArrayList<Object[]>();
    when(traineeRepository.countTraineesGroupBySkillByLocation("HaNoi"))
        .thenReturn(trainees);
    List<DataVo> dataVos = traineeService
        .countTraineesGroupBySkillByLocation("HaNoi", type);
    Log4J.getLogger().info(dataVos);
    assertFalse(dataVos.size() == 4);
  }

  /**
   * This method to test get trainee by status when exist data.
   */
  @Test
  public void testCountTraineesGroupBySkillByStatusUtc01() {
    Object[] object1 = { "planning", "JAVA", 2 };
    Object[] object2 = { "planning", "PHP", 5 };
    List<Object[]> trainees = new ArrayList<Object[]>();
    trainees.add(object1);
    trainees.add(object2);
    String type = "table";
    when(traineeRepository.countTraineesGroupBySkillByStatus("planning"))
        .thenReturn(trainees);
    List<DataVo> dataVos = traineeService
        .countTraineesGroupBySkillByStatus("planning", type);
    Log4J.getLogger().info(dataVos);
    assertTrue(dataVos.size() == 2);
  }

  /**
   * This method to test get trainee by status when not exist data.
   */
  @Test
  public void testCountTraineesGroupBySkillByStatusUtc02() {
    String type = "table";
    List<Object[]> trainees = new ArrayList<Object[]>();
    when(traineeRepository.countTraineesGroupBySkillByLocation("HaNoi"))
        .thenReturn(trainees);
    List<DataVo> dataVos = traineeService
        .countTraineesGroupBySkillByStatus("planning", type);
    Log4J.getLogger().info(dataVos);
    assertFalse(dataVos.size() == 2);
  }

  /**
   * This method to test get trainee by status and location when exist data.
   */
  @Test
  public void testCountTraineesGroupBySkillByStatusAndLocationUtc01() {
    Object[] object1 = { "planning", "JAVA", 2 };
    Object[] object2 = { "planning", "PHP", 5 };
    List<Object[]> trainees = new ArrayList<Object[]>();
    trainees.add(object1);
    trainees.add(object2);
    when(traineeRepository
        .countTraineesGroupBySkillByStatusAndLocation("planning", "HaNoi"))
            .thenReturn(trainees);
    String type = "table";
    List<DataVo> dataVos = traineeService
        .countTraineesGroupBySkillByStatusAndLocation("planning", "HaNoi",
            type);
    Log4J.getLogger().info(dataVos);
    assertTrue(dataVos.size() == 2);
  }

  /**
   * This method to test get trainee by status and location when not exist data.
   */
  @Test
  public void testCountTraineesGroupBySkillByStatusAndLocationUtc02() {
    String type = "table";
    List<Object[]> trainees = new ArrayList<Object[]>();
    when(traineeRepository
        .countTraineesGroupBySkillByStatusAndLocation("planning", "HaNoi"))
            .thenReturn(trainees);
    List<DataVo> dataVos = traineeService
        .countTraineesGroupBySkillByStatusAndLocation("planning", "HaNoi",
            type);
    assertTrue(dataVos.size() == 0);
  }

}
