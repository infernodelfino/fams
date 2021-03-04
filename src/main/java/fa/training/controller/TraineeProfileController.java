package fa.training.controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import fa.training.entity.Faculty;
import fa.training.entity.Trainee;
import fa.training.entity.University;
import fa.training.entity.vo.TraineeProfileVo;
import fa.training.service.FacultyService;
import fa.training.service.LearningPathService;
import fa.training.service.TraineeProfileService;
import fa.training.service.TraineeResultService;
import fa.training.service.UniversityService;
import fa.training.utils.Log4J;
import fa.training.utils.Message;

/**
 * (C) Copyright 2020 Fresher Academy. All Rights Reserved.
 * 
 * @author Minh Tuan, Tung Linh.
 * @date Apr 3, 2020.
 * @version 1.0.
 */
@Controller
public class TraineeProfileController {

  @Autowired
  private TraineeProfileService traineeProfileService;

  @Autowired
  private TraineeResultService traineeResultService;

  @Autowired
  private UniversityService universityService;

  @Autowired
  private FacultyService facultyService;

  @Autowired
  LearningPathService learningPathService;

  /**
   * To show trainee profile in view mode when you clicked on a traineeId.
   * 
   * @param idTrainee is the Id of a trainee.
   * @param map       to pass attribute back to JSP.
   * @param session   to access session.
   * @return to the trainee profile in view mode.
   */
  @GetMapping("/trainee-management/view/trainee-profile-ajax")
  public String getTrainee(@RequestParam("empID") int idTrainee, ModelMap map,
      HttpSession session) {
    session.setAttribute("empId", idTrainee);
    Trainee trainee = traineeProfileService.findTrainee(idTrainee);
    Log4J.getLogger().info(trainee.getName());
    map.addAttribute("trainee", trainee);

    return "trainee-profile-view-ajax";
  }

  /**
   * To go back to trainee profile in view mode.
   * 
   * @param map     to pass attribute back to JSP.
   * @param session to access session.
   * @return to the trainee profile in view mode.
   */
  @GetMapping("/trainee-management/view/"
      + "trainee-result-ajax-back-to-profile")
  public String backToProfile(ModelMap map, HttpSession session) {
    int traineeId = 0;
    if (session.getAttribute("empId") != null) {
      traineeId = (Integer) session.getAttribute("empId");
    }
    Trainee trainee = traineeProfileService.findTrainee(traineeId);
    Log4J.getLogger().info(trainee.getName());
    map.addAttribute("trainee", trainee);

    return "trainee-profile-view-ajax";
  }

  /**
   * to deactivate a trainee or a list of trainee.
   * 
   * @param list  is from the check boxes.
   * @param model to pass the attribute to JSP page.
   * @return to the Trainee Listing page.
   */
  @PostMapping(value = "/trainee-management/delete/trainee")
  public String deleteTrainee(@RequestBody List<Integer> list, Model model) {
    int count = 0;
    for (Integer x : list) {
      if (traineeProfileService.deleteTrainee(x, false)) {
        count++;
      }
    }
    if (list.size() == count) {
      List<Trainee> traineeList = traineeResultService.findActiveTrainee(true);
      model.addAttribute("traineeList", traineeList);
      return "trainee-management-ajax";
    }
    return "/page-403";
  }

  /**
   * to display the trainee's information to update.
   * 
   * @param model   to pass the attribute.
   * @param session to use the session.
   * @return to the trainee-profile-update page.
   */
  @GetMapping(value = "/trainee-management/update"
      + "/trainee-profile-ajax-view-to-update")
  public String toUpdateTrainee(Model model, HttpSession session) {
    int traineeId = 0;
    if (session.getAttribute("empId") != null) {
      traineeId = (Integer) session.getAttribute("empId");
    }
    Trainee trainee = traineeProfileService.findTrainee(traineeId);
    List<University> universityList = universityService.findAllUniversity();
    List<Faculty> facultyList = facultyService.findAllFaculty();

    model.addAttribute("trainee", trainee);
    model.addAttribute("universityList", universityList);
    model.addAttribute("facultyList", facultyList);

    return "trainee-profile-update-ajax";
  }

  /**
   * to display the trainee's information to update.
   * 
   * @param traineeId is from JSP page when you check the check box.
   * @param model     to pass the attribute.
   * @param session   to use the session.
   * @return to the trainee-profile-update page.
   */
  @PostMapping(value = "/trainee-management/update/trainee-profile-ajax")
  public String enterUpdateTrainee(@RequestParam("id") int traineeId,
      Model model, HttpSession session) {

    session.setAttribute("empId", traineeId);
    Trainee trainee = traineeProfileService.findTrainee(traineeId);
    List<University> universityList = universityService.findAllUniversity();
    List<Faculty> facultyList = facultyService.findAllFaculty();

    model.addAttribute("trainee", trainee);
    model.addAttribute("universityList", universityList);
    model.addAttribute("facultyList", facultyList);

    return "trainee-profile-update-ajax";
  }

  /**
   * to receive and update a trainee profile.
   * 
   * @param traineeProfileVo is a class to contain a request body.
   * @param model            to pass the attribute.
   * @param session          to use the session.
   * @return to trainee profile update page.
   * @version 2.0 check null for session attribute.
   */
  @PostMapping("/trainee-management/update/trainee-profile-ajax"
      + "/confirm-update")
  public String updateTrainee(@RequestBody TraineeProfileVo traineeProfileVo,
      Model model, HttpSession session) {
    Trainee trainee = null;
    String message = null;
    if ((session.getAttribute("empId") != null)
        && ((Integer) session.getAttribute("empId") == traineeProfileVo
            .getId())) {
      trainee = traineeProfileService.updateTrainee(traineeProfileVo);
      if (trainee != null) {
        message = Message.MSG22;
      } else {
        return "forward:/trainee-management/update/trainee-profile-ajax"
            + "/update-fail";
      }
    }
    model.addAttribute("trainee", trainee);
    model.addAttribute("message", message);
    return "trainee-profile-view-ajax";

  }

  /**
   * to trainee alert an error in profile update page if the trainee existed
   * already.
   * 
   * @param model   to pass the attribute.
   * @param session to use the session.
   * @return to trainee alert an error in profile update page if the trainee
   *         existed already.
   */
  @PostMapping("/trainee-management/update/trainee-profile-ajax/update-fail")
  public String updateTraineeFail(Model model, HttpSession session) {
    int traineeId = 0;
    if (session.getAttribute("empId") != null) {
      traineeId = (Integer) session.getAttribute("empId");
    }
    Trainee trainee = traineeProfileService.findTrainee(traineeId);
    String message = Message.MSG13;
    List<University> universityList = universityService.findAllUniversity();
    List<Faculty> facultyList = facultyService.findAllFaculty();

    model.addAttribute("trainee", trainee);
    model.addAttribute("message", message);
    model.addAttribute("universityList", universityList);
    model.addAttribute("facultyList", facultyList);

    return "trainee-profile-update-ajax";
  }
}