package fa.training.service.impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

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
import fa.training.repository.AllocationRepository;
import fa.training.service.impl.AllocationServiceImpl;

@RunWith(SpringRunner.class)
public class AllocationServiceTest {

  @Mock
  private AllocationRepository allocationRepository;
  @InjectMocks
  private AllocationServiceImpl allocationServiceImpl;
  
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
  public void getAllocationByTraineeTestCase1() throws Exception {
    Allocation allocation = new Allocation();
    allocation.setId(1);
    allocation.setAllocatedFsu("FA");
    allocation.setAllocationStatus("In processing");
    allocation.setRemarks("Coollll");
    
    when(allocationRepository.findByTraineeId(3)).thenReturn(allocation);
    
    Allocation actual = allocationServiceImpl.getByTrainee(3);
    assertEquals(allocation, actual);
    
  }
  
  @Test(expected = NullPointerException.class)
  public void getAllocationByTraineeTestCase2() throws Exception {
    when(allocationRepository.findByTraineeId(0))
    .thenThrow(new NullPointerException());
    Allocation actual = allocationServiceImpl.getByTrainee(0);  
  }
  
  @Test
  public void getAllocationByTraineeTestCase3() throws Exception {
    when(allocationRepository.findByTraineeId(4)).thenReturn(null);
    Allocation actual = allocationServiceImpl.getByTrainee(4);
    assertNull(actual);
  }
}
