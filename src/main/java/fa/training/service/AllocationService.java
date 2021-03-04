package fa.training.service;

import java.time.LocalDate;

import fa.training.entity.Allocation;

public interface AllocationService {
	public Allocation getByTrainee(int traineeID) throws Exception;

	public Allocation saveAllocation(Allocation allocationToSave, LocalDate commitStartDate) throws Exception;

	public void deleteByTraineeId(int traineeId);
}
