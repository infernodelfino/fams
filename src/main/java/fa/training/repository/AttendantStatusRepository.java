package fa.training.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import fa.training.entity.AttendantStatus;

@Repository
public interface AttendantStatusRepository
    extends JpaRepository<AttendantStatus, Integer> {
  public List<AttendantStatus> findByTraineeId(int traineeId);

  @Query(value = "Select * from attendant_status e where e.trainee_id=?1", 
          nativeQuery = true)
  List<AttendantStatus> findAllByTraineeId(int traineeId);

  @Transactional
  @Modifying
  @Query(value = "Delete dbo.attendant_status where trainee_id =?1", 
      nativeQuery = true)
  int deleteByTraineeId(int traineeId);
}
