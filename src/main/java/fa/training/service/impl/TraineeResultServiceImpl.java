package fa.training.service.impl;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fa.training.entity.Allocation;
import fa.training.entity.Allowance;
import fa.training.entity.Commitment;
import fa.training.entity.Gpa;
import fa.training.entity.LearningPath;
import fa.training.entity.RewardPenalty;
import fa.training.entity.Trainee;
import fa.training.repository.TraineeRepository;
import fa.training.service.AllocationService;
import fa.training.service.AllowanceService;
import fa.training.service.CommitmentService;
import fa.training.service.GpaService;
import fa.training.service.LearningPathService;
import fa.training.service.RewardPenaltyService;
import fa.training.service.TraineeResultService;
import fa.training.utils.Log4J;
import fa.training.utils.Message;

/**
 * (C) Copyright 2020 Fresher Academy. All Rights Reserved.
 * 
 * @author G3
 * @date Apr 3, 2020
 * @version 1.0
 */
@Service
public class TraineeResultServiceImpl implements TraineeResultService {

	@Autowired
	private TraineeRepository traineeRepository;
	@Autowired
	private LearningPathService learningPathService;
	@Autowired
	private GpaService gpaService;
	@Autowired
	private CommitmentService commitmentService;
	@Autowired
	private AllowanceService allowanceService;
	@Autowired
	private AllocationService allocationService;
	@Autowired
	private RewardPenaltyService rewardPenaltyService;

	@Override
	public List<Trainee> findActiveTrainee(boolean active) {
		List<Trainee> traineeList = traineeRepository.findByActive(active);
		return traineeList;
	}

	/**
	 * Method to save the trainee result.
	 * 
	 * @param trainee save this trainee
	 * @throws Exception if trainee is null
	 */
	@Transactional(rollbackOn = { Exception.class })
	public String saveTraineeResult(Trainee trainee) throws Exception {
		if (trainee.getId() <= 0) {
			throw new Exception("The traineeId is null");
		} else {
			int traineeId = trainee.getId();
			// Get the list of learning path from DB, and group them by name milestone
			List<LearningPath> learningPaths = learningPathService.findByTraineeId(traineeId);
			if(learningPaths.size()==0) {
				rewardPenaltyService.deleteByTraineeId(traineeId);
				allowanceService.deleteByTraineeId(traineeId);
				commitmentService.deleteByTraineeId(traineeId);
				gpaService.deleteByTraineeId(traineeId);
				return "There are no milestone of this Trainee";
			}
			// Save the reward penalty
			List<RewardPenalty> traineeRewPen = trainee.getRewardPenalty();
			rewardPenaltyService.saveRewardPenalty(traineeRewPen);
			// Caculate GPA, Delete of the current trainee and save the new ones
			gpaService.deleteByTraineeId(traineeId);
			List<Gpa> traineeGpas = gpaService.getListByLearningPath(learningPaths, trainee);
			gpaService.saveGpa(traineeGpas);
			// Caculate Allowance, Delete of the current trainee
			// and save the new ones
			List<Allowance> allowanceTrainee = allowanceService.getAllowanceByGpa(traineeGpas, learningPaths, trainee);
			allowanceService.saveAllowance(allowanceTrainee);
			// Caculate Commitment, Delete of the current trainee
			// and save the new ones
			Commitment traineeCommitment = commitmentService.caculateCommitment(learningPaths, trainee);
			commitmentService.saveCommitment(traineeCommitment);
			// Validate Allocation Service
			Allocation allocation = trainee.getAllocation();
			Log4J.getLogger().info(traineeCommitment);
			if (traineeCommitment != null) {
				allocationService.saveAllocation(allocation, traineeCommitment.getStartDate());
			}
			return Message.MSG22;
		}
	}
}