package fa.training.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Trainee {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @OneToMany(mappedBy = "trainee")
  private List<LearningPath> learningPaths;

  @OneToMany(mappedBy = "trainee")
  private List<AttendantStatus> attendantStatus;

  @OneToMany(mappedBy = "trainee", cascade = CascadeType.ALL)
  private List<RewardPenalty> rewardPenalty;

  @OneToMany(mappedBy = "trainee", cascade = CascadeType.ALL)
  private List<Gpa> gpa;

  @OneToOne(mappedBy = "trainee", cascade = CascadeType.ALL)
  private Allocation allocation;

  @OneToOne(mappedBy = "trainee", cascade = CascadeType.ALL)
  private Commitment commitment;

  @OneToMany(mappedBy = "trainee", cascade = CascadeType.ALL)
  private List<Allowance> allowances;

  @OneToOne
  @JoinColumn(name = "university_id")
  private University university;
  @OneToOne
  @JoinColumn(name = "faculty_id")
  private Faculty faculty;
  @ManyToOne
  @JoinColumn(name = "class_id")
  private Classes classes;

  private String traineeID;
  @Column(columnDefinition = "")
  private String name;
  private String account;
  private String allocationStatus;
  private String type; // chi co 2 loai trainee va candidate
  private String status;
  private boolean gender;
  private LocalDate dateOfBirth;
  private String phone;
  private String email;
  private String tPBAccount;
  private String history;
  private String skill;
  private boolean active;
  private boolean salaryPaid;
  private String allowanceGroup; // co the dung entity

  private LocalDate createdDate;
  private String adminCreate;

  public Trainee() {
    super();
  }

  public Trainee(int id) {
    super();
    this.id = id;
  }

  public Trainee(List<LearningPath> learningPaths,
      List<AttendantStatus> attendantStatus, List<RewardPenalty> rewardPenalty,
      List<Gpa> gpa, Allocation allocation, Commitment commitment,
      University university, Faculty faculty, Classes classes, String name,
      String account, String type, String status, boolean gender,
      LocalDate dateOfBirth, String phone, String email, String tPBAccount,
      String history, String skill, String allowanceGroup, boolean active) {
    super();
    this.learningPaths = learningPaths;
    this.attendantStatus = attendantStatus;
    this.rewardPenalty = rewardPenalty;
    this.gpa = gpa;
    this.allocation = allocation;
    this.commitment = commitment;
    this.university = university;
    this.faculty = faculty;
    this.classes = classes;
    this.name = name;
    this.account = account;
    this.type = type;
    this.status = status;
    this.gender = gender;
    this.dateOfBirth = dateOfBirth;
    this.phone = phone;
    this.email = email;
    this.tPBAccount = tPBAccount;
    this.history = history;
    this.skill = skill;
    this.allowanceGroup = allowanceGroup;
    this.active = active;
  }

  public String getTraineeID() {
    return traineeID;
  }

  public void setTraineeID(String traineeID) {
    this.traineeID = traineeID;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public List<Allowance> getAllowances() {
    return allowances;
  }

  public void setAllowances(List<Allowance> allowances) {
    this.allowances = allowances;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public boolean isSalaryPaid() {
    return salaryPaid;
  }

  public void setSalaryPaid(boolean salaryPaid) {
    this.salaryPaid = salaryPaid;
  }

  public LocalDate getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(LocalDate createdDate) {
    this.createdDate = createdDate;
  }

  public String getAdminCreate() {
    return adminCreate;
  }

  public void setAdminCreate(String adminCreate) {
    this.adminCreate = adminCreate;
  }

  public List<LearningPath> getLearningPaths() {
    return learningPaths;
  }

  public void setLearningPaths(List<LearningPath> learningPaths) {
    this.learningPaths = learningPaths;
  }

  public List<AttendantStatus> getAttendantStatus() {
    return attendantStatus;
  }

  public void setAttendantStatus(List<AttendantStatus> attendantStatus) {
    this.attendantStatus = attendantStatus;
  }

  public List<RewardPenalty> getRewardPenalty() {
    return rewardPenalty;
  }

  public void setRewardPenalty(List<RewardPenalty> rewardPenalty) {
    this.rewardPenalty = rewardPenalty;
  }

  public List<Gpa> getGpa() {
    return gpa;
  }

  public void setGpa(List<Gpa> gpa) {
    this.gpa = gpa;
  }

  public Allocation getAllocation() {
    return allocation;
  }

  public void setAllocation(Allocation allocation) {
    this.allocation = allocation;
  }

  public Commitment getCommitment() {
    return commitment;
  }

  public void setCommitment(Commitment commitment) {
    this.commitment = commitment;
  }

  public University getUniversity() {
    return university;
  }

  public void setUniversity(University university) {
    this.university = university;
  }

  public Faculty getFaculty() {
    return faculty;
  }

  public void setFaculty(Faculty faculty) {
    this.faculty = faculty;
  }

  public Classes getClasses() {
    return classes;
  }

  public void setClasses(Classes classes) {
    this.classes = classes;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAccount() {
    return account;
  }

  public void setAccount(String account) {
    this.account = account;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public boolean isGender() {
    return gender;
  }

  public void setGender(boolean gender) {
    this.gender = gender;
  }

  public LocalDate getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(LocalDate dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String gettPBAccount() {
    return tPBAccount;
  }

  public void settPBAccount(String tPBAccount) {
    this.tPBAccount = tPBAccount;
  }

  public String getHistory() {
    return history;
  }

  public void setHistory(String history) {
    this.history = history;
  }

  public String getSkill() {
    return skill;
  }

  public void setSkill(String skill) {
    this.skill = skill;
  }

  public String getAllowanceGroup() {
    return allowanceGroup;
  }

  public void setAllowanceGroup(String allowanceGroup) {
    this.allowanceGroup = allowanceGroup;
  }

  public String getAllocationStatus() {
    return allocationStatus;
  }

  public void setAllocationStatus(String allocationStatus) {
    this.allocationStatus = allocationStatus;
  }

  @Override
  public String toString() {
    return "Trainee [id=" + id + ", name=" + name + ", account=" + account
        + ", type=" + type + ", status=" + status + ", gender=" + gender
        + ", dateOfBirth=" + dateOfBirth + ", phone=" + phone + ", email="
        + email + ", tPBAccount=" + tPBAccount + ", history=" + history
        + ", skill=" + skill + ", active=" + active + ", salaryPaid="
        + salaryPaid + "]";
  }

}
