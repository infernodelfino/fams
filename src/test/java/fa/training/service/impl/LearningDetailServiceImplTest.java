package fa.training.service.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import fa.training.entity.LearningDetail;
import fa.training.entity.Topic;
import fa.training.exception.DeleteLearnDetailException;
import fa.training.repository.LearningDetailRepository;
import fa.training.repository.LearningPathRepository;
import fa.training.utils.Log4J;

/**
 * .
 * 
 * @author hoangminhduong
 *
 */
@RunWith(SpringRunner.class)
@TestPropertySource("/application.properties")
public class LearningDetailServiceImplTest {
  @InjectMocks
  private LearningDetailServiceImpl learningDetailServiceImpl;
  @Mock
  private LearningDetailRepository learningDetailRepository;
  @Mock
  private LearningPathRepository learningPathRepository;

  /**
   * Test method for getAllLearningDetailNormal().
   */
  @Test
  public void testGetAllLearningDetailNormal() {
    List<LearningDetail> listOfLearningDetail = new ArrayList<LearningDetail>();
    listOfLearningDetail.add(new LearningDetail(1, "JAVA", new Topic(1)));
    listOfLearningDetail.add(new LearningDetail(2, "JAVA", new Topic(2)));
    listOfLearningDetail.add(new LearningDetail(3, "JAVA", new Topic(3)));
    listOfLearningDetail.add(new LearningDetail(12, "JAVA", new Topic(5)));
    listOfLearningDetail.add(new LearningDetail(5, "NET", new Topic(2)));
    listOfLearningDetail.add(new LearningDetail(6, "NET", new Topic(3)));
    listOfLearningDetail.add(new LearningDetail(30, "NET", new Topic(5)));
    when(learningDetailRepository.findAll()).thenReturn(listOfLearningDetail);
    List<LearningDetail> listResult = learningDetailServiceImpl
        .getAllLearningDetail();
    assertTrue(listResult == listOfLearningDetail);
  }

  /**
   * Test method for GetLearningCodeDistinct().
   */
  @Test
  public void testGetLearningCodeDistinctNormal() {
    List<Object[]> listObject = new ArrayList<Object[]>();
    Object[] objectsJava = new Object[]{"JAVA"};
    listObject.add(objectsJava);
    Object[] objectsNet = new Object[]{"NET"};
    listObject.add(objectsNet);
    Object[] objectsFee = new Object[]{"FEE"};
    listObject.add(objectsFee);
    when(learningDetailRepository.getLearningCodeDistinct())
        .thenReturn(listObject);
    List<String> listLearnCode = learningDetailServiceImpl
        .getLearningCodeDistinct();
    Log4J.getLogger().info(listLearnCode);
    assertEquals("JAVA", listLearnCode.get(0));
    assertEquals("NET", listLearnCode.get(1));
    assertEquals("FEE", listLearnCode.get(2));
  }

  @Test
  public void testGetLearningCodeDistinctAbNormal() {
    List<Object[]> listObject = new ArrayList<Object[]>();
    when(learningDetailRepository.getLearningCodeDistinct())
        .thenReturn(listObject);
    List<String> listLearnCode = learningDetailServiceImpl
        .getLearningCodeDistinct();
    assertEquals(0, listLearnCode.size());
  }

  /**
   * Test method for test method SaveLearningDetail() normal.
   */
  @Test
  public void testSaveLearningDetail() {
    LearningDetail detail = new LearningDetail("NET", new Topic(1));
    LearningDetail expected = new LearningDetail(1,"NET", new Topic(1));
    when(learningDetailRepository.save(detail)).thenReturn(expected);
    LearningDetail result = learningDetailServiceImpl
        .saveLearningDetail(detail);
    assertNotNull(result);
  }

  /**
   * test for UC1 of method saveOrDeleteLearnDetail().
   * 
   * @throws DeleteLearnDetailException exception if delete a learningDetail
   *                                    that exists a learingPath with this
   *                                    learningDetail
   */
  @Test(expected = DeleteLearnDetailException.class)
  public void testSaveOrDeleteLearnDetailUC1()
      throws DeleteLearnDetailException {
    List<LearningDetail> leariningDetailInput = new ArrayList<LearningDetail>();
    leariningDetailInput.add(new LearningDetail(1, "JAVA", new Topic(1)));
    leariningDetailInput.add(new LearningDetail(2, "JAVA", new Topic(2)));
    leariningDetailInput.add(new LearningDetail(3, "JAVA", new Topic(3)));
    List<LearningDetail> learingDetailDB = new ArrayList<LearningDetail>();
    learingDetailDB.add(new LearningDetail(1, "JAVA", new Topic(1)));
    learingDetailDB.add(new LearningDetail(2, "JAVA", new Topic(2)));
    learingDetailDB.add(new LearningDetail(3, "JAVA", new Topic(3)));
    learingDetailDB.add(new LearningDetail(4, "JAVA", new Topic(4)));
    when(learningDetailRepository.findByLearnCode("JAVA"))
        .thenReturn(learingDetailDB);
    leariningDetailInput.forEach(x -> {
      when(learningDetailRepository.save(x)).thenReturn(x);
    });
    when(learningPathRepository
        .countByLearningCode(new LearningDetail(4, "JAVA", new Topic(4))))
            .thenReturn(1L);
    learningDetailServiceImpl.saveOrDeleteLearnDetail(leariningDetailInput);
  }

  /**
   * test for UC2 of method saveOrDeleteLearnDetail().
   * 
   * @throws DeleteLearnDetailException exception if delete a learningDetail
   *                                    that exists a learingPath with this
   *                                    learningDetail
   */
  @Test
  public void testSaveOrDeleteLearnDetailUC2()
      throws DeleteLearnDetailException {
    List<LearningDetail> leariningDetailInput = new ArrayList<LearningDetail>();
    leariningDetailInput.add(new LearningDetail(1, "JAVA", new Topic(1)));
    leariningDetailInput.add(new LearningDetail(2, "JAVA", new Topic(2)));
    leariningDetailInput.add(new LearningDetail(3, "JAVA", new Topic(3)));
    List<LearningDetail> learingDetailDB = new ArrayList<LearningDetail>();
    learingDetailDB.add(new LearningDetail(1, "JAVA", new Topic(1)));
    learingDetailDB.add(new LearningDetail(2, "JAVA", new Topic(2)));
    learingDetailDB.add(new LearningDetail(3, "JAVA", new Topic(3)));
    learingDetailDB.add(new LearningDetail(4, "JAVA", new Topic(4)));
    when(learningDetailRepository.findByLearnCode("JAVA"))
        .thenReturn(learingDetailDB);
    leariningDetailInput.forEach(x -> {
      when(learningDetailRepository.save(x)).thenReturn(x);
    });
    when(learningPathRepository
        .countByLearningCode(new LearningDetail(4, "JAVA", new Topic(4))))
            .thenReturn(0L);
    boolean result = learningDetailServiceImpl
        .saveOrDeleteLearnDetail(leariningDetailInput);
    assertTrue(result);
  }

  /**
   * test for UC3 of method saveOrDeleteLearnDetail().
   * 
   * @throws DeleteLearnDetailException exception if delete a learningDetail
   *                                    that exists a learingPath with this
   *                                    learningDetail
   */
  @Test
  public void testSaveOrDeleteLearnDetailUC3()
      throws DeleteLearnDetailException {
    List<LearningDetail> leariningDetailInput = new ArrayList<LearningDetail>();
    leariningDetailInput.add(new LearningDetail(1, "JAVA", new Topic(1)));
    leariningDetailInput.add(new LearningDetail(2, "JAVA", new Topic(2)));
    leariningDetailInput.add(new LearningDetail(3, "JAVA", new Topic(3)));
    leariningDetailInput.add(new LearningDetail(4, "JAVA", new Topic(4)));
    leariningDetailInput.add(new LearningDetail(0, "JAVA", new Topic(5)));
    List<LearningDetail> learingDetailDB = new ArrayList<LearningDetail>();
    learingDetailDB.add(new LearningDetail(1, "JAVA", new Topic(1)));
    learingDetailDB.add(new LearningDetail(2, "JAVA", new Topic(2)));
    learingDetailDB.add(new LearningDetail(3, "JAVA", new Topic(3)));
    learingDetailDB.add(new LearningDetail(4, "JAVA", new Topic(4)));
    when(learningDetailRepository.findByLearnCode("JAVA"))
        .thenReturn(learingDetailDB);
    leariningDetailInput.forEach(x -> {
      when(learningDetailRepository.save(x)).thenReturn(x);
    });
    boolean result = learningDetailServiceImpl
        .saveOrDeleteLearnDetail(leariningDetailInput);
    assertTrue(result);
  }

  /**
   * test for UC4 of method saveOrDeleteLearnDetail().
   * 
   * @throws DeleteLearnDetailException exception if delete a learningDetail
   *                                    that exists a learingPath with this
   *                                    learningDetail
   */
  @Test
  public void testSaveOrDeleteLearnDetailUC4()
      throws DeleteLearnDetailException {
    List<LearningDetail> leariningDetailInput = null;
    boolean result = learningDetailServiceImpl
        .saveOrDeleteLearnDetail(leariningDetailInput);
    assertFalse(result);
  }

}
