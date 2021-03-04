package fa.training.service.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import fa.training.entity.LearningDetail;
import fa.training.entity.LearningPath;
import fa.training.entity.Topic;
import fa.training.entity.Trainee;
import fa.training.entity.vo.Milestone;
import fa.training.exception.LearnPathException;
import fa.training.repository.LearningDetailRepository;
import fa.training.repository.LearningPathRepository;
import fa.training.utils.Log4J;

/**
 * .
 * 
 * @author hoangminhduong
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class LearningPathServiceImplTest {
  @InjectMocks
  private LearningPathServiceImpl learningPathServiceImp;
  @Mock
  private LearningPathRepository learningPathRepository;
  @Mock
  private LearningDetailRepository learningDetailRepository;

  /**
   * Test method for test saveOrUpdateListLearningPath() use case 1.
   * 
   * @throws LearnPathException if LearningPath invalid
   */
  @Test
  public void testSaveOrUpdateListLearningPathUC1() throws LearnPathException {
    List<LearningPath> listLearningPathDB = new ArrayList<LearningPath>();
    listLearningPathDB.add(new LearningPath(1, new Trainee(1), "Thg03-20",
        "Yes", LocalDate.of(2020, 3, 1), LocalDate.of(2020, 3, 31), 9.0, 5.0,
        6.0, 9.0, new LearningDetail("JAVA", new Topic(1))));
    listLearningPathDB.add(new LearningPath(2, new Trainee(1), "Thg05-20",
        "Yes", LocalDate.of(2020, 5, 1), LocalDate.of(2020, 5, 31), 9.0, 7.0,
        5.0, 9.0, new LearningDetail("JAVA", new Topic(3))));
    listLearningPathDB.add(new LearningPath(2, new Trainee(1), "Thg07-20",
        "Yes", LocalDate.of(2020, 7, 1), LocalDate.of(2020, 7, 31), 8.0, 7.0,
        5.0, 9.0, new LearningDetail("JAVA", new Topic(5))));
    int traineeId = 1;
    when(learningPathRepository.findAllLearningPathByTraineeId(traineeId))
        .thenReturn(listLearningPathDB);
    List<LearningPath> listLearningPathInput = new ArrayList<LearningPath>();
    listLearningPathInput.add(new LearningPath(1, new Trainee(1), "Thg03-20",
        "Yes", LocalDate.of(2020, 3, 1), LocalDate.of(2020, 3, 31), 9.0, 5.0,
        6.0, 9.0, new LearningDetail("JAVA", new Topic(1))));
    listLearningPathInput.add(new LearningPath(2, new Trainee(1), "Thg05-20",
        "Yes", LocalDate.of(2020, 5, 1), LocalDate.of(2020, 5, 31), 9.0, 7.0,
        5.0, 9.0, new LearningDetail("JAVA", new Topic(3))));
    listLearningPathInput.add(new LearningPath(2, new Trainee(1), "Thg07-20",
        "Yes", LocalDate.of(2020, 7, 1), LocalDate.of(2020, 7, 31), 8.0, 7.0,
        5.0, 9.0, new LearningDetail("JAVA", new Topic(5))));
    listLearningPathInput.add(new LearningPath(0, new Trainee(1), "Thg08-20",
        "Yes", LocalDate.of(2020, 8, 1), LocalDate.of(2020, 8, 30), 8.0, 7.0,
        5.0, 9.0, new LearningDetail("JAVA", new Topic(6))));
    listLearningPathInput.forEach(x -> {
      when(learningPathRepository.save(x)).thenReturn(x);
    });
    boolean result = learningPathServiceImp
        .saveOrUpdateListLearningPath(traineeId, listLearningPathInput);
    assertTrue(result);
  }

  /**
   * Test method for test saveOrUpdateListLearningPath() use case 2.
   * 
   * @throws LearnPathException if LearningPath invalid
   */
  @Test
  public void testSaveOrUpdateListLearningPathUC2() throws LearnPathException {
    List<LearningPath> listLearningPathDB = new ArrayList<LearningPath>();
    listLearningPathDB.add(new LearningPath(1, new Trainee(1), "Thg03-20",
        "Yes", LocalDate.of(2020, 3, 1), LocalDate.of(2020, 3, 31), 9.0, 5.0,
        6.0, 9.0, new LearningDetail("JAVA", new Topic(1))));
    listLearningPathDB.add(new LearningPath(2, new Trainee(1), "Thg05-20",
        "Yes", LocalDate.of(2020, 5, 1), LocalDate.of(2020, 5, 31), 9.0, 7.0,
        5.0, 9.0, new LearningDetail("JAVA", new Topic(3))));
    listLearningPathDB.add(new LearningPath(2, new Trainee(1), "Thg07-20",
        "Yes", LocalDate.of(2020, 7, 1), LocalDate.of(2020, 7, 31), 8.0, 7.0,
        5.0, 9.0, new LearningDetail("JAVA", new Topic(5))));
    int traineeId = 1;
    when(learningPathRepository.findAllLearningPathByTraineeId(traineeId))
        .thenReturn(listLearningPathDB);
    List<LearningPath> listLearningPathInput = new ArrayList<LearningPath>();
    listLearningPathInput.add(new LearningPath(1, new Trainee(1), "Thg03-20",
        "Yes", LocalDate.of(2020, 3, 1), LocalDate.of(2020, 3, 31), 9.0, 5.0,
        6.0, 9.0, new LearningDetail("JAVA", new Topic(1))));
    listLearningPathInput.add(new LearningPath(2, new Trainee(1), "Thg05-20",
        "Yes", LocalDate.of(2020, 5, 1), LocalDate.of(2020, 5, 31), 9.0, 7.0,
        5.0, 9.0, new LearningDetail("JAVA", new Topic(3))));
    listLearningPathInput.forEach(x -> {
      when(learningPathRepository.save(x)).thenReturn(x);
    });
    boolean result = learningPathServiceImp
        .saveOrUpdateListLearningPath(traineeId, listLearningPathInput);
    assertTrue(result);
  }

  /**
   * Test method for test saveOrUpdateListLearningPath() use case 3.
   * 
   * @throws LearnPathException if LearningPath invalid
   */
  @Test
  public void testSaveOrUpdateListLearningPathUC3() throws LearnPathException {
    List<LearningPath> listLearningPathDB = new ArrayList<LearningPath>();
    listLearningPathDB.add(new LearningPath(1, new Trainee(1), "Thg03-20",
        "Yes", LocalDate.of(2020, 3, 1), LocalDate.of(2020, 3, 31), 9.0, 5.0,
        6.0, 9.0, new LearningDetail("JAVA", new Topic(1))));
    listLearningPathDB.add(new LearningPath(2, new Trainee(1), "Thg05-20",
        "Yes", LocalDate.of(2020, 5, 1), LocalDate.of(2020, 5, 31), 9.0, 7.0,
        5.0, 9.0, new LearningDetail("JAVA", new Topic(3))));
    listLearningPathDB.add(new LearningPath(2, new Trainee(1), "Thg07-20",
        "Yes", LocalDate.of(2020, 7, 1), LocalDate.of(2020, 7, 31), 8.0, 7.0,
        5.0, 9.0, new LearningDetail("JAVA", new Topic(5))));
    int traineeId = 1;
    when(learningPathRepository.findAllLearningPathByTraineeId(traineeId))
        .thenReturn(listLearningPathDB);
    List<LearningPath> listLearningPathInput = new ArrayList<LearningPath>();
    listLearningPathInput.add(new LearningPath(1, new Trainee(1), "Thg03-20",
        "Yes", LocalDate.of(2020, 3, 1), LocalDate.of(2020, 3, 31), 9.0, 5.0,
        6.0, 9.0, new LearningDetail("JAVA", new Topic(1))));
    listLearningPathInput.add(new LearningPath(2, new Trainee(1), "Thg05-20",
        "Yes", LocalDate.of(2020, 5, 1), LocalDate.of(2020, 5, 31), 9.0, 7.0,
        5.0, 9.0, new LearningDetail("JAVA", new Topic(3))));
    listLearningPathInput.add(new LearningPath(2, new Trainee(1), "Thg09-20",
        "Yes", LocalDate.of(2020, 9, 1), LocalDate.of(2020, 9, 30), 8.0, 7.0,
        5.0, 9.0, new LearningDetail("JAVA", new Topic(5))));
    listLearningPathInput.forEach(x -> {
      when(learningPathRepository.save(x)).thenReturn(x);
    });
    boolean result = learningPathServiceImp
        .saveOrUpdateListLearningPath(traineeId, listLearningPathInput);
    assertTrue(result);
  }

  /**
   * Test method for test saveOrUpdateListLearningPath() use case 4.
   * 
   * @throws LearnPathException if LearningPath invalid
   */
  @Test(expected = LearnPathException.class)
  public void testSaveOrUpdateListLearningPathUC4() throws LearnPathException {
    List<LearningPath> listLearningPathDB = new ArrayList<LearningPath>();
    listLearningPathDB.add(new LearningPath(1, new Trainee(1), "Thg03-20",
        "Yes", LocalDate.of(2020, 3, 1), LocalDate.of(2020, 3, 31), 9.0, 5.0,
        6.0, 9.0, new LearningDetail("JAVA", new Topic(1))));
    listLearningPathDB.add(new LearningPath(2, new Trainee(1), "Thg05-20",
        "Yes", LocalDate.of(2020, 5, 1), LocalDate.of(2020, 5, 31), 9.0, 7.0,
        5.0, 9.0, new LearningDetail("JAVA", new Topic(3))));
    listLearningPathDB.add(new LearningPath(2, new Trainee(1), "Thg07-20",
        "Yes", LocalDate.of(2020, 7, 1), LocalDate.of(2020, 7, 31), 8.0, 7.0,
        5.0, 9.0, new LearningDetail("JAVA", new Topic(5))));
    int traineeId = 1;
    when(learningPathRepository.findAllLearningPathByTraineeId(traineeId))
        .thenReturn(listLearningPathDB);
    List<LearningPath> listLearningPathInput = new ArrayList<LearningPath>();
    listLearningPathInput.add(new LearningPath(1, new Trainee(1), "Thg03-20",
        "Yes", LocalDate.of(2020, 3, 1), LocalDate.of(2020, 3, 31), 9.0, 5.0,
        6.0, 9.0, new LearningDetail("JAVA", new Topic(1))));
    listLearningPathInput
        .add(new LearningPath(2, new Trainee(1), "Thg05-20", "Yes", null, null,
            9.0, 7.0, 5.0, 9.0, new LearningDetail("JAVA", new Topic(3))));
    listLearningPathInput.add(new LearningPath(2, new Trainee(1), "Thg09-20",
        "Yes", LocalDate.of(2020, 9, 1), LocalDate.of(2020, 9, 30), 8.0, 7.0,
        5.0, 9.0, new LearningDetail("JAVA", new Topic(5))));
    listLearningPathInput.forEach(x -> {
      when(learningPathRepository.save(x)).thenReturn(x);
    });
    boolean result = learningPathServiceImp
        .saveOrUpdateListLearningPath(traineeId, listLearningPathInput);
    assertTrue(result);
  }

  @Test
  public void testSaveOrUpdateListLearningPathUC5() throws LearnPathException {
    List<LearningPath> listLearningPathDB = new ArrayList<LearningPath>();
    listLearningPathDB.add(new LearningPath(1, new Trainee(1), "Thg03-20",
        "Yes", LocalDate.of(2020, 3, 1), LocalDate.of(2020, 3, 31), 9.0, 5.0,
        6.0, 9.0, new LearningDetail("JAVA", new Topic(1))));
    listLearningPathDB.add(new LearningPath(2, new Trainee(1), "Thg05-20",
        "Yes", LocalDate.of(2020, 5, 1), LocalDate.of(2020, 5, 31), 9.0, 7.0,
        5.0, 9.0, new LearningDetail("JAVA", new Topic(3))));
    listLearningPathDB.add(new LearningPath(2, new Trainee(1), "Thg07-20",
        "Yes", LocalDate.of(2020, 7, 1), LocalDate.of(2020, 7, 31), 8.0, 7.0,
        5.0, 9.0, new LearningDetail("JAVA", new Topic(5))));
    int traineeId = 1;
    when(learningPathRepository.findAllLearningPathByTraineeId(traineeId))
        .thenReturn(listLearningPathDB);
    List<LearningPath> listLearningPathInput = null;
    boolean result = learningPathServiceImp
        .saveOrUpdateListLearningPath(traineeId, listLearningPathInput);
    assertFalse(result);
  }

  /**
   * Test method for GetMilestoneByTraineeI() UC1.
   */
  @Test
  public void testGetMilestoneByTraineeIdUC1() {
    List<Object[]> learnCodeObjects = new ArrayList<Object[]>();
    Object[] objs = new Object[]{"Thg03-20","Yes","2020-03-01","2020-03-31"};
    learnCodeObjects.add(objs);
    Object[] objs2 = new Object[]{"Thg05-20","Yes","2020-05-01","2020-05-31"};
    learnCodeObjects.add(objs2);
    Object[] objs3 = new Object[]{"Thg09-20","Yes","2020-09-01","2020-09-30"};
    learnCodeObjects.add(objs3);
    int traineeId = 1;
    when(learningPathRepository.getListMilestoneById(traineeId))
        .thenReturn(learnCodeObjects);
    List<Milestone> listMilestone = learningPathServiceImp
        .getMilestoneByTraineeId(traineeId);
    assertTrue(3 == listMilestone.size());
  }

  @Test
  public void testGetMilestoneByTraineeIdUC2() {
    int traineeId = 0;
    List<Object[]> learnCodeObjects = new ArrayList<Object[]>();
    when(learningPathRepository.getListMilestoneById(traineeId))
        .thenReturn(learnCodeObjects);
    List<Milestone> listMilestone = learningPathServiceImp
        .getMilestoneByTraineeId(traineeId);
    assertTrue(0 == listMilestone.size());
  }

  /**
   * Test method for test UC1 for findLearnDetailByTrainee() method.
   */
  @Test
  public void testFindLearnDetailByTraineeUC1() {
    List<Object[]> learnCodeObjects = new ArrayList<Object[]>();
    Object[] objects = new Object[1];
    objects[0] = "JAVA";
    learnCodeObjects.add(objects);
    int traineeId = 1;
    when(learningPathRepository.findLearnCodeByTraineeId(traineeId))
        .thenReturn(learnCodeObjects);
    List<LearningDetail> listOfLearningDetail = new ArrayList<LearningDetail>();
    listOfLearningDetail.add(new LearningDetail(1, "JAVA", new Topic(1)));
    listOfLearningDetail.add(new LearningDetail(3, "JAVA", new Topic(3)));
    when(learningDetailRepository.findByLearnCode("JAVA"))
        .thenReturn(listOfLearningDetail);
    List<LearningDetail> result = learningPathServiceImp
        .findLearnDetailByTrainee(traineeId);
    assertTrue(result == listOfLearningDetail);

  }

  @Test
  public void testFindLearnDetailByTraineeUC2() {
    List<Object[]> learnCodeObjects = new ArrayList<Object[]>();
    int traineeId = 0;
    when(learningPathRepository.findLearnCodeByTraineeId(traineeId))
        .thenReturn(learnCodeObjects);
    List<LearningDetail> listOfLearningDetail = new ArrayList<LearningDetail>();
    when(learningDetailRepository.findByLearnCode(""))
        .thenReturn(listOfLearningDetail);
    Log4J.getLogger()
        .info("list LearningDetail size:" + listOfLearningDetail.size());
    assertTrue(0 == listOfLearningDetail.size());

  }

}
