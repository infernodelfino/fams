package fa.training.service.impl;

import static org.junit.Assert.assertEquals;
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
import fa.training.repository.ClassRepository;
import fa.training.service.impl.ClassesServiceImpl;
import fa.training.utils.Log4J;

@RunWith(MockitoJUnitRunner.class)
public class ClassesServiceTest {
  @InjectMocks
  ClassesServiceImpl classesServiceImpl;

  @Mock
  ClassRepository classRepository;

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
  }

  @Before
  public void setUp() throws Exception {
  }

  /**
   * This method to test get class by location when exist data.
   */
  @Test
  public void testCountClassesGroupBySkillByLocationUtc01() {
    DataVo dataVo1 = new DataVo("planning", "JAVA", 2);
    DataVo dataVo2 = new DataVo("planning", "PHP", 5);
    DataVo dataVo3 = new DataVo("planned", "JAVA", 2);
    DataVo dataVo4 = new DataVo("inprogress", "C#", 2);
    List<DataVo> classes = new ArrayList<DataVo>();
    classes.add(dataVo1);
    classes.add(dataVo2);
    classes.add(dataVo3);
    classes.add(dataVo4);
    String type = "table";
    when(classRepository.countClassesGroupBySkillByLocation("HaNoi"))
        .thenReturn(classes);
    List<DataVo> dataVos = classesServiceImpl
        .countClassesGroupBySkillByLocation("HaNoi", type);
    Log4J.getLogger().info(dataVos);
    assertEquals(4, dataVos.size());
  }

  /**
   * This method to test get class by location when not exist data.
   */
  @Test
  public void testCountClassesGroupBySkillByLocationUtc02() {
    List<DataVo> classes = new ArrayList<DataVo>();
    String type = "table";
    when(classRepository.countClassesGroupBySkillByLocation("HaNoi"))
        .thenReturn(classes);
    List<DataVo> dataVos = classesServiceImpl
        .countClassesGroupBySkillByLocation("HaNoi", type);
    Log4J.getLogger().info(dataVos);
    assertEquals(0, dataVos.size());
  }
  
  /**
   * This method to test get class by status when exist data.
   */
  @Test
  public void testCountClassesGroupBySkillByStatusUtc01() {
    DataVo dataVo1 = new DataVo("planning", "JAVA", 2);
    DataVo dataVo2 = new DataVo("planning", "PHP", 5);
    List<DataVo> classes = new ArrayList<DataVo>();
    classes.add(dataVo1);
    classes.add(dataVo2);
    String type = "table";
    when(classRepository.countClassesGroupBySkillByStatus("planning"))
        .thenReturn(classes);
    List<DataVo> dataVos = classesServiceImpl
        .countClassesGroupBySkillByStatus("planning", type);
    Log4J.getLogger().info(dataVos);
    assertEquals(2, dataVos.size());
  }

  /**
   * This method to test get class by location when not exist data.
   */
  @Test
  public void testCountClassesGroupBySkillByStatusUtc02() {
    List<DataVo> classes = new ArrayList<DataVo>();
    String type = "table";
    when(classRepository.countClassesGroupBySkillByStatus("planning"))
        .thenReturn(classes);
    List<DataVo> dataVos = classesServiceImpl
        .countClassesGroupBySkillByStatus("planning", type);
    Log4J.getLogger().info(dataVos);
    assertEquals(0, dataVos.size());
  }

  /**
   * This method to test get class by location and status when exist data.
   */
  @Test
  public void testCountClassesGroupBySkillByStatusAndLocationUtc01() {
    DataVo dataVo1 = new DataVo("planning", "JAVA", 2);
    DataVo dataVo2 = new DataVo("planning", "PHP", 5);
    List<DataVo> classes = new ArrayList<DataVo>();
    classes.add(dataVo1);
    classes.add(dataVo2);
    String type = "table";
    when(classRepository.countClassesGroupBySkillByStatusAndLocation("planning",
        "HaNoi")).thenReturn(classes);
    List<DataVo> dataVos = classesServiceImpl
        .countClassesGroupBySkillByStatusAndLocation("planning", "HaNoi", type);
    Log4J.getLogger().info(dataVos);
    assertEquals(2, dataVos.size());
  }

  /**
   * This method to test get class by location and status when not exist data.
   */
  @Test
  public void testCountClassesGroupBySkillByStatusAndLocationUtc02() {
    List<DataVo> classes = new ArrayList<DataVo>();
    String type = "table";
    when(classRepository.countClassesGroupBySkillByStatusAndLocation("planning",
        "HaNoi")).thenReturn(classes);
    List<DataVo> dataVos = classesServiceImpl
        .countClassesGroupBySkillByStatusAndLocation("planning", "HaNoi", type);
    Log4J.getLogger().info(dataVos);
    assertEquals(0, dataVos.size());
  }

}
