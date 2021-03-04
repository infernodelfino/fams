package fa.training.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fa.training.entity.Faculty;
import fa.training.entity.Trainee;
import fa.training.entity.University;
import fa.training.entity.vo.TraineeProfileVo;
import fa.training.service.impl.FacultyServiceImpl;
import fa.training.service.impl.TraineeProfileServiceImpl;
import fa.training.service.impl.TraineeResultServiceImpl;
import fa.training.service.impl.UniversityServiceImpl;
import fa.training.utils.Message;

/**
 * 
 * @author LinhNT70.
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TraineeProfileControllerTest {

  @Mock
  private TraineeProfileServiceImpl traineeProfileService;

  @Mock
  private TraineeResultServiceImpl traineeResultService;

  @Mock
  private UniversityServiceImpl universityService;

  @Mock
  private FacultyServiceImpl facultyService;

  private MockMvc mockMvc;

  @InjectMocks
  private TraineeProfileController traineeProfileController;

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
  }

  @Before
  public void setUp() throws Exception {
    mockMvc = MockMvcBuilders.standaloneSetup(traineeProfileController).build();
  }

  /**
   * chuyển RequestBody các loại sang String để test.
   * 
   * @param obj any object.
   * @return String.
   * @throws JsonProcessingException throw
   */
  protected String maptoJson(Object obj) throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.writeValueAsString(obj);
  }

  /**
   * getTrainee() xử lý cho GetMapping, có RequestParam.
   * 
   * @throws Exception throws.
   */
  @Test
  public void testGetTrainee() throws Exception {
    int id = 1;
    Trainee trainee = new Trainee(null, null, null, null, null, null,
        new University(1, "FPT"), new Faculty(1, "CNTT"), null,
        "Nguyen Tung Linh", "LinhNT", "Trainee", "Waiting for allocation", true,
        LocalDate.of(1992, 12, 1), "0913304304", "linhnt@gmail.com", "11223300",
        null, "Dev-N", null, true);
    trainee.setId(1);

    when(traineeProfileService.findTrainee(id)).thenReturn(trainee);

    // xử lý cho GetMapping, có RequestParam.
    this.mockMvc
        .perform(MockMvcRequestBuilders
            .get("/trainee-management/view/trainee-profile-ajax")
            .param("empID", id + ""))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(model().attribute("trainee", trainee))
        .andExpect(view().name("trainee-profile-view-ajax"));
  }

  /**
   * backtoProfile().
   * 
   * @throws Exception throws
   */
  @Test
  public void testBackToProfile() throws Exception {
    int id = 1;
    Trainee trainee1 = new Trainee(null, null, null, null, null, null,
        new University(1, "FPT"), new Faculty(1, "CNTT"), null,
        "Nguyen Tung Linh", "LinhNT", "Trainee", "Waiting for allocation", true,
        LocalDate.of(1992, 12, 1), "0913304304", "linhnt@gmail.com", "11223300",
        null, "Dev-N", null, true);
    trainee1.setId(id);

    when(traineeProfileService.findTrainee(id)).thenReturn(trainee1);

    this.mockMvc
        .perform(MockMvcRequestBuilders
            .get("/trainee-management/view/trainee-result-ajax-back-to-profile")
            .sessionAttr("empId", id))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(model().attribute("trainee", trainee1))
        .andExpect(view().name("trainee-profile-view-ajax"));
  }

  /**
   * deleteTrainee() xử lý RequestBody, method Post.
   * 
   * @throws Exception throw.
   */
  @Test
  public void testDeleteTrainee() throws Exception {
    Trainee trainee1 = new Trainee(null, null, null, null, null, null,
        new University(1, "FPT"), new Faculty(1, "CNTT"), null,
        "Nguyen Tung Linh", "LinhNT", "Trainee", "Waiting for allocation", true,
        LocalDate.of(1992, 12, 1), "0913304304", "linhnt@gmail.com", "11223300",
        null, "Dev-N", null, true);
    trainee1.setId(1);

    Trainee trainee2 = new Trainee(null, null, null, null, null, null,
        new University(2, "FPT"), new Faculty(1, "CNTT"), null,
        "Nguyen Quang Anh", "AnhNQ", "Trainee", "Waiting for allocation", true,
        LocalDate.of(1992, 5, 25), "0913304305", "anhnq@gmail.com", "11223300",
        null, "Dev-N", null, true);
    trainee2.setId(2);

    Trainee trainee3 = new Trainee(null, null, null, null, null, null,
        new University(1, "FPT"), new Faculty(2, "DTVT"), null,
        "Nguyen Tuan Anh", "AnhNT", "Trainee", "Waiting for allocation", true,
        LocalDate.of(1992, 3, 13), "0913304306", "anhnt@gmail.com", "11223300",
        null, "Dev-N", null, true);
    trainee3.setId(3);

    List<Trainee> traineeList = new ArrayList<>();
    traineeList.add(trainee1);
    traineeList.add(trainee2);
    traineeList.add(trainee3);

    List<Integer> idList = new ArrayList<>();
    idList.add(trainee1.getId());
    idList.add(trainee2.getId());

    List<Trainee> traineeListAfterDelete = new ArrayList<>();
    traineeListAfterDelete.add(trainee3);

    for (Trainee x : traineeList) {
      when(traineeProfileService.deleteTrainee(x.getId(), false))
          .thenReturn(true);
    }

    when(traineeResultService.findActiveTrainee(true))
        .thenReturn(traineeListAfterDelete);

    String idListJsonType = maptoJson(idList);

    // xử lý RequestBody, method Post.
    this.mockMvc
        .perform(
            MockMvcRequestBuilders.post("/trainee-management/delete/trainee")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(idListJsonType))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(model().attributeExists("traineeList"))
        .andExpect(view().name("trainee-management-ajax"));
  }
  
  /**
   * deleteTrainee() xử lý RequestBody, method Post.
   * 
   * @throws Exception throw.
   */
  @Test
  public void testDeleteTrainee2() throws Exception {
    Trainee trainee1 = new Trainee(null, null, null, null, null, null,
        new University(1, "FPT"), new Faculty(1, "CNTT"), null,
        "Nguyen Tung Linh", "LinhNT", "Trainee", "Waiting for allocation", true,
        LocalDate.of(1992, 12, 1), "0913304304", "linhnt@gmail.com", "11223300",
        null, "Dev-N", null, true);
    trainee1.setId(1);

    Trainee trainee2 = new Trainee(null, null, null, null, null, null,
        new University(2, "FPT"), new Faculty(1, "CNTT"), null,
        "Nguyen Quang Anh", "AnhNQ", "Trainee", "Waiting for allocation", true,
        LocalDate.of(1992, 5, 25), "0913304305", "anhnq@gmail.com", "11223300",
        null, "Dev-N", null, true);
    trainee2.setId(2);

    Trainee trainee3 = new Trainee(null, null, null, null, null, null,
        new University(1, "FPT"), new Faculty(2, "DTVT"), null,
        "Nguyen Tuan Anh", "AnhNT", "Trainee", "Waiting for allocation", true,
        LocalDate.of(1992, 3, 13), "0913304306", "anhnt@gmail.com", "11223300",
        null, "Dev-N", null, true);
    trainee3.setId(3);

    List<Trainee> traineeList = new ArrayList<>();
    traineeList.add(trainee1);
    traineeList.add(trainee2);
    traineeList.add(trainee3);

    List<Integer> idList = new ArrayList<>();
    idList.add(trainee1.getId());

    List<Trainee> traineeListAfterDelete = new ArrayList<>();
    traineeListAfterDelete.add(trainee2);
    traineeListAfterDelete.add(trainee3);

    for (Trainee x : traineeList) {
      when(traineeProfileService.deleteTrainee(x.getId(), false))
          .thenReturn(true);
    }

    when(traineeResultService.findActiveTrainee(true))
        .thenReturn(traineeListAfterDelete);

    String idListJsonType = maptoJson(idList);

    // xử lý RequestBody, method Post.
    this.mockMvc
        .perform(
            MockMvcRequestBuilders.post("/trainee-management/delete/trainee")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(idListJsonType))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(model().attributeExists("traineeList"))
        .andExpect(view().name("trainee-management-ajax"));
  }

  /**
   * toUpdateTrainee().
   * 
   * @throws Exception throws.
   */
  @Test
  public void testToUpdateTrainee() throws Exception {
    int id = 1;
    Trainee trainee1 = new Trainee(null, null, null, null, null, null,
        new University(1, "FPT"), new Faculty(1, "CNTT"), null,
        "Nguyen Tung Linh", "LinhNT", "Trainee", "Waiting for allocation", true,
        LocalDate.of(1992, 12, 1), "0913304304", "linhnt@gmail.com", "11223300",
        null, "Dev-N", null, true);
    trainee1.setId(id);

    University university1 = new University(1, "FPT");
    University university2 = new University(2, "PTIT");
    University university3 = new University(3, "HUST");
    List<University> universityList = new ArrayList<>();
    universityList.add(university1);
    universityList.add(university2);
    universityList.add(university3);

    List<Faculty> facultyList = new ArrayList<>();
    Faculty faculty1 = new Faculty(1, "CNTT");
    Faculty faculty2 = new Faculty(2, "DTVT");
    Faculty faculty3 = new Faculty(3, "DDT");
    facultyList.add(faculty1);
    facultyList.add(faculty2);
    facultyList.add(faculty3);

    when(traineeProfileService.findTrainee(id)).thenReturn(trainee1);

    when(universityService.findAllUniversity()).thenReturn(universityList);

    when(facultyService.findAllFaculty()).thenReturn(facultyList);

    this.mockMvc
        .perform(MockMvcRequestBuilders.get(
            "/trainee-management/update/trainee-profile-ajax-view-to-update")
            .sessionAttr("empId", id))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(model().attribute("trainee", trainee1))
        .andExpect(model().attribute("universityList", universityList))
        .andExpect(model().attribute("facultyList", facultyList))
        .andExpect(view().name("trainee-profile-update-ajax"));

  }

  /**
   * enterUpdateTrainee().
   * 
   * @throws Exception throws an exception.
   */
  @Test
  public void testEnterUpdateTrainee() throws Exception {
    int id = 1;
    Trainee trainee1 = new Trainee(null, null, null, null, null, null,
        new University(1, "FPT"), new Faculty(1, "CNTT"), null,
        "Nguyen Tung Linh", "LinhNT", "Trainee", "Waiting for allocation", true,
        LocalDate.of(1992, 12, 1), "0913304304", "linhnt@gmail.com", "11223300",
        null, "Dev-N", null, true);
    trainee1.setId(id);

    University university1 = new University(1, "FPT");
    University university2 = new University(2, "PTIT");
    University university3 = new University(3, "HUST");
    List<University> universityList = new ArrayList<>();
    universityList.add(university1);
    universityList.add(university2);
    universityList.add(university3);

    List<Faculty> facultyList = new ArrayList<>();
    Faculty faculty1 = new Faculty(1, "CNTT");
    Faculty faculty2 = new Faculty(2, "DTVT");
    Faculty faculty3 = new Faculty(3, "DDT");
    facultyList.add(faculty1);
    facultyList.add(faculty2);
    facultyList.add(faculty3);

    when(traineeProfileService.findTrainee(id)).thenReturn(trainee1);

    when(universityService.findAllUniversity()).thenReturn(universityList);

    when(facultyService.findAllFaculty()).thenReturn(facultyList);

    this.mockMvc
        .perform(MockMvcRequestBuilders
            .post("/trainee-management/update/trainee-profile-ajax")
            .param("id", id + ""))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(model().attribute("trainee", trainee1))
        .andExpect(model().attribute("universityList", universityList))
        .andExpect(model().attribute("facultyList", facultyList))
        .andExpect(view().name("trainee-profile-update-ajax"));
  }

  /**
   * updateTrainee() fail.
   * 
   * @throws Exception
   */
  @Test
  public void testUpdateTrainee() throws Exception {
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

    when(traineeProfileService
        .updateTrainee(ArgumentMatchers.any(TraineeProfileVo.class)))
            .thenReturn(null);

    String jsonInput = maptoJson(traineeVo);

    this.mockMvc.perform(MockMvcRequestBuilders
        .post("/trainee-management/update/trainee-profile-ajax/confirm-update")
        .sessionAttr("empId", id).contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(jsonInput))
        .andExpect(forwardedUrl(
            "/trainee-management/update/trainee-profile-ajax/update-fail"));
  }

  /**
   * updateTrainee() successfully.
   * 
   * @throws Exception throw exception.
   */
  @Test
  public void testUpdateTrainee2() throws Exception {
    int id = 2;
    University university = new University(1, "FPT");
    Faculty faculty = new Faculty(1, "CNTT");

    TraineeProfileVo traineeVo = new TraineeProfileVo(2, "Nguyen Tung Linh", 1,
        "1992-12-01", "FPT", "CNTT", "0913304304", "linhnt@gmail.com", 1,
        "110022", "Dev-N");

    Trainee trainee = new Trainee(null, null, null, null, null, null,
        university, faculty, null, "Nguyen Tung Linh", "LinhNT", "Trainee",
        "Waiting for allocation", true, LocalDate.of(1992, 12, 1), "0913304304",
        "linhnt@gmail.com", "11223300", null, "Dev-N", null, true);
    trainee.setId(id);

    String jsonInput = maptoJson(traineeVo);

    when(traineeProfileService
        .updateTrainee(ArgumentMatchers.any(TraineeProfileVo.class)))
            .thenReturn(trainee);

    this.mockMvc.perform(MockMvcRequestBuilders
        .post("/trainee-management/update/trainee-profile-ajax/confirm-update")
        .sessionAttr("empId", id).contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(jsonInput)).andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(model().attribute("trainee", trainee))
        .andExpect(model().attribute("message", "Update successfully"))
        .andExpect(view().name("trainee-profile-view-ajax"));
  }

  /**
   * updateTraineeFail().
   * 
   * @throws Exception throws an exception.
   */
  @Test
  public void testUpdateTraineeFail() throws Exception {
    int id = 1;
    Trainee trainee1 = new Trainee(null, null, null, null, null, null,
        new University(1, "FPT"), new Faculty(1, "CNTT"), null,
        "Nguyen Tung Linh", "LinhNT", "Trainee", "Waiting for allocation", true,
        LocalDate.of(1992, 12, 1), "0913304304", "linhnt@gmail.com", "11223300",
        null, "Dev-N", null, true);
    trainee1.setId(id);

    University university1 = new University(1, "FPT");
    University university2 = new University(2, "PTIT");
    University university3 = new University(3, "HUST");
    List<University> universityList = new ArrayList<>();
    universityList.add(university1);
    universityList.add(university2);
    universityList.add(university3);

    List<Faculty> facultyList = new ArrayList<>();
    Faculty faculty1 = new Faculty(1, "CNTT");
    Faculty faculty2 = new Faculty(2, "DTVT");
    Faculty faculty3 = new Faculty(3, "DDT");
    facultyList.add(faculty1);
    facultyList.add(faculty2);
    facultyList.add(faculty3);

    when(traineeProfileService.findTrainee(id)).thenReturn(trainee1);

    when(universityService.findAllUniversity()).thenReturn(universityList);

    when(facultyService.findAllFaculty()).thenReturn(facultyList);

    this.mockMvc
        .perform(MockMvcRequestBuilders
            .post("/trainee-management/update/trainee-profile-ajax/update-fail")
            .sessionAttr("empId", id))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(model().attribute("trainee", trainee1))
        .andExpect(model().attribute("message", Message.MSG13))
        .andExpect(model().attribute("universityList", universityList))
        .andExpect(model().attribute("facultyList", facultyList))
        .andExpect(view().name("trainee-profile-update-ajax"));
  }

}
