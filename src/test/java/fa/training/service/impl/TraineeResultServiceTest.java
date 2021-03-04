package fa.training.service.impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import fa.training.entity.Allocation;
import fa.training.entity.Allowance;
import fa.training.entity.Commitment;
import fa.training.entity.Gpa;
import fa.training.entity.LearningPath;
import fa.training.entity.RewardPenalty;
import fa.training.entity.Trainee;
import fa.training.exception.InvalidDateException;
import fa.training.exception.PointOutOfRange;
import fa.training.repository.GpaRepository;
import fa.training.repository.TraineeRepository;
import fa.training.service.AllocationService;
import fa.training.service.AllowanceService;
import fa.training.service.CommitmentService;
import fa.training.service.GpaService;
import fa.training.service.LearningPathService;
import fa.training.service.RewardPenaltyService;
import fa.training.service.impl.AllocationServiceImpl;
import fa.training.service.impl.GpaServiceImpl;
import fa.training.service.impl.LearningPathServiceImpl;
import fa.training.service.impl.RewardPenaltyServiceImpl;
import fa.training.service.impl.TraineeResultServiceImpl;
import fa.training.utils.Message;

@RunWith(SpringRunner.class)
public class TraineeResultServiceTest {
  @Mock
  LearningPathService learningPathService;
  @Mock
  RewardPenaltyService rewardPenaltyService;
  @Mock
  GpaService gpaService;
  @Mock
  AllocationService allocationService;
  @Mock
  AllowanceService allowanceService;
  @Mock
  CommitmentService commitmentService;
  @InjectMocks
  TraineeResultServiceImpl traineeResultServiceImpl;
  
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
  public void testSaveTraineeCase1() throws Exception {
    Trainee trainee = new Trainee();
    trainee.setId(4);
    
    List<LearningPath> learningPaths = new ArrayList<>();
    List<RewardPenalty> rewardPenalties = new ArrayList<>();
    List<Gpa> gpas = new ArrayList<>();
    List<Allowance> allowances = new ArrayList<>();
    Commitment commitment = new Commitment();
    Allocation allocation = new Allocation();
    when(learningPathService.findByTraineeId(4)).thenReturn(learningPaths);
    when(gpaService.getListByLearningPath(learningPaths, trainee)).thenReturn(gpas);
    when(gpaService.saveGpa(gpas)).thenReturn(gpas);
    when(allowanceService.getAllowanceByGpa(gpas, learningPaths, trainee)).thenReturn(allowances);
    when(allowanceService.saveAllowance(allowances)).thenReturn(allowances);
    when(commitmentService.caculateCommitment(learningPaths, trainee)).thenReturn(commitment);
    when(commitmentService.saveCommitment(commitment)).thenReturn(commitment);
    when(allocationService.saveAllocation(allocation, null)).thenReturn(allocation);
    String actual = traineeResultServiceImpl.saveTraineeResult(trainee);
    assertEquals(Message.MSG22, actual);
  }
  @Test(expected = InvalidDateException.class)
  public void testSaveTraineeCase2() throws Exception {
    Trainee trainee = new Trainee();
    trainee.setId(3);
    
    List<LearningPath> learningPaths = new ArrayList<>();
    LearningPath learningPath3 = new LearningPath();
    learningPath3.setId(2);
    learningPath3.setMilestoneName("Thg05-20");
    learningPath3.setMaxScore(10.0);
    learningPath3.setPassScore(6.0);
    learningPath3.setScore(10.0);
    learningPath3.setWeightedNumber(4.0);
    
    learningPaths.add(learningPath3);
    
    List<RewardPenalty> rewardPenalties = new ArrayList<>();
    RewardPenalty rewardPenalty2 = new 
        RewardPenalty("Thg05-20", LocalDate.of(2020, 4, 12), 0, 1, "vi pham", trainee);
    rewardPenalties.add(rewardPenalty2);
    trainee.setRewardPenalty(rewardPenalties);
    when(rewardPenaltyService.saveRewardPenalty(rewardPenalties)).thenThrow(InvalidDateException.class);
    traineeResultServiceImpl.saveTraineeResult(trainee);
  }
  @Test(expected = InvalidDateException.class)
  public void testSaveTraineeCase3() throws Exception {
    Trainee trainee = new Trainee();
    trainee.setId(3);
    
    List<LearningPath> learningPaths = new ArrayList<>();
    LearningPath learningPath3 = new LearningPath();
    learningPath3.setId(2);
    learningPath3.setMilestoneName("Thg05-20");
    learningPath3.setMaxScore(10.0);
    learningPath3.setPassScore(6.0);
    learningPath3.setScore(10.0);
    learningPath3.setWeightedNumber(4.0);
    
    learningPaths.add(learningPath3);
    
    Commitment commitment = new Commitment();
    commitment.setStartDate(LocalDate.of(2020, 4, 1));
    trainee.setCommitment(commitment);
    Allocation allocation = new Allocation();
    allocation.setStartDate(LocalDate.of(2020, 3, 29));
    trainee.setAllocation(allocation);
    when(learningPathService.findByTraineeId(3)).thenReturn(learningPaths);
    when(commitmentService.caculateCommitment(learningPaths, trainee)).thenReturn(commitment);
    when(commitmentService.saveCommitment(commitment)).thenReturn(commitment);
    when(allocationService.saveAllocation(allocation, LocalDate.of(2020, 4, 1))).thenThrow(InvalidDateException.class);
    traineeResultServiceImpl.saveTraineeResult(trainee);
  }
}
