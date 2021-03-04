package fa.training.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

/**
 * (C) Copyright 2020 Fresher Academy. All Rights Reserved.
 * 
 * @author G3
 * @date Apr 3, 2020
 * @version 1.0
 */
@Entity
public class Commitment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private int commitmentValue; // double
  private int workingDuration;
  @JsonSerialize(using = LocalDateSerializer.class)
  @JsonDeserialize(using = LocalDateDeserializer.class)
  private LocalDate startDate;
  @JsonSerialize(using = LocalDateSerializer.class)
  @JsonDeserialize(using = LocalDateDeserializer.class)
  private LocalDate endDate;
  private String remark;
  @OneToOne
  @JoinColumn(name = "trainee_id")
  private Trainee trainee;

  public Commitment() {
    super();
  }

  public Commitment(int commitmentValue, int workingDuration,
      LocalDate startDate, LocalDate endDate, Trainee trainee) {
    super();
    this.commitmentValue = commitmentValue;
    this.workingDuration = workingDuration;
    this.startDate = startDate;
    this.endDate = endDate;
    this.trainee = trainee;
  }
  
  public Commitment(int id, int commitmentValue, int workingDuration,
      LocalDate startDate, LocalDate endDate, Trainee trainee) {
    super();
    this.id = id;
    this.commitmentValue = commitmentValue;
    this.workingDuration = workingDuration;
    this.startDate = startDate;
    this.endDate = endDate;
    this.trainee = trainee;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getCommitmentValue() {
    return commitmentValue;
  }

  public void setCommitmentValue(int commitmentValue) {
    this.commitmentValue = commitmentValue;
  }

  public int getWorkingDuration() {
    return workingDuration;
  }

  public void setWorkingDuration(int workingDuration) {
    this.workingDuration = workingDuration;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }

  public Trainee getTrainee() {
    return trainee;
  }

  public void setTrainee(Trainee trainee) {
    this.trainee = trainee;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  @Override
  public String toString() {
    return "Commitment [id=" + id + ", commitmentValue=" + commitmentValue
        + ", workingDuration=" + workingDuration + ", startDate=" + startDate
        + ", endDate=" + endDate + "]";
  }

}
