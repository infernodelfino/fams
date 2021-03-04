package fa.training.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fa.training.entity.AttendantStatus;
import fa.training.repository.AttendantStatusRepository;
import fa.training.service.AttendantStatusService;

@Service
public class AttendantStatusServiceImpl implements AttendantStatusService {

  @Autowired
  private AttendantStatusRepository attendanceStatusRepository;

  @Override
  public List<AttendantStatus> getAllAttendantStatus() {
    List<AttendantStatus> listOfAttendantStatus = attendanceStatusRepository
        .findAll();
    return listOfAttendantStatus;
  }

  @Override
  public List<AttendantStatus> getAllAttendantStatusByTraineeId(int traineeId) {
    return attendanceStatusRepository.findAllByTraineeId(traineeId);
  }

  @Override
  public AttendantStatus getByTrainee(int traineeId) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean saveOrUpdate(int traineeId,
      List<AttendantStatus> listOfAttendantStatus) {
    if (listOfAttendantStatus == null || listOfAttendantStatus.size() == 0) {
      return false;
    }
    // xoa attendance status bi xoa tren giao dien
    List<AttendantStatus> listAttendantStatusDatabase = 
        attendanceStatusRepository.findByTraineeId(traineeId);
    listAttendantStatusDatabase.forEach(x -> {
      if (!listOfAttendantStatus.contains(x)) {
        attendanceStatusRepository.delete(x);
      }
    });
    // save or update nhung attendance status tren giao dien
    listOfAttendantStatus.forEach(x -> attendanceStatusRepository.save(x));
    return true;
  }
}
