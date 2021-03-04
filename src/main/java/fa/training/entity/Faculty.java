package fa.training.entity;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Faculty {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String facultyName;

  @OneToMany(mappedBy = "faculty")
  private List<Trainee> trainees;

  public Faculty() {
    super();
  }

  public Faculty(String facultyName) {
    super();
    this.facultyName = facultyName;
  }

  public Faculty(int id, String facultyName) {
    super();
    this.id = id;
    this.facultyName = facultyName;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFacultyName() {
    return facultyName;
  }

  public void setFacultyName(String facultyName) {
    this.facultyName = facultyName;
  }

  public List<Trainee> getTrainees() {
    return trainees;
  }

  public void setTrainees(List<Trainee> trainees) {
    this.trainees = trainees;
  }

  @Override
  public String toString() {
    return "Faculty [id=" + id + ", facultyName=" + facultyName + "]";
  }

}
