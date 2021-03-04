package fa.training.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
import fa.training.exception.DeleteLearnDetailException;
import fa.training.exception.LearnPathException;
import fa.training.service.AllocationService;
import fa.training.service.AllowanceService;
import fa.training.service.AttendantStatusService;
import fa.training.service.CommitmentService;
import fa.training.service.GpaService;
import fa.training.service.LearingDetailService;
import fa.training.service.LearningPathService;
import fa.training.service.RewardPenaltyService;
import fa.training.service.TopicService;
import fa.training.service.TraineeProfileService;
import fa.training.service.TraineeResultService;
import fa.training.utils.Log4J;

@Controller
public class TraineeResultController {
  @Autowired
  private TopicService topicService;
  @Autowired
  private LearingDetailService learningDetailService;

  @Autowired
  LearningPathService learningPathService;

  @Autowired
  GpaService gpaService;
  @Autowired
  CommitmentService commitmentService;
  @Autowired
  AllowanceService allowanceService;
  @Autowired
  AllocationService allocationService;
  // tuanpm
  @Autowired
  TraineeProfileService traineeProfileService;
  @Autowired
  RewardPenaltyService rewardPenaltyService;
  @Autowired
  TraineeResultService traineeResult;
  // duonghm
  @Autowired
  private AttendantStatusService attendantStatusService;

  // tuanpm
  /**
   * . This method to show trainee-result-view-ajax
   * 
   * @param traineeID id of trainee will display
   * @param modelMap  transfer data to view
   * @param session   store trainee id of trainee
   * @return 'trainee-result-view-ajax' page show information of trainee
   * @throws Exception an exception
   */
  @GetMapping("/trainee-management/view/trainee-result-ajax")
  public String getTraineeResultViewPage(
      @RequestParam(name = "empId", required = false) Integer traineeID,
      ModelMap modelMap, HttpSession session) throws Exception {
    if (traineeID != null) {
      session.setAttribute("empId", traineeID);
    }
    if (session.getAttribute("empId") != null) {
      traineeID = (int) session.getAttribute("empId");
    }
    // tuanPM
    getTraineeResultViewGpaRewardPenaltyAllocationAllocateTable(traineeID,
        modelMap);
    //// duonghm
    getTraineeResultViewMilestoneAttendantStatusTopicMarkTable(traineeID,
        modelMap);
    return "trainee-result-view-ajax";
  }

  /**
   * . This method to show information of trainee about Milestone,
   * AttendaceStatus, TopicMark table
   * 
   * @param traineeID id of trainee
   * @param modelMap  tranfer data to trainee-result-view-ajax page
   */
  private void getTraineeResultViewMilestoneAttendantStatusTopicMarkTable(
      int traineeID, ModelMap modelMap) {
    List<AttendantStatus> listOfAttendaceStatus = attendantStatusService
        .getAllAttendantStatusByTraineeId(traineeID);
    modelMap.addAttribute("listOfAttendaceStatus", listOfAttendaceStatus);
    List<LearningPath> listOfLearningPath = learningPathService
        .getAllLearningPathByTraineeId(traineeID);
    double finalTopicMark = this.getFinalTopicMark(listOfLearningPath);
    String finalTopicMarkFormat = "0";
    // tinh final topicMark
    if (finalTopicMark != 0) {
      finalTopicMarkFormat = String.format("%.1f", finalTopicMark);
    }
    List<Milestone> listOfMilestone = learningPathService
        .getMilestoneByTraineeId(traineeID);
    modelMap.addAttribute("finalTopicMark", finalTopicMarkFormat);
    modelMap.addAttribute("listOfLearningPath", listOfLearningPath);
    modelMap.addAttribute("listOfMilestone", listOfMilestone);
  }

  /**
   * . This method to show information of trainee about GPA, RewarPenalty,
   * commitment, allowance and allocate table
   * 
   * @param traineeID id of trainee
   * @param modelMap  transfer data to trainee-result-view-ajax page
   * @throws Exception an exception
   */
  private void getTraineeResultViewGpaRewardPenaltyAllocationAllocateTable(
      int traineeID, ModelMap modelMap) throws Exception {
    List<Gpa> gpas = gpaService.getById(traineeID);
    modelMap.addAttribute("listgpa", gpas);
    Commitment commitment = commitmentService.getByTraineeId(traineeID);
    modelMap.addAttribute("commit", commitment);
    List<Allowance> allowances = allowanceService.getByTraineeId(traineeID);
    modelMap.addAttribute("listallowance", allowances);
    Allocation allocation = allocationService.getByTrainee(traineeID);
    List<RewardPenalty> rewardPenalties = rewardPenaltyService
        .getByTraineeId(traineeID);
    modelMap.addAttribute("listRewPen", rewardPenalties);
    Log4J.getLogger().info(gpas.size());
    modelMap.addAttribute("allocate", allocation);
  }

  // duonghm
  /**
   * . This method to get list LearningDetail have learncode like learncode of
   * trainee, from that get all topic of learncode
   * 
   * @param traineeId id of trainee need get learning detail
   * @return list learning detail of trainee
   */
  @GetMapping("/trainee-management/update"
      + "/trainee-result-ajax/getAllTopicFromLearnDetail")
  @ResponseBody
  public List<LearningDetail> getAllLearnDetails(@RequestParam int traineeId) {
    List<LearningDetail> listOfLearningDetail = learningPathService.findLearnDetailByTrainee(traineeId);
    return listOfLearningDetail;
  }

  /**
   * . This method to get learning detail by lean code, from that get all topic
   * name to show on user interface
   * 
   * @param learCode keyword to get learning detail from LearningDetail table
   * @return list LearningDetail have learnCode like input
   */
  @GetMapping("/trainee-management/update"
      + "/trainee-result-ajax/getAllTopicByLearCode")
  @ResponseBody
  public List<LearningDetail> getAllLearnDetailByLearCode(@RequestParam String learCode) {
    List<LearningDetail> listOfLearningDetail = learningPathService.findLearnDetailByLearCode(learCode);
    return listOfLearningDetail;
  }

  /**
   * . This method to get view 'trainee-result-update-ajax'
   * 
   * @param model   uses to data transfer to view 'trainee-result-update-ajax'
   * @param session store variable traineeId of trainee.
   * @return page 'trainee-result-update-ajax' after rendered with data from
   *         model
   * @throws Exception an exception
   */
  @RequestMapping("/trainee-management/update/trainee-result-ajax")
  public String getTraineeResultUpdatePage(Model model, HttpSession session)
      throws Exception {
    // tuanpm22 @GetMapping("/view-update")
    int traineeID = (int) session.getAttribute("empId");
    getTraineeResultUpdateTuanPM22(model, traineeID);
    //// duongHM
    getTraineeResultUpdateDuongHM(model, traineeID);
    return "trainee-result-update-ajax";
  }

  private void getTraineeResultUpdateDuongHM(Model model, int traineeID) {
    List<String> listLearingPathDetail = learningDetailService
        .getLearningCodeDistinct();
    List<LearningPath> learningPaths = learningPathService
        .findByTraineeId(traineeID);
    model.addAttribute("listMilestone", learningPaths);
    model.addAttribute("listLearingPathDetail", listLearingPathDetail);
    List<AttendantStatus> listOfAttendaceStatus = attendantStatusService
        .getAllAttendantStatusByTraineeId(traineeID);
    model.addAttribute("listOfAttendaceStatus", listOfAttendaceStatus);
    List<LearningPath> listOfLearningPath = learningPathService
        .getAllLearningPathByTraineeId(traineeID);
    // tinh final
    double finalTopicMark = this.getFinalTopicMark(listOfLearningPath);
    String finalTopicMarkFormat = "0";
    if (finalTopicMark != 0) {
      finalTopicMarkFormat = String.format("%.1f", finalTopicMark);
    }
    List<Milestone> listOfMilestone = learningPathService
        .getMilestoneByTraineeId(traineeID);
    List<LearningDetail> listOfTopicFromLearningDetail = learningPathService
        .findLearnDetailByTrainee(traineeID);
    model.addAttribute("finalTopicMark", finalTopicMarkFormat);
    model.addAttribute("traineeId", traineeID);
    model.addAttribute("listOfLearningPath", listOfLearningPath);
    model.addAttribute("listOfMilestone", listOfMilestone);
    model.addAttribute("listOfTopicFromLearningDetail",
        listOfTopicFromLearningDetail);
  }

  private void getTraineeResultUpdateTuanPM22(Model model, int traineeID)
      throws Exception {
    List<Gpa> gpas = gpaService.getById(traineeID);
    model.addAttribute("listgpa", gpas);
    Commitment commitment = commitmentService.getByTraineeId(traineeID);
    model.addAttribute("commit", commitment);
    List<Allowance> allowances = allowanceService.getByTraineeId(traineeID);
    model.addAttribute("listallowance", allowances);
    Allocation allocation = allocationService.getByTrainee(traineeID);
    model.addAttribute("allocate", allocation);
    Log4J.getLogger().info(gpas.size());
    // get the list milestone in Reward Penalty
    List<String> rpMileStone = new ArrayList<>();
    model.addAttribute("mileStoneRewPen", rpMileStone);
    List<RewardPenalty> rewardPenalties = rewardPenaltyService
        .getByTraineeId(traineeID);
    for (RewardPenalty rp : rewardPenalties) {
      rpMileStone.add(rp.getMilestoneName());
    }
    model.addAttribute("listRewPen", rewardPenalties);
  }

  /**
   * . This method allow save or update trainee information from user interface
   * 
   * @return redirect to '/trainee-management/view/trainee-result-ajax' to show
   *         new information about trainee
   * @throws LearnPathException when data about trainee invalid
   */
  @PostMapping("/trainee-management/update"
      + "/trainee-result-ajax/saveOrUpdate-trainee")
  public String saveOrUpdateTraineeInformation(@RequestBody Trainee trainee, ModelMap map)
      throws LearnPathException, Exception {
    // a tuan
    int traineeId = trainee.getId();
    /// duonghm
    List<AttendantStatus> listOfAttendantStatus = trainee.getAttendantStatus();
    List<LearningPath> listOfLearningPath = trainee.getLearningPaths();
    learningPathService.saveOrUpdateListLearningPath(
        traineeId, listOfLearningPath);
    attendantStatusService.saveOrUpdate(traineeId, listOfAttendantStatus);
    trainee.setLearningPaths(null);
    trainee.setAttendantStatus(null);
    String resultUpdate = traineeResult.saveTraineeResult(trainee);
    map.addAttribute("result-update", resultUpdate);
    Log4J.getLogger().info(resultUpdate);
    return "redirect:/trainee-management/view/trainee-result-ajax";
  }

  /**
   * . This method to show list topic of learnCode choosed and list topic in
   * Topic table
   * 
   * @param learnCode learn code need customize
   * @param model     transfer data to modal-add-learning-detail page
   * @return modal-add-learning-detail page with data after render
   */
  @PostMapping("/trainee-management/update"
      + "/trainee-result-ajax/getTopicOfLearningDetail")
  public String getTopicOfLearningDetail(@RequestParam String learnCode,
      Model model) {
    List<LearningDetail> listOfLearningDetailCurrent = learningDetailService
        .findByLearnCode(learnCode);
    List<Topic> listOfTopic = topicService.getAllTopics();
    model.addAttribute("learnCode", learnCode);
    model.addAttribute("listOfLearningDetailCurrent", listOfLearningDetailCurrent);
    model.addAttribute("listOfTopic", listOfTopic);
    return "modal-add-learning-detail";
  }

  /**
   * . This method allow user add or delete topic from learnCode
   * 
   * @param listOfLearnDetail list data learning detail get from client
   * @return true if save, update, delete successfully
   * @throws DeleteLearnDetailException exception throw when user choose delete
   *                                    topic that have trainee learn
   */
  @PostMapping("/trainee-management/update"
      + "/trainee-result-ajax/saveOrDeleteLearningDetail")
  @ResponseBody
  public boolean saveOrDeteleTopicFromLearnCode(
      @RequestBody List<LearningDetail> listOfLearnDetail)
      throws DeleteLearnDetailException {
    boolean result = learningDetailService
        .saveOrDeleteLearnDetail(listOfLearnDetail);
    return result;
  }

  /**
   * . This method to calculate final topic mark of all milestone
   * 
   * @param listOfLearningPath list leaning path need calculate final topic mark
   * @return final topic mark
   */
  private double getFinalTopicMark(List<LearningPath> listOfLearningPath) {
    if (listOfLearningPath == null || listOfLearningPath.size() == 0) {
      return 0;
    }
    double scoreWNumber = 0;
    double maxScoreWNumber = 0;
    for (LearningPath learningPath : listOfLearningPath) {
      scoreWNumber = scoreWNumber + learningPath.getScore() * learningPath.getWeightedNumber();
      maxScoreWNumber = maxScoreWNumber + learningPath.getMaxScore() * learningPath.getWeightedNumber();
    }
    if (maxScoreWNumber == 0) {
      return 0;
    }
    double finalTopicMark = scoreWNumber / maxScoreWNumber * 100;
    return finalTopicMark;
  }
}