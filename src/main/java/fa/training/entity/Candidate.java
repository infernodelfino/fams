package fa.training.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * The persistent Candidate for the Candidate table in database.
 * 
 * @author ThanhDT19
 *
 */
@Entity
public class Candidate {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String status;
  private String location;
  private String remarks;

  @OneToOne
  @JoinColumn(name = "trainee_id")
  private Trainee trainee;

  public Candidate() {
    super();
  }

  public Candidate(int id, String status, String location, Trainee trainee) {
    super();
    this.id = id;
    this.status = status;
    this.location = location;
    this.trainee = trainee;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getRemarks() {
    return remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Trainee getTrainee() {
    return trainee;
  }

  public void setTrainee(Trainee trainee) {
    this.trainee = trainee;
  }

}
