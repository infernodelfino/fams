package fa.training.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fa.training.entity.Allowance;
import fa.training.entity.Gpa;
import fa.training.entity.LearningPath;
import fa.training.entity.Trainee;
import fa.training.exception.MatchException;
import fa.training.repository.AllowanceRepository;
import fa.training.service.AllowanceService;
import fa.training.utils.Constants;
import fa.training.utils.Log4J;

/**
 * (C) Copyright 2020 Fresher Academy. All Rights Reserved.
 * 
 * @author Minh Tuan
 * @date Apr 3, 2020
 * @version 1.0
 */
@Service
@Transactional
public class AllowanceServiceImpl implements AllowanceService {

	@Autowired
	AllowanceRepository allowanceRepository;

	@Override
	public List<Allowance> getByTraineeId(int traineeID) throws Exception {
		if (traineeID <= 0) {
			Log4J.getLogger().error("The traineeId is null");
			throw new NullPointerException();
		} else {
			return allowanceRepository.findByTraineeId(traineeID);
		}
	}

	/**
	 * 
	 */
	@Override
	public void deleteByTraineeId(int traineeId) {
		allowanceRepository.deleteByTraineeId(traineeId);
	}

	/**
	 * function to save a list of Allowance to database.
	 * 
	 * @param gpasToSave list Allowance to save
	 * @return list Allowance after have been saved
	 */
	@Override
	public List<Allowance> saveAllowance(List<Allowance> allowanceToSave) throws Exception {
		if (allowanceToSave == null) {
			return null;
		} else {
			if (allowanceToSave.size() == 0) {
				return allowanceToSave;
			}
			for (Allowance allowance : allowanceToSave) {
				if (Collections.frequency(allowanceToSave, allowance) > 1) {
					throw new MatchException("Have more than one Allowance with the same milestoneName");
				}
			}
			return allowanceRepository.saveAll(allowanceToSave);
		}
	}

	/**
	 * function to caculate allowance from the list GPA caculated.
	 * 
	 * @param gpas          list GPA
	 * @param learningPaths list Milestone
	 * @param trainee       trainee who want to set the allowance
	 * @throws Exception throw when the parameter is null
	 */
	@Override
	public List<Allowance> getAllowanceByGpa(List<Gpa> gpas, List<LearningPath> learningPaths, Trainee trainee)
			throws Exception {
		if (trainee == null || learningPaths == null || gpas == null) {
			return new ArrayList<>();
		}
		List<Allowance> allowances;
		if (trainee.getAllowances() != null && trainee.getAllowances().size() != 0) {
			allowances = trainee.getAllowances();
			if (gpas.size() == 0) {
				return allowances;
			}
			updateAllowance(allowances, gpas, learningPaths, trainee);
		} else {
			allowances = new ArrayList<>();
			if (gpas.size() == 0) {
				return allowances;
			}
			caculateAllowance(allowances, gpas, learningPaths, trainee);
		}
		allowances.forEach(a -> Log4J.getLogger().info(a));
		return allowances;
	}

	/**
	 * Method update existence allowances
	 * 
	 * @param allowances    list of allowance that get from trainee
	 * @param gpas          list of gpas
	 * @param learningPaths list learning path
	 * @param trainee       trainee who want to set the allowance
	 */
	private void updateAllowance(List<Allowance> allowances, List<Gpa> gpas, List<LearningPath> learningPaths,
			Trainee trainee) {
		gpas.forEach(g -> {
			Allowance allowance = allowances.stream().filter(a -> a.getMilestoneName().equals(g.getMilestoneName()))
					.findAny().orElse(null);

			double allowanceAmount = 0;
			allowanceAmount = caculateAmountAllowance(allowance, g.getGpa());
			allowance.setGpa(g.getGpa());
			allowance
					.setSalaryPaid(learningPaths.stream().filter(l -> l.getMilestoneName().equals(g.getMilestoneName()))
							.findAny().orElse(null).getSalaryPaid());
			allowance.setStandardAllowance(allowanceAmount);
			allowance.setTrainee(trainee);
			allowances.removeIf(a -> (g.getMilestoneName().equals(a.getMilestoneName())));
			allowances.add(allowance);
		});
	}

	/**
	 * Method caculate the new allowances for trainee
	 * 
	 * @param allowances    list of allowance that being create the first time
	 * @param gpas          list of gpas
	 * @param learningPaths list learning path
	 * @param trainee       trainee who want to set the allowance
	 */
	private void caculateAllowance(List<Allowance> allowances, List<Gpa> gpas, List<LearningPath> learningPaths,
			Trainee trainee) {
		gpas.forEach(g -> {
			Allowance allowance = new Allowance();
			double allowanceAmount = 0;
			allowanceAmount = caculateAmountAllowance(allowance, g.getGpa());
			allowance.setGpa(g.getGpa());
			allowance
					.setSalaryPaid(learningPaths.stream().filter(l -> l.getMilestoneName().equals(g.getMilestoneName()))
							.findAny().orElse(null).getSalaryPaid());
			allowance.setStandardAllowance(allowanceAmount);
			allowance.setTrainee(trainee);
			allowance.setMilestoneName(g.getMilestoneName());

			allowances.add(allowance);
		});
	}

	/**
	 * function caculate level for trainee with the iven gpa score and caculate allowance amount
	 * @param allowance allowance 
	 * @param gpa
	 * @return the allowance amout of trainee
	 */
	private double caculateAmountAllowance(Allowance allowance, double gpa) {
		double allowanceRate = 0.0;
		double allowanceAmount = 0;
		if (gpa >= 0.93) {
			allowance.setLevel("A+");
			allowanceRate = 1.2;
		} else if ((gpa < 0.93) && (gpa >= 0.86)) {
			allowance.setLevel("A");
			allowanceRate = 1.1;
		} else if ((gpa < 0.86) && (gpa >= 0.72)) {
			allowance.setLevel("B");
			allowanceRate = 1;
		} else if ((gpa < 0.72) && (gpa >= 0.6)) {
			allowance.setLevel("C");
			allowanceRate = 0.6;
		} else {
			allowance.setLevel("D");
			allowanceRate = 0;
		}
		allowanceAmount = Constants.BASIC_ALLOWANCE * allowanceRate;
		return allowanceAmount;
	}
}
