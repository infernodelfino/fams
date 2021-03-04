package fa.training.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import fa.training.entity.vo.DataVo;
import fa.training.service.CandidateService;
import fa.training.service.ClassesService;
import fa.training.service.TraineeVoService;
import fa.training.utils.Log4J;
import fa.training.utils.Pages;

/**
 * The controller groups together all methods related to Landing page.
 * 
 * @author ThanhDT19
 *
 */
@Controller
public class DashboardController {
  @Autowired
  MessageSource messageSource;

  @Autowired
  ClassesService classesService;

  @Autowired
  TraineeVoService traineeVoService;

  @Autowired
  CandidateService candidateService;

  /**
   * This method to response landing page when login success.
   * 
   * @RequestMapping define url request to response landing page
   * 
   * @param modelMap  ModelMap is also used to pass values to render a view
   * @param principal The principal is the currently logged in user
   * @return landing page and data from class
   */
  @RequestMapping("/landing-page")
  public String loadLadingPage(ModelMap modelMap, Principal principal) {
    if (principal == null) {
      return Pages.LOGIN;
    }
    User userLogin = (User) ((Authentication) principal).getPrincipal();
    Collection<GrantedAuthority> authorities = userLogin.getAuthorities();
    List<String> roles = authorities.stream()
        .map(author -> author.getAuthority()).collect(Collectors.toList());
    List<DataVo> dataVos = getClasses("all", "all", "table");
    List<String> locations = getLocations("class");
    Set<String> statuses = dataVos.stream().map(x -> x.getStatus())
        .collect(Collectors.toSet());
    modelMap.addAttribute("classesVo", dataVos);
    modelMap.addAttribute("statuses", statuses);
    modelMap.addAttribute("locations", locations);
    modelMap.addAttribute("roles", roles);
    return Pages.LANDING_PAGE;
  }

  /**
   * This method to response landing page when click dashboard.
   * 
   * @RequestMapping define url request to response landing page
   * @return landing page and data from class
   */
  @RequestMapping("/landing-page/ajax")
  public String loadLandingPageAjax(ModelMap modelMap) {
    List<DataVo> dataVos = getClasses("all", "all", "table");
    Set<String> statuses = dataVos.stream().map(x -> x.getStatus())
        .collect(Collectors.toSet());
    List<String> locations = getLocations("class");
    modelMap.addAttribute("classesVo", dataVos);
    modelMap.addAttribute("statuses", statuses);
    modelMap.addAttribute("locations", locations);
    return Pages.LANDING_PAGE_AJAX;
  }

  /**
   * This method to response landing page with results as a table when click
   * dropdown list dashboadfor, location, status, type of dashboardfor.
   * 
   * @RequestMapping define url request to response dashboard content as a table
   * @param modelMap     ModelMap is also used to pass values to render a view
   * @param status       get status from url request
   * @param location     get location from url request
   * @param dashboardFor get dashboardFor from url request
   * @return result table by status, location, dashboardfor
   * @throws Exception declare exception
   */
  @RequestMapping("/landing-page/content-table/{dashboardFor}")
  public String loadLandingPageTableContent(ModelMap modelMap,
      @RequestParam(name = "status") String status,
      @RequestParam(name = "location") String location,
      @PathVariable(name = "dashboardFor", required = false) 
      String dashboardFor)
      throws BadRequest, Exception {
    String type = "table";
    List<DataVo> dataVos = getData(dashboardFor, location, status, type);
    List<String> locations = getLocations(dashboardFor);
    Set<String> statuses = dataVos.stream().map(x -> x.getStatus())
        .collect(Collectors.toSet());
    modelMap.addAttribute("statuses", statuses);
    modelMap.addAttribute("locations", locations);
    modelMap.addAttribute("dataVo", dataVos);
    Log4J.getLogger().info("dataVos: " + dataVos);
    return Pages.DASHBOARD_CONTENT_TABLE;
  }

  /**
   * This method to return data Json for page with results as a chart when click
   * dropdown list dashboadfor, location, status, type of dashboardfor.
   * 
   * @RequestMapping define url request to data to display result as chart
   * @param modelMap     ModelMap is also used to pass values to render a view
   * @param status       get status from url request
   * @param location     get location from url request
   * @param dashboardFor get dashboardFor from url request
   * @return result table by status, location, dashboardfor
   * @throws Exception declare exception
   */
  @RequestMapping("/landing-page/content-chart/{dashboardFor}")
  @ResponseBody
  public Object[] loadLandingPageChartContent(ModelMap modelMap,
      @RequestParam(name = "status") String status,
      @RequestParam(name = "location") String location,
      @PathVariable(name = "dashboardFor", required = false) 
      String dashboardFor)
      throws BadRequest, Exception {
    String type = "chart";
    List<DataVo> dataVos = getData(dashboardFor, location, status, type);
    Set<String> statuses = dataVos.stream().map(x -> x.getStatus())
        .collect(Collectors.toSet());
    Object[] data = { statuses, dataVos };
    Log4J.getLogger().info(data[0]);
    Log4J.getLogger().info(data[1]);
    return data;
  }

  /**
   * This method to get data by dashboardFor, location, status.
   * 
   * @param status   get dashboardFor from url request
   * @param location get location from url request
   * @return list data
   * @throws Exception an exception
   */
  public List<DataVo> getData(String dashboardFor, String location,
      String status, String type) {
    List<DataVo> dataVos = new ArrayList<DataVo>();
    if ("class".equalsIgnoreCase(dashboardFor)) {
      dataVos = getClasses(location, status, type);
    } else if ("trainee".equalsIgnoreCase(dashboardFor)) {
      dataVos = getTrainees(location, status, type);
    } else if ("candidate".equalsIgnoreCase(dashboardFor)) {
      dataVos = getCandidates(location, status, type);
    }
    return dataVos;
  }

  /**
   * This method to get data from class table.
   * 
   * @param status   get status from url request
   * @param location get location from url request
   * @return list class
   * @throws Exception an exception
   */
  public List<DataVo> getClasses(String location, String status, String type) {
    List<DataVo> dataVos = new ArrayList<DataVo>();
    if (location == null || status == null) {
      return dataVos;
    }
    if ("all".equalsIgnoreCase(location) && "all".equalsIgnoreCase(status)) {
      dataVos = classesService.countClassesGroupBySkill(type);
    } else if ("all".equalsIgnoreCase(location)
        && "all".equalsIgnoreCase(status) == false) {
      dataVos = classesService.countClassesGroupBySkillByStatus(status, type);
    } else if ("all".equalsIgnoreCase(location) == false
        && "all".equalsIgnoreCase(status)) {
      dataVos = classesService.countClassesGroupBySkillByLocation(location,
          type);
    } else {
      dataVos = classesService
          .countClassesGroupBySkillByStatusAndLocation(status, location, type);
    }
    return dataVos;
  }

  /**
   * This method to get data from trainee table.
   * 
   * @param status   get status from url request
   * @param location get location from url request
   * @return list trainee
   * @throws Exception an exception
   */
  public List<DataVo> getTrainees(String location, String status, String type) {
    List<DataVo> dataVos = new ArrayList<DataVo>();
    if (location != null || status != null) {
      if ("all".equalsIgnoreCase(location) && "all".equalsIgnoreCase(status)) {
        dataVos = traineeVoService.countTraineesGroupBySkill(type);
      } else if ("all".equalsIgnoreCase(location)
          && "all".equalsIgnoreCase(status) == false) {
        dataVos = traineeVoService.countTraineesGroupBySkillByStatus(status,
            type);
      } else if ("all".equalsIgnoreCase(location) == false
          && "all".equalsIgnoreCase(status)) {
        dataVos = traineeVoService.countTraineesGroupBySkillByLocation(location,
            type);
      } else {
        dataVos = traineeVoService.countTraineesGroupBySkillByStatusAndLocation(
            status, location, type);
      }
    }
    return dataVos;
  }

  /**
   * This method to get data from candidate table.
   * 
   * @param status   get status from url request
   * @param location get location from url request
   * @return list candidate
   * @throws Exception an exception
   */
  public List<DataVo> getCandidates(String location, String status,
      String type) {
    List<DataVo> dataVos = new ArrayList<DataVo>();
    if (location != null || status != null) {
      if ("all".equalsIgnoreCase(location) && "all".equalsIgnoreCase(status)) {
        dataVos = candidateService.countCandidateGroupBySkill(type);
      } else if ("all".equalsIgnoreCase(location)
          && "all".equalsIgnoreCase(status) == false) {
        dataVos = candidateService.countCandidateGroupBySkillByStatus(status,
            type);
      } else if ("all".equalsIgnoreCase(location) == false
          && "all".equalsIgnoreCase(status)) {
        dataVos = candidateService
            .countCandidateGroupBySkillByLocation(location, type);
      } else {
        dataVos = candidateService
        .countCandidateGroupBySkillByStatusAndLocation(status, location, type);
      }
    }
    return dataVos;
  }

  /**
   * This method get location by dashboardFor.
   * 
   * @param dashboardFor is parameter from request
   */
  public List<String> getLocations(String dashboardFor) {
    List<String> locations = null;
    if ("class".equalsIgnoreCase(dashboardFor)) {
      locations = classesService.getLocations();
    } else if ("trainee".equalsIgnoreCase(dashboardFor)) {
      locations = traineeVoService.getLocations();
    } else {
      locations = candidateService.getLocations();
    }
    return locations;
  }
}
