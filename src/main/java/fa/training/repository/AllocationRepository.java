package fa.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import fa.training.entity.Allocation;

@Repository
public interface AllocationRepository
    extends JpaRepository<Allocation, Integer> {
  public Allocation findByTraineeId(int traineeId);
  public void deleteByTraineeId(int traineeId);
}
