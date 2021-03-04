package fa.training.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import fa.training.entity.Allocation;
import fa.training.entity.Allowance;
import fa.training.entity.Gpa;
import fa.training.entity.LearningPath;
import fa.training.entity.Trainee;
import fa.training.repository.AllocationRepository;
import fa.training.repository.AllowanceRepository;
import fa.training.service.AllowanceService;
import fa.training.service.impl.AllocationServiceImpl;
import fa.training.service.impl.AllowanceServiceImpl;
import fa.training.utils.Log4J;

@RunWith(SpringRunner.class)
public class AllowanceServiceTest {

  @Mock
  private  AllowanceRepository  allowanceRepository;
  
  @InjectMocks
  private AllowanceServiceImpl allowanceSericeImpl;
  
  @BeforeAll
  static void setUpBeforeClass() throws Exception {
  }

  @AfterAll
  static void tearDownAfterClass() throws Exception {
  }

  @BeforeEach
  void setUp() throws Exception {
  }

  @AfterEach
  void tearDown() throws Exception {
  }
  
  @Test
  public void getAllowanceByTraineeTestCase1() throws Exception {
    List<Allowance> allowances = new ArrayList<>();
    Allowance allowance = new Allowance();
    allowance.setGpa(0.695);
    allowance.setLevel("C");
    allowance.setMilestoneName("Thg04-20");
    allowances.add(allowance);
    when(allowanceRepository.findByTraineeId(3)).thenReturn(allowances);
    List<Allowance> actual = allowanceSericeImpl.getByTraineeId(3);
    assertEquals(allowances.size(), actual.size());
    
  }
  
  @Test(expected = NullPointerException.class)
  public void getAllocationByTraineeTestCase2() throws Exception {
    when(allowanceRepository.findByTraineeId(0))
    .thenThrow(new NullPointerException());
    List<Allowance> actual = allowanceSericeImpl.getByTraineeId(0);
    
  }
  
  @Test
  public void getAllocationByTraineeTestCase3() throws Exception {
    when(allowanceRepository.findByTraineeId(4)).thenReturn(null);
    List<Allowance> actual = allowanceSericeImpl.getByTraineeId(4);
    assertNull(actual);
    
  }
  
  @Test
  public void getAllowanceByGpaCase1() throws Exception {
    
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
    
    
    List<Gpa> gpas = new ArrayList<>();
    Trainee trainee = new Trainee();
    trainee.setId(3);
    Gpa gpa = new Gpa("Thg04-20", 0.85, 0.2, 2.0, 0.855, trainee);
    gpas.add(gpa);
    AllowanceService service = new AllowanceServiceImpl();
    List<Allowance> allowances = service.getAllowanceByGpa(gpas, learningPaths, trainee);
    assertEquals(5000000.0, allowances.get(0).getStandardAllowance());
  }
  
  @Test
  public void getAllowanceByGpaCase2() throws Exception {
    
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
    
    
    List<Gpa> gpas = new ArrayList<>();
    Trainee trainee = new Trainee();
    trainee.setId(3);
    Gpa gpa = new Gpa("Thg04-20", 0.85, 0.2, 2.0, 0.855, trainee);
    gpas.add(gpa);
    
    List<Allowance> traineeAllowance = new ArrayList<>();
    Allowance allowance = new Allowance();
    allowance.setRemarks("Good");
    allowance.setMilestoneName("Thg04-20");
    traineeAllowance.add(allowance);
    
    trainee.setAllowances(traineeAllowance);
    AllowanceService service = new AllowanceServiceImpl();
    
    List<Allowance> allowances = service.getAllowanceByGpa(gpas, learningPaths, trainee);
    
    assertEquals("Good", allowances.get(0).getRemarks());
  }

  @Test
  public void getAllowanceByGpaCase3() throws Exception {
    
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
    
    
    List<Gpa> gpas = new ArrayList<>();
    Trainee trainee = new Trainee();
    trainee.setId(3);
    Gpa gpa = new Gpa("Thg04-20", 0.85, 0.2, 2.0, 0.93, trainee);
    gpas.add(gpa);
    AllowanceService service = new AllowanceServiceImpl();
    List<Allowance> allowances = service.getAllowanceByGpa(gpas, learningPaths, trainee);
    assertEquals("A+", allowances.get(0).getLevel());
  }
  @Test
  public void getAllowanceByGpaCase4() throws Exception {
    
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
    
    
    List<Gpa> gpas = new ArrayList<>();
    Trainee trainee = new Trainee();
    trainee.setId(3);
    Gpa gpa = new Gpa("Thg04-20", 0.85, 0.2, 2.0, 0.86, trainee);
    gpas.add(gpa);
    AllowanceService service = new AllowanceServiceImpl();
    List<Allowance> allowances = service.getAllowanceByGpa(gpas, learningPaths, trainee);
    assertEquals("A", allowances.get(0).getLevel());
  }
}
