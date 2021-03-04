package fa.training.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import fa.training.entity.Faculty;
import fa.training.entity.Trainee;
import fa.training.entity.University;
import fa.training.entity.vo.TraineeProfileVo;
import fa.training.repository.FacultyRepository;
import fa.training.repository.TraineeRepository;
import fa.training.repository.UniversityRepository;
import fa.training.utils.Log4J;

/**
 * 
 * @author LinhNT70.
 *
 */
@RunWith(SpringRunner.class)
public class TraineeProfileServiceImplTest {

  @Mock
  private TraineeRepository traineeRepository;

  @InjectMocks
  private TraineeProfileServiceImpl traineeProfileService;

  @Mock
  private UniversityRepository universityRepository;

  @InjectMocks
  private UniversityServiceImpl universityService;
  
  @Mock
  private UniversityServiceImpl universityServiceMock;

  @Mock
  private FacultyRepository facultyRepository;

  @InjectMocks
  private FacultyServiceImpl facultyService;
  
  @Mock
  private FacultyServiceImpl facultyServiceMock;

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
  }

  @Before
  public void setUp() throws Exception {
  }

  /**
   * test script to find a trainee.
   */
  @Test
  public void testFindTrainee() {
    int id = 1;
    Trainee trainee = new Trainee(null, null, null, null, null, null,
        new University(1, "FPT"), new Faculty(1, "CNTT"), null,
        "Nguyen Tung Linh", "LinhNT", "Trainee", "Waiting for allocation", true,
        LocalDate.of(1992, 12, 1), "0913304304", "11223300", null, null,
        "Dev-N", null, true);
    trainee.setId(1);
    Optional<Trainee> optional = Optional.ofNullable(trainee);

    Log4J.getLogger().info(trainee.toString());
    when(traineeRepository.findById(id)).thenReturn(optional.get());

    Trainee actual = traineeProfileService.findTrainee(id);
    Log4J.getLogger().info(actual.toString());

    assertEquals("Nguyen Tung Linh", actual.getName());
    assertEquals("0913304304", actual.getPhone());

  }

  /**
   * test script to find a trainee but return a null one.
   */
  @Test(expected = NoSuchElementException.class)
  public void testFindTrainee2() {
    int id = 1;
    Trainee trainee = new Trainee(null, null, null, null, null, null,
        new University(1, "FPT"), new Faculty(1, "CNTT"), null,
        "Nguyen Tung Linh", "LinhNT", "Trainee", "Waiting for allocation", true,
        LocalDate.of(1992, 12, 1), "0913304304", "11223300", null, null,
        "Dev-N", null, true);
    trainee.setId(id);
    Optional<Trainee> optional = Optional.empty();
    Log4J.getLogger().info(trainee.toString());
    when(traineeRepository.findById(id)).thenReturn(optional.get());

    Trainee actual = traineeProfileService.findTrainee(id);

    assertEquals("Nguyen Tung Linh", actual.getName());
    assertEquals("0913304304", actual.getPhone());

  }

  /**
<<<<<<< HEAD
   * test script to find to deactivate a trainee.
=======
   * test script to find and deactivate a trainee.
>>>>>>> c0147809b51a4c8e5c5d56f7ff05860c7fc63596
   */
  @Test
  public void testDeleteTrainee() {
    int id = 1;
    Trainee trainee = new Trainee(null, null, null, null, null, null,
        new University(1, "FPT"), new Faculty(1, "CNTT"), null,
        "Nguyen Tung Linh", "LinhNT", "Trainee", "Waiting for allocation", true,
        LocalDate.of(1992, 12, 1), "0913304304", "11223300", null, null,
        "Dev-N", null, true);
    trainee.setId(id);

    Log4J.getLogger().info(trainee.toString());
    Optional<Trainee> optional = Optional.ofNullable(trainee);
    // dùng function của Repo để giả lập get được 1 trainee theo id
    when(traineeRepository.findById(id)).thenReturn(optional.get());
    // giả lập sử dụng Service để deactivate 1 trainee theo id
    boolean actual = traineeProfileService.deleteTrainee(id, false);

    Log4J.getLogger().info("trainee's active status: " + trainee.isActive());
    
    //actual = true => deactivate trainee succesfully.
    Log4J.getLogger().info("actual: " + actual);
    assertTrue(actual);
  }

  /**
   * test script to deactivate a trainee but fail.
   */
  @Test(expected = NoSuchElementException.class)
  public void testDeleteTrainee2() {
    int id = 1;
    Trainee trainee = new Trainee(null, null, null, null, null, null,
        new University(1, "FPT"), new Faculty(1, "CNTT"), null,
        "Nguyen Tung Linh", "LinhNT", "Trainee", "Waiting for allocation", true,
        LocalDate.of(1992, 12, 1), "0913304304", "11223300", null, null,
        "Dev-N", null, true);
    trainee.setId(id);
    Log4J.getLogger().info(trainee.toString());
    Optional<Trainee> optional = Optional.ofNullable(null);

    when(traineeRepository.findById(id)).thenReturn(optional.get());
    boolean actual = traineeProfileService.deleteTrainee(id, false);

    Log4J.getLogger().info("trainee's active status: " + trainee.isActive());
    Log4J.getLogger().info("actual: " + actual);

    assertFalse(actual);
  }

  /**
   * test script to update a trainee.
   */
  @Test
  public void testUpdateTrainee() {
    int id = 1;
    University university = new University(1, "FPT");
    Faculty faculty = new Faculty(1, "CNTT");

    TraineeProfileVo traineeVo = new TraineeProfileVo(1, "Nguyen Tung Lanh", 1,
        "1992-12-01", "FPT", "CNTT", "0913304304", "linhnt@gmail.com", 1,
        "110022", "Dev-N");

    Trainee trainee = new Trainee(null, null, null, null, null, null,
        university, faculty, null, "Nguyen Tung Linh", "LinhNT", "Trainee",
        "Waiting for allocation", true, LocalDate.of(1992, 12, 1), "0913304304",
        "linhnt@gmail.com", "11223300", null, "Dev-N", null, true);
    trainee.setId(id);

    when(universityServiceMock.findUniversityByName(traineeVo.getUniversityName()))
        .thenReturn(university);

    when(facultyServiceMock.findByFacultyName(traineeVo.getFacultyName()))
        .thenReturn(faculty);

    when(traineeRepository.findById(traineeVo.getId())).thenReturn(trainee);

    when(traineeRepository.findByNameAndDateOfBirthAndPhoneAndEmail(
        traineeVo.getName(), LocalDate.of(1992, 12, 1), traineeVo.getPhone(),
        traineeVo.getEmail())).thenReturn(null);

    when(traineeRepository.save(ArgumentMatchers.any(Trainee.class)))
        .thenReturn(trainee);

    Trainee actual = traineeProfileService.updateTrainee(traineeVo);

    actual.setUniversity(university);
    actual.setFaculty(faculty);
    Log4J.getLogger().info("actual: " + actual);

    assertEquals(trainee.getName(), actual.getName());
  }

  /**
   * test script to update a trainee but duplicated information with an existed trainee.
   */
  @Test
  public void testUpdateTrainee2() {
    int id = 1;
    University university = new University(1, "FPT");
    Faculty faculty = new Faculty(1, "CNTT");

    TraineeProfileVo traineeVo = new TraineeProfileVo(1, "Nguyen Tung Lanh", 1,
        "1992-12-01", "FPT", "CNTT", "0913304304", "linhnt@gmail.com", 1,
        "110022", "Dev-N");

    Trainee trainee = new Trainee(null, null, null, null, null, null,
        university, faculty, null, "Nguyen Tung Linh", "LinhNT", "Trainee",
        "Waiting for allocation", true, LocalDate.of(1992, 12, 1), "0913304304",
        "linhnt@gmail.com", "11223300", null, "Dev-N", null, true);
    trainee.setId(id);

    when(universityServiceMock.findUniversityByName(traineeVo.getUniversityName()))
        .thenReturn(university);

    when(facultyServiceMock.findByFacultyName(traineeVo.getFacultyName()))
        .thenReturn(faculty);

    when(traineeRepository.findById(traineeVo.getId())).thenReturn(trainee);

    when(traineeRepository.findByNameAndDateOfBirthAndPhoneAndEmail(
        traineeVo.getName(), LocalDate.of(1992, 12, 1), traineeVo.getPhone(),
        traineeVo.getEmail())).thenReturn(trainee);

    when(traineeRepository.save(ArgumentMatchers.any(Trainee.class)))
        .thenReturn(trainee);

    Trainee actual = traineeProfileService.updateTrainee(traineeVo);

    actual.setUniversity(university);
    actual.setFaculty(faculty);
    Log4J.getLogger().info("actual: " + actual);

    assertEquals(trainee.getName(), actual.getName());
  }
  
  /**
   * found duplicate trainee.
   */
  @Test
  public void testFindDuplicateTrainee() {
    int id = 1;
    Trainee trainee = new Trainee(null, null, null, null, null, null,
        new University(1, "FPT"), new Faculty(1, "CNTT"), null,
        "Nguyen Tung Linh", "LinhNT", "Trainee", "Waiting for allocation", true,
        LocalDate.of(1992, 12, 1), "0913304304", "linhnt@gmail.com", "11223300",
        null, "Dev-N", null, true);

    trainee.setId(id);

    TraineeProfileVo traineeVo = new TraineeProfileVo(1, "Nguyen Tung Linh", 1,
        "1992-12-01", "FPT", "CNTT", "0913304304", "linhnt@gmail.com", 1,
        "110022", "Dev-N");

    when(traineeRepository.findByNameAndDateOfBirthAndPhoneAndEmail(
        traineeVo.getName(), LocalDate.of(1992, 12, 1), traineeVo.getPhone(),
        traineeVo.getEmail())).thenReturn(trainee);

    // Found = false, not found = true
    boolean actual = traineeProfileService.findDuplicateTrainee(
        traineeVo.getName(), LocalDate.of(1992, 12, 1), traineeVo.getPhone(),
        traineeVo.getEmail());

    Log4J.getLogger().info("trainee: " + trainee);

    assertFalse(actual);
  }

  /**
   * duplicate trainee not found.
   */
  @Test
  public void testFindDuplicateTrainee2() {
    int id = 1;
    Trainee trainee = new Trainee(null, null, null, null, null, null,
        new University(1, "FPT"), new Faculty(1, "CNTT"), null,
        "Nguyen Tung Linh", "LinhNT", "Trainee", "Waiting for allocation", true,
        LocalDate.of(1992, 12, 1), "0913304304", "linhnt@gmail.com", "11223300",
        null, "Dev-N", null, true);

    trainee.setId(id);

    TraineeProfileVo traineeVo = new TraineeProfileVo(1, "Nguyen Tung Linh", 1,
        "1992-12-01", "FPT", "CNTT", "0913304304", "linhnt@gmail.com", 1,
        "110022", "Dev-N");

    when(traineeRepository.findByNameAndDateOfBirthAndPhoneAndEmail(
        traineeVo.getName(), LocalDate.of(1992, 12, 1), traineeVo.getPhone(),
        traineeVo.getEmail())).thenReturn(null);

    // Found = false, not found = true
    boolean actual = traineeProfileService.findDuplicateTrainee(
        traineeVo.getName(), LocalDate.of(1992, 12, 1), traineeVo.getPhone(),
        traineeVo.getEmail());

    Log4J.getLogger().info("trainee: " + trainee);

    assertTrue(actual);
  }

  /**
   * not found duplicate trainee.
   */
  @Test
  public void testFindDuplicateTrainee3() {
    int id = 1;
    Trainee trainee = new Trainee(null, null, null, null, null, null,
        new University(1, "FPT"), new Faculty(1, "CNTT"), null,
        "Nguyen Tung Linh", "LinhNT", "Trainee", "Waiting for allocation", true,
        LocalDate.of(1992, 12, 1), "0913304304", "linhnt@gmail.com", "11223300",
        null, "Dev-N", null, true);

    trainee.setId(id);

    TraineeProfileVo traineeVo = new TraineeProfileVo(1, "Nguyen Tung Lanh", 1,
        "1992-12-01", "FPT", "CNTT", "0913304304", "linhnt@gmail.com", 1,
        "110022", "Dev-N");

    when(traineeRepository.findByNameAndDateOfBirthAndPhoneAndEmail(
        traineeVo.getName(), LocalDate.of(1992, 12, 1), traineeVo.getPhone(),
        traineeVo.getEmail())).thenReturn(null);

    // Found = false, not found = true
    boolean actual = traineeProfileService.findDuplicateTrainee(
        traineeVo.getName(), LocalDate.of(1992, 12, 1), traineeVo.getPhone(),
        traineeVo.getEmail());

    Log4J.getLogger().info("trainee: " + trainee);

    assertTrue(actual);
  }
  
  /**
   * not found duplicate trainee.
   */
  @Test
  public void testFindDuplicateTrainee4() {
    int id = 1;
    Trainee trainee = new Trainee(null, null, null, null, null, null,
        new University(1, "FPT"), new Faculty(1, "CNTT"), null,
        "Nguyen Tung Linh", "LinhNT", "Trainee", "Waiting for allocation", true,
        LocalDate.of(1992, 12, 1), "0913304304", "linhnt@gmail.com", "11223300",
        null, "Dev-N", null, true);

    trainee.setId(id);

    TraineeProfileVo traineeVo = new TraineeProfileVo(1, "Nguyen Tung Linh", 1,
        "1992-12-01", "FPT", "CNTT", "0123456789", "linhnt@gmail.com", 1,
        "110022", "Dev-N");

    when(traineeRepository.findByNameAndDateOfBirthAndPhoneAndEmail(
        traineeVo.getName(), LocalDate.of(1992, 12, 1), traineeVo.getPhone(),
        traineeVo.getEmail())).thenReturn(null);

    // Found = false, not found = true
    boolean actual = traineeProfileService.findDuplicateTrainee(
        traineeVo.getName(), LocalDate.of(1992, 12, 1), traineeVo.getPhone(),
        traineeVo.getEmail());

    Log4J.getLogger().info("trainee: " + trainee);

    assertTrue(actual);
  }
  
  /**
   * not found duplicate trainee.
   */
  @Test
  public void testFindDuplicateTrainee5() {
    int id = 1;
    Trainee trainee = new Trainee(null, null, null, null, null, null,
        new University(1, "FPT"), new Faculty(1, "CNTT"), null,
        "Nguyen Tung Linh", "LinhNT", "Trainee", "Waiting for allocation", true,
        LocalDate.of(1992, 12, 1), "0913304304", "linhnt@gmail.com", "11223300",
        null, "Dev-N", null, true);

    trainee.setId(id);

    TraineeProfileVo traineeVo = new TraineeProfileVo(1, "Nguyen Tung Linh", 1,
        "1992-12-01", "FPT", "CNTT", "0913304304", "four@gmail.com", 1,
        "110022", "Dev-N");

    when(traineeRepository.findByNameAndDateOfBirthAndPhoneAndEmail(
        traineeVo.getName(), LocalDate.of(1992, 12, 1), traineeVo.getPhone(),
        traineeVo.getEmail())).thenReturn(null);

    // Found = false, not found = true
    boolean actual = traineeProfileService.findDuplicateTrainee(
        traineeVo.getName(), LocalDate.of(1992, 12, 1), traineeVo.getPhone(),
        traineeVo.getEmail());

    Log4J.getLogger().info("trainee: " + trainee);

    assertTrue(actual);
  }
}
