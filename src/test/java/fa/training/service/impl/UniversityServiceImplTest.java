package fa.training.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import fa.training.entity.University;
import fa.training.repository.UniversityRepository;

/**
 * 
 * @author LinhNT70.
 *
 */
@RunWith(SpringRunner.class)
public class UniversityServiceImplTest {

  @Mock
  private UniversityRepository universityRepository;

  @InjectMocks
  private UniversityServiceImpl universityService;

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
  }

  @Before
  public void setUp() throws Exception {
  }

  /**
   * find all.
   */
  @Test
  public void testFindAllUniversity() {
    University university1 = new University(1, "FPT");
    University university2 = new University(2, "PTIT");
    University university3 = new University(3, "HUST");
    List<University> universityList = new ArrayList<>();
    universityList.add(university1);
    universityList.add(university2);
    universityList.add(university3);

    when(universityRepository.findAll()).thenReturn(universityList);

    List<University> actual = universityService.findAllUniversity();

    assertEquals(universityList.size(), actual.size());
  }

  /**
   * find all fail.
   */
  @Test(expected = NullPointerException.class)
  public void testFindAllUniversity2() {
    University university1 = new University(1, "FPT");
    University university2 = new University(2, "PTIT");
    University university3 = new University(3, "HUST");
    List<University> universityList = new ArrayList<>();
    universityList.add(university1);
    universityList.add(university2);
    universityList.add(university3);

    when(universityRepository.findAll()).thenReturn(null);

    List<University> actual = universityService.findAllUniversity();

    assertEquals(universityList.size(), actual.size());
  }

  /**
   * find by universityName.
   */
  @Test
  public void testFindUniversityByName() {
    University university1 = new University(1, "FPT");

    when(universityRepository
        .findByUniversityName(university1.getUniversityName()))
            .thenReturn(university1);

    University actual = universityService
        .findUniversityByName(university1.getUniversityName());

    assertEquals(university1.getUniversityName(), actual.getUniversityName());
  }

  /**
   * find by universityName fail => save a new object.
   */
  @Test
  public void testFindUniversityByName2() {
    University university1 = new University(1, "FPT");

    when(universityRepository
        .findByUniversityName(university1.getUniversityName()))
            .thenReturn(null);
    when(universityRepository.save(ArgumentMatchers.any(University.class))).thenReturn(university1);
    
    University actual = universityService
        .findUniversityByName(university1.getUniversityName());

    assertEquals(university1.getUniversityName(), actual.getUniversityName());
  }
}
