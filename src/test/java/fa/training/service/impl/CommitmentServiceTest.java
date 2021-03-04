package fa.training.service.impl;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import fa.training.entity.Allowance;
import fa.training.entity.Commitment;
import fa.training.entity.LearningPath;
import fa.training.entity.Trainee;
import fa.training.service.impl.CommitmentServiceImpl;
import fa.training.utils.Log4J;

public class CommitmentServiceTest {

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
  public void caculateCommitmentServiceCase1() {
    List<LearningPath> learningPaths = new ArrayList<>();
    LearningPath learningPath = new LearningPath();
    learningPath.setId(1);
    learningPath.setMilestoneName("Thg04-20");
    learningPath.setMaxScore(10.0);
    learningPath.setStartMilestoneDate(LocalDate.of(2020, 4, 1));
    learningPath.setEndMilestoneDate(LocalDate.of(2020, 4, 29));
    learningPath.setPassScore(6.0);
    learningPath.setSalaryPaid("yes");
    learningPath.setScore(8.0);
    learningPath.setWeightedNumber(4.0);
    LearningPath learningPath2 = new LearningPath();
    learningPath2.setId(2);
    learningPath2.setMilestoneName("Thg04-20");
    learningPath2.setStartMilestoneDate(LocalDate.of(2020, 4, 1));
    learningPath2.setEndMilestoneDate(LocalDate.of(2020, 4, 29));
    learningPath2.setMaxScore(10.0);
    learningPath2.setPassScore(6.0);
    learningPath2.setSalaryPaid("no");
    learningPath2.setScore(9.0);
    learningPath2.setWeightedNumber(4.0);
    
    learningPaths.add(learningPath);
    learningPaths.add(learningPath2);
    
    Trainee trainee = new Trainee();
    trainee.setId(3);
    
    List<Allowance> allowances = new ArrayList<>();
    Allowance allowance = new Allowance();
    allowance.setMilestoneName("Thg04-20");
    allowance.setStandardAllowance(5000000.0);
    allowances.add(allowance);
    trainee.setAllowances(allowances);
    
    CommitmentServiceImpl serviceImpl = new CommitmentServiceImpl();
    Commitment actual = serviceImpl.caculateCommitment(learningPaths, trainee);
    Log4J.getLogger().info(actual);
    assertEquals(4, actual.getWorkingDuration());
  }
  
  /**
   * case already have commitment
   */
  @Test
  public void caculateCommitmentServiceCase2() {
    List<LearningPath> learningPaths = new ArrayList<>();
    LearningPath learningPath = new LearningPath();
    learningPath.setId(1);
    learningPath.setMilestoneName("Thg04-20");
    learningPath.setMaxScore(10.0);
    learningPath.setPassScore(6.0);
    learningPath.setSalaryPaid("yes");
    learningPath.setScore(8.0);
    learningPath.setWeightedNumber(4.0);
    LearningPath learningPath2 = new LearningPath();
    learningPath2.setId(2);
    learningPath2.setMilestoneName("Thg04-20");
    learningPath2.setMaxScore(10.0);
    learningPath2.setPassScore(6.0);
    learningPath2.setSalaryPaid("no");
    learningPath2.setScore(9.0);
    learningPath2.setWeightedNumber(4.0);
    
    learningPaths.add(learningPath);
    learningPaths.add(learningPath2);
    
    Trainee trainee = new Trainee();
    trainee.setId(3);
    
    Commitment commitment = new Commitment();
    commitment.setRemark("good");
    trainee.setCommitment(commitment);
    
    List<Allowance> allowances = new ArrayList<>();
    Allowance allowance = new Allowance();
    allowance.setMilestoneName("Thg04-20");
    allowance.setStandardAllowance(5000000.0);
    
    CommitmentServiceImpl serviceImpl = new CommitmentServiceImpl();
    Commitment actual = serviceImpl.caculateCommitment(learningPaths, trainee);
    Log4J.getLogger().info(actual);
    assertEquals("good", actual.getRemark());
    assertEquals(4, actual.getWorkingDuration());
  }
  
  /**
   * case already have 2 milestone
   */
  @Test
  public void caculateCommitmentServiceCase3() {
    List<LearningPath> learningPaths = new ArrayList<>();
    LearningPath learningPath = new LearningPath();
    learningPath.setId(1);
    learningPath.setMilestoneName("Thg04-20");
    learningPath.setMaxScore(10.0);
    learningPath.setPassScore(6.0);
    learningPath.setSalaryPaid("yes");
    learningPath.setScore(8.0);
    learningPath.setWeightedNumber(4.0);
    LearningPath learningPath2 = new LearningPath();
    learningPath2.setId(2);
    learningPath2.setMilestoneName("Thg04-20");
    learningPath2.setMaxScore(10.0);
    learningPath2.setPassScore(6.0);
    learningPath2.setSalaryPaid("no");
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
    trainee.setId(3);
    
    CommitmentServiceImpl serviceImpl = new CommitmentServiceImpl();
    Commitment actual = serviceImpl.caculateCommitment(learningPaths, trainee);
    Log4J.getLogger().info(actual);
    assertEquals(6, actual.getWorkingDuration());
  }
  
  /**
   * case already have 0 milestone
   */
  @Test
  public void caculateCommitmentServiceCase4() {
    List<LearningPath> learningPaths = new ArrayList<>();
    
    Trainee trainee = new Trainee();
    trainee.setId(3);
    
    CommitmentServiceImpl serviceImpl = new CommitmentServiceImpl();
    Commitment actual = serviceImpl.caculateCommitment(learningPaths, trainee);
    assertNull(actual);
  
  }
}
