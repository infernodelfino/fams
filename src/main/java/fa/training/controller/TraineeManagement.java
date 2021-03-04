package fa.training.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import fa.training.entity.Trainee;
import fa.training.service.TraineeResultService;

@Controller
public class TraineeManagement {
	@Autowired
	private TraineeResultService traineeResultService;

	@RequestMapping("/trainee-management/trainee-listing")
	public String getTraineeListing(Model model) {
		List<Trainee> traineeList = traineeResultService.findActiveTrainee(true);
		traineeList = traineeList.stream().sorted(
				(o1, o2) -> o1.getAccount().compareToIgnoreCase(o2.getAccount())).collect(Collectors.toList());
			
		model.addAttribute("traineeList", traineeList);
		return "trainee-management-ajax";
	}

}
