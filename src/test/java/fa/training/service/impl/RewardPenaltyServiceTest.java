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

import fa.training.entity.RewardPenalty;
import fa.training.entity.Trainee;
import fa.training.exception.InvalidDateException;
import fa.training.exception.MatchException;
import fa.training.exception.PointOutOfRange;
import fa.training.repository.RewardPenaltyRepository;
import fa.training.service.impl.RewardPenaltyServiceImpl;

@RunWith(SpringRunner.class)
public class RewardPenaltyServiceTest {

  @Mock
  RewardPenaltyRepository rewardPenaltyRepository;
  
  @InjectMocks
  RewardPenaltyServiceImpl rewardPenaltyServiceImpl;
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
  public void testSaveCase1() throws MatchException, InvalidDateException, PointOutOfRange {
       
    List<RewardPenalty> rewardPenalties = new ArrayList<RewardPenalty>();
    Trainee trainee = new Trainee();
    trainee.setId(3);
    RewardPenalty rewardPenalty1 = new RewardPenalty("Thg04-20",LocalDate.of(2020, 4, 1), 2, 0, "", trainee);
    RewardPenalty rewardPenalty2 = new 
        RewardPenalty("Thg05-20", LocalDate.of(2020, 4, 1), 0, 1, "vi pham", trainee);
    rewardPenalties.add(rewardPenalty1);
    rewardPenalties.add(rewardPenalty2);
    when(rewardPenaltyRepository.saveAll(rewardPenalties)).thenReturn(rewardPenalties);
    List<RewardPenalty> actual = rewardPenaltyServiceImpl.saveRewardPenalty(rewardPenalties);
    assertEquals(2, actual.size());
    
  }
  
  @Test(expected = InvalidDateException.class)
  public void testSaveCase2() throws MatchException, InvalidDateException, PointOutOfRange {
    List<RewardPenalty> rewardPenalties = new ArrayList<RewardPenalty>();
    Trainee trainee = new Trainee();
    trainee.setId(3);
    RewardPenalty rewardPenalty1 = new RewardPenalty("Thg04-20",LocalDate.of(2020, 4, 12), 2, 0, "", trainee);
    RewardPenalty rewardPenalty2 = new 
        RewardPenalty("Thg05-20", LocalDate.of(2020, 4, 12), 0, 1, "vi pham", trainee);
    rewardPenalties.add(rewardPenalty1);
    rewardPenalties.add(rewardPenalty2);

    rewardPenaltyServiceImpl.saveRewardPenalty(rewardPenalties);
  }
  
  @Test(expected = PointOutOfRange.class)
  public void testSaveCase3() throws MatchException, InvalidDateException, PointOutOfRange {
    List<RewardPenalty> rewardPenalties = new ArrayList<RewardPenalty>();
    Trainee trainee = new Trainee();
    trainee.setId(3);
    RewardPenalty rewardPenalty1 = new RewardPenalty("Thg04-20",LocalDate.of(2020, 4, 1), 21, 0, "", trainee);
    RewardPenalty rewardPenalty2 = new 
        RewardPenalty("Thg04-20", LocalDate.of(2020, 4, 1), 0, 1, "vi pham", trainee);
    rewardPenalties.add(rewardPenalty1);
    rewardPenalties.add(rewardPenalty2);

    rewardPenaltyServiceImpl.saveRewardPenalty(rewardPenalties);
  }
 
  @Test(expected = PointOutOfRange.class)
  public void testSaveCase5() throws InvalidDateException, PointOutOfRange {
    List<RewardPenalty> rewardPenalties = new ArrayList<RewardPenalty>();
    Trainee trainee = new Trainee();
    trainee.setId(3);
    RewardPenalty rewardPenalty1 = new RewardPenalty("Thg04-20",LocalDate.of(2020, 4, 1), 2, 0, "", trainee);
    RewardPenalty rewardPenalty2 = new 
        RewardPenalty("Thg04-20", LocalDate.of(2020, 4, 1), 0, 21, "vi pham", trainee);
    rewardPenalties.add(rewardPenalty1);
    rewardPenalties.add(rewardPenalty2);

    rewardPenaltyServiceImpl.saveRewardPenalty(rewardPenalties);
  }
}
