/**
 * 
 */
package fa.training.controller;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fa.training.entity.Allocation;
import fa.training.entity.Allowance;
import fa.training.entity.AttendantStatus;
import fa.training.entity.Commitment;
import fa.training.entity.Gpa;
import fa.training.entity.LearningDetail;
import fa.training.entity.LearningPath;
import fa.training.entity.RewardPenalty;
import fa.training.entity.Topic;
import fa.training.entity.Trainee;
import fa.training.entity.vo.Milestone;
import fa.training.exception.LearnPathException;
import fa.training.service.AllocationService;
import fa.training.service.AllowanceService;
import fa.training.service.AttendantStatusService;
import fa.training.service.CommitmentService;
import fa.training.service.LearingDetailService;
import fa.training.service.LearningPathService;
import fa.training.service.RewardPenaltyService;
import fa.training.service.TopicService;
import fa.training.service.TraineeResultService;
import fa.training.service.impl.GpaServiceImpl;
import fa.training.utils.Log4J;
import fa.training.utils.Message;

/**
 * @author hoangminhduong
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
//@TestPropertySource("/application.properties")
public class TraineeResultControllerTest {
	@InjectMocks
	private TraineeResultController traineeResultController;
	private MockMvc mockMvc;
	@Mock
	private LearningPathService learningPathService;
	@Mock
	private LearingDetailService learningDetailService;
	@Mock
	private GpaServiceImpl gpaService;
	@Mock
	private AttendantStatusService attendantStatusService;
	@Mock
	private RewardPenaltyService rewardPenaltyService;
	@Mock
	private CommitmentService commitmentService;
	@Mock
	private AllowanceService allowanceService;
	@Mock
	private AllocationService allocationService;
	@Mock
	private TopicService topicService;
	@Mock
	private TraineeResultService traineeResultService;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(traineeResultController).build();
		MockitoAnnotations.initMocks(this);
	}

	protected String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		String resultString = objectMapper.writeValueAsString(obj);
		return resultString;
	}

	protected <T> T mapFromJson(String json, Class<T> clazz) throws JsonMappingException, JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, clazz);
	}

	/**
	 * Test method for method GetTraineeResultViewPage().
	 * 
	 */
	@Test
	public void testGetTraineeResultViewPage() throws Exception {
		int traineeId = 1;
		List<Gpa> listOfGpa = new ArrayList<Gpa>();
		listOfGpa.add(new Gpa(74, "Thg04-20", 0.6, 0.6, 0.0, 0.0, 0.6, new Trainee(1)));
		listOfGpa.add(new Gpa(75, "Thg03-20", 0.5, 0.6, 0.0, 0.0, 0.4, new Trainee(1)));
		when(gpaService.getById(traineeId)).thenReturn(listOfGpa);
		Commitment commitment = new Commitment(46, 20000000, 6, LocalDate.of(2020, 5, 5), LocalDate.of(2020, 11, 5),
				new Trainee(1));
		when(commitmentService.getByTraineeId(traineeId)).thenReturn(commitment);
		List<Allowance> listOfAllowance = new ArrayList<Allowance>();
		listOfAllowance.add(new Allowance(74, 0.6, "C", "Yes", 3000000.0, "", "Thg04-20", new Trainee(1)));
		listOfAllowance.add(new Allowance(75, 0.4, "D", "Yes", 0.0, "", "Thg03-20", new Trainee(1)));
		when(allowanceService.getByTraineeId(traineeId)).thenReturn(listOfAllowance);
		Allocation allocation = new Allocation(1, "FF", 11.0, LocalDate.of(2020, 4, 4), "Not allocated", "remark",
				new Trainee(1));
		when(allocationService.getByTrainee(traineeId)).thenReturn(allocation);
		List<RewardPenalty> listOfPenalty = new ArrayList<RewardPenalty>();
		listOfPenalty
				.add(new RewardPenalty(17, "Thg03-20", LocalDate.of(2020, 3, 30), 1, 1, "lop truong", new Trainee(1)));
		when(rewardPenaltyService.getByTraineeId(traineeId)).thenReturn(listOfPenalty);
		List<AttendantStatus> listOfAttendant = new ArrayList<AttendantStatus>();
		listOfAttendant.add(new AttendantStatus(1, "Thg03-20", 2, 3, 60, 20.0, new Trainee(1)));
		listOfAttendant.add(new AttendantStatus(2, "Thg04-20", 1, 3, 50, 20.0, new Trainee(1)));
		when(attendantStatusService.getAllAttendantStatusByTraineeId(traineeId)).thenReturn(listOfAttendant);
		List<LearningPath> listOfLearningPath = new ArrayList<LearningPath>();
		listOfLearningPath.add(new LearningPath(1, new Trainee(1), "Thg03-20", "Yes", LocalDate.of(2020, 3, 4),
				LocalDate.of(2020, 3, 31), 9.0, 9.0, 5.0, 9.0, new LearningDetail(1)));
		listOfLearningPath.add(new LearningPath(4, new Trainee(1), "Thg04-20", "Yes", LocalDate.of(2020, 4, 1),
				LocalDate.of(2020, 4, 30), 6.0, 6.0, 4.0, 8.0, new LearningDetail(3)));
		when(learningPathService.getAllLearningPathByTraineeId(traineeId)).thenReturn(listOfLearningPath);
		List<Milestone> listOfMilestone = new ArrayList<Milestone>();
		listOfMilestone.add(new Milestone("Thg03-20", "Yes", LocalDate.of(2020, 3, 4), LocalDate.of(2020, 3, 31)));
		listOfMilestone.add(new Milestone("Thg04-20", "Yes", LocalDate.of(2020, 4, 1), LocalDate.of(2020, 4, 30)));
		when(learningPathService.getMilestoneByTraineeId(traineeId)).thenReturn(listOfMilestone);
		this.mockMvc
				.perform(MockMvcRequestBuilders.get("/trainee-management/view/trainee-result-ajax").param("empId", "1"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("listOfAttendaceStatus"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("finalTopicMark"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("listOfLearningPath"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("listOfMilestone"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("listgpa"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("commit"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("listallowance"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("listRewPen"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("allocate"))
				.andExpect(MockMvcResultMatchers.view().name("trainee-result-view-ajax"));
	}

	@Test
	public void testGetTraineeResultViewPageUC2() throws Exception {
		this.mockMvc
				.perform(MockMvcRequestBuilders.get("/trainee-management/view/trainee-result-ajax").param("empId", "0"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("listOfAttendaceStatus"))
				.andExpect(MockMvcResultMatchers.model().attribute("finalTopicMark", "0"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("listOfLearningPath"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("listOfMilestone"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("listgpa"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("listallowance"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("listRewPen"))
				.andExpect(MockMvcResultMatchers.view().name("trainee-result-view-ajax"));
	}

	/**
	 * Test method for getAllLearnDetails().
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetAllLearnDetails() throws Exception {
		int traineeId = 1;
		List<LearningDetail> listOfLearningDetail = new ArrayList<LearningDetail>();
		listOfLearningDetail.add(new LearningDetail(1, "JAVA", new Topic(1, "SQL")));
		listOfLearningDetail.add(new LearningDetail(2, "JAVA", new Topic(2, "HTML")));
		listOfLearningDetail.add(new LearningDetail(3, "JAVA", new Topic(3, "CSS")));
		listOfLearningDetail.add(new LearningDetail(12, "JAVA", new Topic(5, "EL")));
		when(learningPathService.findLearnDetailByTrainee(traineeId)).thenReturn(listOfLearningDetail);
		MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders
				.get("/trainee-management/update" + "/trainee-result-ajax/getAllTopicFromLearnDetail")
				.param("traineeId", "1")).andReturn();
		String resultDataString = result.getResponse().getContentAsString();
		String dataExpected = this.mapToJson(listOfLearningDetail);
		assertEquals(dataExpected, resultDataString);
	}

	/**
	 * Test method for GetAllLearnDetailByLearCode().
	 * 
	 */
	@Test
	public void testGetAllLearnDetailByLearCode() throws Exception {
		List<LearningDetail> listOfLearningDetail = new ArrayList<LearningDetail>();
		listOfLearningDetail.add(new LearningDetail(1, "JAVA", new Topic(1, "SQL")));
		listOfLearningDetail.add(new LearningDetail(2, "JAVA", new Topic(2, "HTML")));
		listOfLearningDetail.add(new LearningDetail(3, "JAVA", new Topic(3, "CSS")));
		listOfLearningDetail.add(new LearningDetail(12, "JAVA", new Topic(5, "EL")));
		when(learningPathService.findLearnDetailByLearCode("JAVA")).thenReturn(listOfLearningDetail);
		MvcResult result = this.mockMvc.perform(
				MockMvcRequestBuilders.get("/trainee-management/update" + "/trainee-result-ajax/getAllTopicByLearCode")
						.param("learCode", "JAVA"))
				.andReturn();
		String resultDataString = result.getResponse().getContentAsString();
		String dataExpected = this.mapToJson(listOfLearningDetail);
		assertEquals(dataExpected, resultDataString);
	}

	/**
	 * Test method for GetTraineeResultUpdatePage().
	 */
	@Test
	public void testGetTraineeResultUpdatePageUC1() throws Exception {
		int traineeId = 1;
		List<Gpa> listOfGpa = new ArrayList<Gpa>();
		listOfGpa.add(new Gpa(74, "Thg04-20", 0.6, 0.6, 0.0, 0.0, 0.6, new Trainee(1)));
		listOfGpa.add(new Gpa(75, "Thg03-20", 0.5, 0.6, 0.0, 0.0, 0.4, new Trainee(1)));
		when(gpaService.getById(traineeId)).thenReturn(listOfGpa);
		Commitment commitment = new Commitment(46, 20000000, 6, LocalDate.of(2020, 5, 5), LocalDate.of(2020, 11, 5),
				new Trainee(1));
		when(commitmentService.getByTraineeId(traineeId)).thenReturn(commitment);
		List<Allowance> listOfAllowance = new ArrayList<Allowance>();
		listOfAllowance.add(new Allowance(74, 0.6, "C", "Yes", 3000000.0, "", "Thg04-20", new Trainee(1)));
		listOfAllowance.add(new Allowance(75, 0.4, "D", "Yes", 0.0, "", "Thg03-20", new Trainee(1)));
		when(allowanceService.getByTraineeId(traineeId)).thenReturn(listOfAllowance);
		Allocation allocation = new Allocation(1, "FF", 11.0, LocalDate.of(2020, 4, 4), "Not allocated", "remark",
				new Trainee(1));
		when(allocationService.getByTrainee(traineeId)).thenReturn(allocation);
		List<RewardPenalty> listOfPenalty = new ArrayList<RewardPenalty>();
		listOfPenalty
				.add(new RewardPenalty(17, "Thg03-20", LocalDate.of(2020, 3, 30), 1, 1, "lop truong", new Trainee(1)));
		when(rewardPenaltyService.getByTraineeId(traineeId)).thenReturn(listOfPenalty);
		List<AttendantStatus> listOfAttendant = new ArrayList<AttendantStatus>();
		listOfAttendant.add(new AttendantStatus(1, "Thg03-20", 2, 3, 60, 20.0, new Trainee(1)));
		listOfAttendant.add(new AttendantStatus(2, "Thg04-20", 1, 3, 50, 20.0, new Trainee(1)));
		when(attendantStatusService.getAllAttendantStatusByTraineeId(traineeId)).thenReturn(listOfAttendant);
		List<LearningPath> listOfLearningPath = new ArrayList<LearningPath>();
		listOfLearningPath.add(new LearningPath(1, new Trainee(1), "Thg03-20", "Yes", LocalDate.of(2020, 3, 4),
				LocalDate.of(2020, 3, 31), 9.0, 9.0, 5.0, 9.0, new LearningDetail(1)));
		listOfLearningPath.add(new LearningPath(4, new Trainee(1), "Thg04-20", "Yes", LocalDate.of(2020, 4, 1),
				LocalDate.of(2020, 4, 30), 6.0, 6.0, 4.0, 8.0, new LearningDetail(3)));
		when(learningPathService.getAllLearningPathByTraineeId(traineeId)).thenReturn(listOfLearningPath);
		List<Milestone> listOfMilestone = new ArrayList<Milestone>();
		listOfMilestone.add(new Milestone("Thg03-20", "Yes", LocalDate.of(2020, 3, 4), LocalDate.of(2020, 3, 31)));
		listOfMilestone.add(new Milestone("Thg04-20", "Yes", LocalDate.of(2020, 4, 1), LocalDate.of(2020, 4, 30)));
		when(learningPathService.getMilestoneByTraineeId(traineeId)).thenReturn(listOfMilestone);
		this.mockMvc
				.perform(MockMvcRequestBuilders.get("/trainee-management/update/trainee-result-ajax")
						.sessionAttr("empId", 1))
				.andExpect(MockMvcResultMatchers.model().attributeExists("listMilestone"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("listLearingPathDetail"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("listOfAttendaceStatus"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("finalTopicMark"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("listOfLearningPath"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("listOfMilestone"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("listOfTopicFromLearningDetail"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("listgpa"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("commit"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("listallowance"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("allocate"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("mileStoneRewPen"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("listRewPen"))
				.andExpect(MockMvcResultMatchers.view().name("trainee-result-update-ajax"));
	}

	/**
	 * Test method for GetTraineeResultUpdatePage().
	 */
	@Test
	public void testGetTraineeResultUpdatePageUC2() throws Exception {
		this.mockMvc
				.perform(MockMvcRequestBuilders.get("/trainee-management/update/trainee-result-ajax")
						.sessionAttr("empId", 0))
				.andExpect(MockMvcResultMatchers.view().name("trainee-result-update-ajax"));
	}

	/**
	 * Test method for SaveOrUpdateTraineeInformation().
	 */
	@Test
	public void testSaveOrUpdateTraineeInformationUC1() throws JsonProcessingException, Exception {
		Trainee trainee = new Trainee(1);
		List<Allowance> listOfAllowance = new ArrayList<Allowance>();
		listOfAllowance.add(new Allowance(74, 0.6, "C", "Yes", 3000000.0, "", "Thg04-20", new Trainee(1)));
		listOfAllowance.add(new Allowance(75, 0.4, "D", "Yes", 0.0, "", "Thg03-20", new Trainee(1)));
		trainee.setAllowances(listOfAllowance);
		List<RewardPenalty> listOfPenalty = new ArrayList<RewardPenalty>();
		listOfPenalty
				.add(new RewardPenalty(17, "Thg03-20", LocalDate.of(2020, 3, 30), 1, 1, "lop truong", new Trainee(1)));
		trainee.setRewardPenalty(listOfPenalty);
		Commitment commitment = new Commitment(46, 20000000, 6, LocalDate.of(2020, 5, 5), LocalDate.of(2020, 11, 5),
				new Trainee(1));
		trainee.setCommitment(commitment);
		List<LearningPath> listOfLearningPath = new ArrayList<LearningPath>();
		listOfLearningPath.add(new LearningPath(1, new Trainee(1), "Thg03-20", "Yes", LocalDate.of(2020, 3, 4),
				LocalDate.of(2020, 3, 31), 9.0, 9.0, 5.0, 9.0, new LearningDetail(1, "JAVA", new Topic(1))));
		listOfLearningPath.add(new LearningPath(4, new Trainee(1), "Thg04-20", "Yes", LocalDate.of(2020, 4, 1),
				LocalDate.of(2020, 4, 30), 6.0, 6.0, 4.0, 8.0, new LearningDetail(3, "JAVA", new Topic(3))));
		trainee.setLearningPaths(listOfLearningPath);
		List<AttendantStatus> listOfAttendant = new ArrayList<AttendantStatus>();
		listOfAttendant.add(new AttendantStatus(1, "Thg03-20", 2, 3, 60, 20.0, new Trainee(1)));
		listOfAttendant.add(new AttendantStatus(2, "Thg04-20", 1, 3, 50, 20.0, new Trainee(1)));
		trainee.setAttendantStatus(listOfAttendant);
		when( learningPathService.saveOrUpdateListLearningPath(1, listOfLearningPath)).thenReturn(true);
		when( attendantStatusService.saveOrUpdate(1, listOfAttendant)).thenReturn(true);
		when(traineeResultService.saveTraineeResult(trainee)).thenReturn(Message.MSG22);
		String jsonInput = this.mapToJson(trainee);
		Log4J.getLogger().info("trainee: " + jsonInput);
		this.mockMvc
				.perform(MockMvcRequestBuilders
						.post("/trainee-management/update/trainee-result-ajax/saveOrUpdate-trainee")
						.contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonInput))
				.andExpect(MockMvcResultMatchers.redirectedUrl("/trainee-management/view/trainee-result-ajax"));
	}

	/**
	 * Test method for SaveOrUpdateTraineeInformation().
	 */
	@Test(expected = Exception.class)
	public void testSaveOrUpdateTraineeInformationUC2() throws JsonProcessingException, Exception {
		Trainee trainee = new Trainee(1);
		List<LearningPath> listOfLearningPath = new ArrayList<LearningPath>();
		listOfLearningPath.add(new LearningPath(1, new Trainee(1), null, "Yes", null,
				LocalDate.of(2020, 3, 31), 6.0, 8.0, 9.0, 9.0, new LearningDetail(1, "JAVA", new Topic(1))));
		trainee.setLearningPaths(listOfLearningPath);
		String jsonInput = this.mapToJson(trainee);
		Log4J.getLogger().info("trainee: " + jsonInput);
		doThrow(LearnPathException.class).when(learningPathService).saveOrUpdateListLearningPath(1, listOfLearningPath);
		this.mockMvc.perform(
				MockMvcRequestBuilders.post("/trainee-management/update/trainee-result-ajax/saveOrUpdate-trainee")
						.contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonInput));
	}

	/**
	 * Test method for GetTopicOfLearningDetail().
	 */
	@Test
	public void testGetTopicOfLearningDetailUC1() throws Exception {
		this.mockMvc
				.perform(MockMvcRequestBuilders
						.post("/trainee-management/update/" + "trainee-result-ajax/getTopicOfLearningDetail")
						.param("learnCode", "JAVA"))
				.andExpect(MockMvcResultMatchers.model().attribute("learnCode", "JAVA"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("listOfLearningDetailCurrent"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("listOfTopic"))
				.andExpect(MockMvcResultMatchers.view().name("modal-add-learning-detail"));
	}

	/**
	 * Test method for GetTopicOfLearningDetail().
	 */
	@Test
	public void testGetTopicOfLearningDetailUC2() throws Exception {
		this.mockMvc
				.perform(MockMvcRequestBuilders
						.post("/trainee-management/update/" + "trainee-result-ajax/getTopicOfLearningDetail")
						.param("learnCode", "ABC"))
				.andExpect(MockMvcResultMatchers.model().attribute("learnCode", "ABC"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("listOfLearningDetailCurrent"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("listOfTopic"))
				.andExpect(MockMvcResultMatchers.view().name("modal-add-learning-detail"));
	}

	/**
	 * Test method for test method saveOrDeteleTopicFromLearnCode().
	 */
	@Test
	public void testSaveOrDeteleTopicFromLearnCode() throws JsonProcessingException, Exception {
		List<LearningDetail> listOfLearnDetail = new ArrayList<LearningDetail>();
		listOfLearnDetail.add(new LearningDetail(1, "JAVA", new Topic(1)));
		listOfLearnDetail.add(new LearningDetail(2, "JAVA", new Topic(2)));
		listOfLearnDetail.add(new LearningDetail(3, "JAVA", new Topic(3)));
		listOfLearnDetail.add(new LearningDetail(12, "JAVA", new Topic(5)));
		when(learningDetailService.saveOrDeleteLearnDetail(listOfLearnDetail)).thenReturn(true);
		assertTrue(traineeResultController.saveOrDeteleTopicFromLearnCode(listOfLearnDetail));
	}

}
