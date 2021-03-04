package fa.training.entity;

import java.util.List;

public class TraineeDataDTO {
  private int traineeId;

  public int getTraineeId() {
    return traineeId;
  }

  public void setTraineeId(int traineeId) {
    this.traineeId = traineeId;
  }

  private List<AttendantStatus> listOfAttendantStatus;
  private List<LearningPath> listOfLearningPath;

  public List<AttendantStatus> getListOfAttendantStatus() {
    return listOfAttendantStatus;
  }

  public void setListOfAttendantStatus(
      List<AttendantStatus> listOfAttendantStatus) {
    this.listOfAttendantStatus = listOfAttendantStatus;
  }

  public List<LearningPath> getListOfLearningPath() {
    return listOfLearningPath;
  }

  public void setListOfLearningPath(List<LearningPath> listOfLearningPath) {
    this.listOfLearningPath = listOfLearningPath;
  }

}
