package fa.training.service.impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import fa.training.entity.Allocation;
import fa.training.entity.AttendantStatus;
import fa.training.entity.Gpa;
import fa.training.entity.LearningPath;
import fa.training.entity.RewardPenalty;
import fa.training.entity.Trainee;
import fa.training.exception.MatchException;
import fa.training.repository.GpaRepository;
import fa.training.repository.TraineeRepository;
import fa.training.service.GpaService;
import fa.training.service.impl.GpaServiceImpl;
import fa.training.utils.Log4J;

@RunWith(SpringRunner.class)
public class GPAServiceTest {

  @Mock
  GpaRepository gpaRepository;
  @Mock
  TraineeRepository traineeRepository;
  @InjectMocks
  GpaServiceImpl gpaServiceImpl;

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
  }

  @AfterClass
  public static void tearDownAfterClass() throws Exception {
  }

  @Before
  public void setUp() throws Exception {
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void testFindByTraineeCase1() throws Exception {
    List<Gpa> gpasTest = new ArrayList<Gpa>();
    Trainee trainee = new Trainee();
    trainee.setId(3);
    Gpa gpa = new Gpa("Thg04-20", 0.85, 0.2, 2.0, 0.679, trainee);
    gpasTest.add(gpa);
    when(gpaRepository.findByTraineeId(3)).thenReturn(gpasTest);
    List<Gpa> actual = gpaServiceImpl.getById(3);
    assertEquals(1, actual.size());
  }

  @Test(expected = NullPointerException.class)
  public void testFindByTraineeCase2() throws Exception {
    when(gpaRepository.findByTraineeId(0)).thenThrow(new NullPointerException());
    List<Gpa> actual = gpaServiceImpl.getById(0);
  }

  @Test
  public void testFindByTraineeCase3() throws Exception {
    when(gpaRepository.findByTraineeId(4)).thenReturn(null);
    List<Gpa> actual = gpaServiceImpl.getById(4);
    assertNull(actual);
  }
  
  @Test
  public void testSaveGpaCase1() throws Exception {
    List<Gpa> gpasTest = new ArrayList<Gpa>();
    Trainee trainee = new Trainee();
    trainee.setId(3);
    Gpa gpa = new Gpa("Thg04-20", 0.85, 0.2, 2.0, 0.679, trainee);
    gpasTest.add(gpa);
    when(gpaRepository.saveAll(gpasTest)).thenReturn(gpasTest);
    List<Gpa> actual = gpaServiceImpl.saveGpa(gpasTest);
    assertEquals(1, actual.size());
  }
  @Test
  public void testSaveGpaCase2() throws Exception {
    List<Gpa> gpasTest = new ArrayList<Gpa>();
    when(gpaRepository.saveAll(gpasTest)).thenReturn(gpasTest);
    List<Gpa> actual = gpaServiceImpl.saveGpa(gpasTest);
    assertEquals(0, actual.size());
  }
  @Test(expected = MatchException.class)
  public void testSaveGpaCase3() throws MatchException  {
    List<Gpa> gpasTest = new ArrayList<Gpa>();
    Trainee trainee = new Trainee();
    trainee.setId(3);
    Gpa gpa = new Gpa("Thg04-20", 0.85, 0.2, 2.0, 0.679, trainee);
    Gpa gpa2 = new Gpa("Thg04-20", 0.85, 0.2, 2.0, 0.679, trainee);
    gpasTest.add(gpa);
    gpasTest.add(gpa2);
    when(gpaRepository.saveAll(gpasTest)).thenThrow(MatchException.class);
    List<Gpa> actual = gpaServiceImpl.saveGpa(gpasTest);
  }
  @Test
  public void testGetListByLearnPathCase1() throws Exception {
    
    List<LearningPath> learningPaths = new ArrayList<>();
    LearningPath learningPath = new LearningPath();
    learningPath.setId(1);
    learningPath.setMilestoneName("Thg04-20");
    learningPath.setMaxScore(10.0);
    learningPath.setPassScore(6.0);
    learningPath.setScore(8.0);
    learningPath.setWeightedNumber(4.0);
    LearningPath learningPath2 = new LearningPath();
    learningPath2.setId(2);
    learningPath2.setMilestoneName("Thg04-20");
    learningPath2.setMaxScore(10.0);
    learningPath2.setPassScore(6.0);
    learningPath2.setScore(9.0);
    learningPath2.setWeightedNumber(4.0);
    
    learningPaths.add(learningPath);
    learningPaths.add(learningPath2);

    
    Trainee trainee = new Trainee();
    List<AttendantStatus> listAttend = new ArrayList<>();
    AttendantStatus attendantStatus = new AttendantStatus();
    attendantStatus.setMilestoneName("Thg04-20");
    attendantStatus.setDisciplinaryPoint(20.0);
    listAttend.add(attendantStatus);
    trainee.setAttendantStatus(listAttend);
    
    List<RewardPenalty> listRewPen = new ArrayList<>();
    RewardPenalty rewardPenalty = new RewardPenalty();
    rewardPenalty.setMilestoneName("Thg04-20");
    rewardPenalty.setBonusPoint(2);
    listRewPen.add(rewardPenalty);
    trainee.setRewardPenalty(listRewPen);
    
    
//    GpaServiceImpl serviceImpl = new GpaServiceImpl();
    List<Gpa> gpas = gpaServiceImpl.getListByLearningPath(learningPaths, trainee);
    Log4J.getLogger().info(gpas);
    assertEquals(1, gpas.size());
    assertEquals(gpas.get(0).getAcacdemicMark(),(Double)0.85);
  }
  
  @Test
  public void testGetListByLearnPathCase2() throws Exception {
    
    List<LearningPath> learningPaths = new ArrayList<>();
    LearningPath learningPath = new LearningPath();
    learningPath.setId(1);
    learningPath.setMilestoneName("Thg04-20");
    learningPath.setMaxScore(10.0);
    learningPath.setPassScore(6.0);
    learningPath.setScore(8.0);
    learningPath.setWeightedNumber(4.0);
    LearningPath learningPath2 = new LearningPath();
    learningPath2.setId(2);
    learningPath2.setMilestoneName("Thg04-20");
    learningPath2.setMaxScore(10.0);
    learningPath2.setPassScore(6.0);
    learningPath2.setScore(9.0);
    learningPath2.setWeightedNumber(4.0);
    
    LearningPath learningPath3 = new LearningPath();
    learningPath3.setId(2);
    learningPath3.setMilestoneName("Thg05-20");
    learningPath3.setMaxScore(10.0);
    learningPath3.setPassScore(6.0);
    learningPath3.setScore(10.0);
    learningPath3.setWeightedNumber(4.0);
    
    learningPaths.add(learningPath);
    learningPaths.add(learningPath2);
    learningPaths.add(learningPath3);

    
    Trainee trainee = new Trainee();
    List<AttendantStatus> listAttend = new ArrayList<>();
    AttendantStatus attendantStatus = new AttendantStatus();
    attendantStatus.setMilestoneName("Thg04-20");
    attendantStatus.setDisciplinaryPoint(20.0);
    listAttend.add(attendantStatus);
    trainee.setAttendantStatus(listAttend);
    
    List<RewardPenalty> listRewPen = new ArrayList<>();
    RewardPenalty rewardPenalty = new RewardPenalty();
    rewardPenalty.setMilestoneName("Thg04-20");
    rewardPenalty.setBonusPoint(2);
    listRewPen.add(rewardPenalty);

    RewardPenalty rewardPenalty2 = new RewardPenalty();
    rewardPenalty2.setMilestoneName("Thg04-20");
    rewardPenalty2.setPenaltyPoint(1);
    listRewPen.add(rewardPenalty2);
    trainee.setRewardPenalty(listRewPen);
    
    trainee.setRewardPenalty(listRewPen);
    List<Gpa> gpas = gpaServiceImpl.getListByLearningPath(learningPaths, trainee);
    Log4J.getLogger().info(gpas);
    assertEquals(2, gpas.size());
  }
  
  @Test
  public void testGetListByLearnPathCase3() throws Exception {
    
    List<LearningPath> learningPaths = new ArrayList<>();
    LearningPath learningPath = new LearningPath();
    learningPath.setId(1);
    learningPath.setMilestoneName("Thg04-20");
    learningPath.setMaxScore(10.0);
    learningPath.setPassScore(6.0);
    learningPath.setScore(8.0);
    learningPath.setWeightedNumber(4.0);
    LearningPath learningPath2 = new LearningPath();
    learningPath2.setId(2);
    learningPath2.setMilestoneName("Thg04-20");
    learningPath2.setMaxScore(10.0);
    learningPath2.setPassScore(6.0);
    learningPath2.setScore(9.0);
    learningPath2.setWeightedNumber(4.0);
    
    LearningPath learningPath3 = new LearningPath();
    learningPath3.setId(2);
    learningPath3.setMilestoneName("Thg05-20");
    learningPath3.setMaxScore(10.0);
    learningPath3.setPassScore(6.0);
    learningPath3.setScore(10.0);
    learningPath3.setWeightedNumber(4.0);
    
    learningPaths.add(learningPath);
    learningPaths.add(learningPath2);
    learningPaths.add(learningPath3);

    
    Trainee trainee = new Trainee();
    List<AttendantStatus> listAttend = new ArrayList<>();
    
    List<RewardPenalty> listRewPen = new ArrayList<>();
    RewardPenalty rewardPenalty = new RewardPenalty();
    rewardPenalty.setMilestoneName("Thg04-20");
    rewardPenalty.setBonusPoint(2);
    listRewPen.add(rewardPenalty);

    RewardPenalty rewardPenalty2 = new RewardPenalty();
    rewardPenalty2.setMilestoneName("Thg04-20");
    rewardPenalty2.setPenaltyPoint(1);
    listRewPen.add(rewardPenalty2);
    trainee.setRewardPenalty(listRewPen);
    
    trainee.setRewardPenalty(listRewPen);
    List<Gpa> gpas = gpaServiceImpl.getListByLearningPath(learningPaths, trainee);
    Log4J.getLogger().info(gpas);
    assertEquals(2, gpas.size());
  }
  
  @Test
  public void testGetListByLearnPathCase4() throws Exception {
    
    List<LearningPath> learningPaths = new ArrayList<>();
    LearningPath learningPath = new LearningPath();
    learningPath.setId(1);
    learningPath.setMilestoneName("Thg04-20");
    learningPath.setMaxScore(10.0);
    learningPath.setPassScore(6.0);
    learningPath.setScore(8.0);
    learningPath.setWeightedNumber(4.0);
    LearningPath learningPath2 = new LearningPath();
    learningPath2.setId(2);
    learningPath2.setMilestoneName("Thg04-20");
    learningPath2.setMaxScore(10.0);
    learningPath2.setPassScore(6.0);
    learningPath2.setScore(9.0);
    learningPath2.setWeightedNumber(4.0);
    
    LearningPath learningPath3 = new LearningPath();
    learningPath3.setId(2);
    learningPath3.setMilestoneName("Thg05-20");
    learningPath3.setMaxScore(10.0);
    learningPath3.setPassScore(6.0);
    learningPath3.setScore(10.0);
    learningPath3.setWeightedNumber(4.0);
    
    learningPaths.add(learningPath);
    learningPaths.add(learningPath2);
    learningPaths.add(learningPath3);

    Trainee trainee = new Trainee();
    List<AttendantStatus> listAttend = new ArrayList<>();
    
    List<Gpa> gpas = gpaServiceImpl.getListByLearningPath(learningPaths, trainee);
    Log4J.getLogger().info(gpas);
    Log4J.getLogger().info(gpas);
    assertEquals(2, gpas.size());
  }
  
  @Test
  public void testGetListByLearnPathCase5() throws Exception {
    
    List<LearningPath> learningPaths = new ArrayList<>();
   
    Trainee trainee = new Trainee();
    List<AttendantStatus> listAttend = new ArrayList<>();
    List<Gpa> gpas = gpaServiceImpl.getListByLearningPath(learningPaths, trainee);
    Log4J.getLogger().info(gpas);
    Log4J.getLogger().info(gpas);
    assertEquals(0, gpas.size());
  }
  
  
  
}
