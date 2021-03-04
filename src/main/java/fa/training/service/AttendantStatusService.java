package fa.training.service;

import java.util.List;
import fa.training.entity.AttendantStatus;

public interface AttendantStatusService {
  AttendantStatus getByTrainee(int traineeId);

  List<AttendantStatus> getAllAttendantStatus();

  List<AttendantStatus> getAllAttendantStatusByTraineeId(int traineeId);

  boolean saveOrUpdate(int traineeId,
      List<AttendantStatus> listOfAttendantStatus);

}
