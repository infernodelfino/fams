package fa.training.entity;

public class Trainer {
  private int id;
  private String fullName;
  private String skill;
  private String status;

  public Trainer() {
    super();
  }

  public Trainer(String fullName, String skill, String status) {
    super();
    this.fullName = fullName;
    this.skill = skill;
    this.status = status;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getSkill() {
    return skill;
  }

  public void setSkill(String skill) {
    this.skill = skill;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

}
