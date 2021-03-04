package fa.training.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

@Entity
public class LearningPath implements Serializable {
  private static final long serialVersionUID = 3380302075051884335L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @ManyToOne
  @JoinColumn(name = "trainee_id")
  private Trainee trainee;

  private String milestoneName;
  private String salaryPaid;
  @JsonSerialize(using = LocalDateSerializer.class)
  @JsonDeserialize(using = LocalDateDeserializer.class)
  private LocalDate startMilestoneDate;
  @JsonSerialize(using = LocalDateSerializer.class)
  @JsonDeserialize(using = LocalDateDeserializer.class)
  private LocalDate endMilestoneDate;

  private Double maxScore;
  private Double passScore;
  private Double score;
  private Double weightedNumber;

  @ManyToOne
  @JoinColumn(referencedColumnName = "learnCode")
  @JoinColumn(referencedColumnName = "topic")
  private LearningDetail learningCode;

  public LearningPath() {
    super();
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof LearningPath) {
      LearningPath learningPath = (LearningPath) o;
      return this.id == learningPath.id;
    }
    return super.equals(o);
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }


  public LearningPath(int id, Trainee trainee, String milestoneName,
      String salaryPaid, LocalDate startMilestoneDate,
      LocalDate endMilestoneDate, Double maxScore, Double passScore,
      Double score, Double weightedNumber, LearningDetail learningCode) {
    super();
    this.id = id;
    this.trainee = trainee;
    this.milestoneName = milestoneName;
    this.salaryPaid = salaryPaid;
    this.startMilestoneDate = startMilestoneDate;
    this.endMilestoneDate = endMilestoneDate;
    this.maxScore = maxScore;
    this.passScore = passScore;
    this.score = score;
    this.weightedNumber = weightedNumber;
    this.learningCode = learningCode;
  }

  
  public LearningPath(Trainee trainee, String milestoneName, String salaryPaid,
      LocalDate startMilestoneDate, LocalDate endMilestoneDate, Double maxScore,
      Double passScore, Double score, Double weightedNumber,
      LearningDetail learningCode) {
    super();
    this.trainee = trainee;
    this.milestoneName = milestoneName;
    this.salaryPaid = salaryPaid;
    this.startMilestoneDate = startMilestoneDate;
    this.endMilestoneDate = endMilestoneDate;
    this.maxScore = maxScore;
    this.passScore = passScore;
    this.score = score;
    this.weightedNumber = weightedNumber;
    this.learningCode = learningCode;
  }

  public Trainee getTrainee() {
    return trainee;
  }

  public void setTrainee(Trainee trainee) {
    this.trainee = trainee;
  }

  public String getMilestoneName() {
    return milestoneName;
  }

  public void setMilestoneName(String milestoneName) {
    this.milestoneName = milestoneName;
  }

  public LocalDate getStartMilestoneDate() {
    return startMilestoneDate;
  }

  public void setStartMilestoneDate(LocalDate startMilestoneDate) {
    this.startMilestoneDate = startMilestoneDate;
  }

  public LocalDate getEndMilestoneDate() {
    return endMilestoneDate;
  }

  public void setEndMilestoneDate(LocalDate endMilestoneDate) {
    this.endMilestoneDate = endMilestoneDate;
  }

  public Double getMaxScore() {
    return maxScore;
  }

  public void setMaxScore(Double maxScore) {
    this.maxScore = maxScore;
  }

  public Double getPassScore() {
    return passScore;
  }

  public void setPassScore(Double passScore) {
    this.passScore = passScore;
  }

  public Double getScore() {
    return score;
  }

  public void setScore(Double score) {
    this.score = score;
  }

  public Double getWeightedNumber() {
    return weightedNumber;
  }

  public void setWeightedNumber(Double weightedNumber) {
    this.weightedNumber = weightedNumber;
  }

  public LearningDetail getLearningCode() {
    return learningCode;
  }

  public void setLearningCode(LearningDetail learningCode) {
    this.learningCode = learningCode;
  }

  public String getSalaryPaid() {
    return salaryPaid;
  }

  public void setSalaryPaid(String salaryPaid) {
    this.salaryPaid = salaryPaid;
  }

  @Override
  public String toString() {
    return "LearningPath [id=" + id + ", trainee=" + trainee
        + ", milestoneName=" + milestoneName + ", salaryPaid=" + salaryPaid
        + ", startMilestoneDate=" + startMilestoneDate + ", endMilestoneDate="
        + endMilestoneDate + ", maxScore=" + maxScore + ", passScore="
        + passScore + ", score=" + score + ", weightedNumber=" + weightedNumber
        + ", learningCodeId=" + learningCode.getId() + ", learningCode="
        + learningCode.getLearnCode() + ", learningCodeTopic="
        + learningCode.getTopic() + "]";
  }

}
