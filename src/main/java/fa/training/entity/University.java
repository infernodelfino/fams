package fa.training.entity;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class University {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String universityName;

  @OneToMany(mappedBy = "university")
  private List<Trainee> trainees;

  public University() {
    super();
  }

  public University(String universityName) {
    super();
    this.universityName = universityName;
  }

  public University(int id, String universityName) {
    super();
    this.id = id;
    this.universityName = universityName;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUniversityName() {
    return universityName;
  }

  public void setUniversityName(String universityName) {
    this.universityName = universityName;
  }

  public List<Trainee> getTrainees() {
    return trainees;
  }

  public void setTrainees(List<Trainee> trainees) {
    this.trainees = trainees;
  }

  @Override
  public String toString() {
    return "University [id=" + id + ", universityName=" + universityName + "]";
  }

}
