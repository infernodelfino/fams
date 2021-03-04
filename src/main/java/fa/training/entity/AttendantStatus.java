package fa.training.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class AttendantStatus {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String milestoneName;
  private int absentTime;
  private int lateOrEarlyLeave;
  private int noPermissionsRate;
  private Double disciplinaryPoint;

  @ManyToOne
  @JoinColumn(name = "trainee_id")
  private Trainee trainee;

  public AttendantStatus() {
    super();
  }

  public AttendantStatus(String milestoneName, int absentTime,
      int lateOrEarlyLeave, int noPermissionsRate, Double disciplinaryPoint,
      Trainee trainee) {
    super();
    this.milestoneName = milestoneName;
    this.absentTime = absentTime;
    this.lateOrEarlyLeave = lateOrEarlyLeave;
    this.noPermissionsRate = noPermissionsRate;
    this.disciplinaryPoint = disciplinaryPoint;
    this.trainee = trainee;
  }
  
  public AttendantStatus(int id, String milestoneName, int absentTime,
      int lateOrEarlyLeave, int noPermissionsRate, Double disciplinaryPoint,
      Trainee trainee) {
    super();
    this.id = id;
    this.milestoneName = milestoneName;
    this.absentTime = absentTime;
    this.lateOrEarlyLeave = lateOrEarlyLeave;
    this.noPermissionsRate = noPermissionsRate;
    this.disciplinaryPoint = disciplinaryPoint;
    this.trainee = trainee;
  }


  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getMilestoneName() {
    return milestoneName;
  }

  public void setMilestoneName(String milestoneName) {
    this.milestoneName = milestoneName;
  }

  public int getAbsentTime() {
    return absentTime;
  }

  public void setAbsentTime(int absentTime) {
    this.absentTime = absentTime;
  }

  public int getLateOrEarlyLeave() {
    return lateOrEarlyLeave;
  }

  public void setLateOrEarlyLeave(int lateOrEarlyLeave) {
    this.lateOrEarlyLeave = lateOrEarlyLeave;
  }

  public int getNoPermissionsRate() {
    return noPermissionsRate;
  }

  public void setNoPermissionsRate(int noPermissionsRate) {
    this.noPermissionsRate = noPermissionsRate;
  }

  public Double getDisciplinaryPoint() {
    return disciplinaryPoint;
  }

  public void setDisciplinaryPoint(Double disciplinaryPoint) {
    this.disciplinaryPoint = disciplinaryPoint;
  }

  public Trainee getTrainee() {
    return trainee;
  }

  public void setTrainee(Trainee trainee) {
    this.trainee = trainee;
  }

  @Override
  public String toString() {
    return "AttendantStatus [id=" + id + ", milestoneName=" + milestoneName
        + ", absentTime=" + absentTime + ", lateOrEarlyLeave="
        + lateOrEarlyLeave + ", noPermissionsRate=" + noPermissionsRate
        + ", disciplinaryPoint=" + disciplinaryPoint + "]";
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof AttendantStatus) {
      AttendantStatus attendantStatus = (AttendantStatus) o;
      return this.id == attendantStatus.id;
    }
    return super.equals(o);
  }

}
