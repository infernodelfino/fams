package fa.training.controller;

import java.text.ParseException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import fa.training.service.impl.MathUtilsServiceImp;

@Controller
public class MathUtilsController {
  @Autowired
  private MathUtilsServiceImp mathUtilService;

  @RequestMapping("/trainee-management/update"
      + "/trainee-result-ajax/modal-attendace-status")
  public String getModalAttendaceStatus(
      @RequestParam("dateCode") String dateCode,
      @RequestParam("startDate") String startdate,
      @RequestParam("endDate") String endDate, Model model)
      throws ParseException {
    List<Integer> days = null;
    if (endDate == "") {
      days = mathUtilService.getDaysByMonth(startdate);
    } else {
      days = mathUtilService.getDays(startdate, endDate);
    }
    model.addAttribute("days", days);
    model.addAttribute("dateCode", dateCode);
    return "modal-attendace-status";
  }
}
