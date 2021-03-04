package fa.training.entity;

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

/**
 * (C) Copyright 2020 Fresher Academy. All Rights Reserved.
 * 
 * @author G3
 * @date Apr 3, 2020
 * @version 1.0
 */
@Entity
public class RewardPenalty {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String milestoneName;
  @JsonSerialize(using = LocalDateSerializer.class)
  @JsonDeserialize(using = LocalDateDeserializer.class)
  private LocalDate createdDate;
  private int bonusPoint;
  private int penaltyPoint;
  private String reason;

  @ManyToOne
  @JoinColumn(name = "trainee_id")
  private Trainee trainee;

  public RewardPenalty() {
    super();
  }

  public RewardPenalty(String milestoneName, LocalDate createdDate,
      int bonusPoint, int penaltyPoint, String reason, Trainee trainee) {
    super();
    this.milestoneName = milestoneName;
    this.createdDate = createdDate;
    this.bonusPoint = bonusPoint;
    this.penaltyPoint = penaltyPoint;
    this.reason = reason;
    this.trainee = trainee;
  }
  
  public RewardPenalty(int id, String milestoneName, LocalDate createdDate,
      int bonusPoint, int penaltyPoint, String reason, Trainee trainee) {
    super();
    this.id = id;
    this.milestoneName = milestoneName;
    this.createdDate = createdDate;
    this.bonusPoint = bonusPoint;
    this.penaltyPoint = penaltyPoint;
    this.reason = reason;
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

  public LocalDate getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(LocalDate createdDate) {
    this.createdDate = createdDate;
  }

  public int getBonusPoint() {
    return bonusPoint;
  }

  public void setBonusPoint(int bonusPoint) {
    this.bonusPoint = bonusPoint;
  }

  public int getPenaltyPoint() {
    return penaltyPoint;
  }

  public void setPenaltyPoint(int penaltyPoint) {
    this.penaltyPoint = penaltyPoint;
  }

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  public Trainee getTrainee() {
    return trainee;
  }

  public void setTrainee(Trainee trainee) {
    this.trainee = trainee;
  }

  @Override
  public String toString() {
    return "RewardPenalty [id=" + id + ", milestoneName=" + milestoneName
        + ", createdDate=" + createdDate + ", bonusPoint=" + bonusPoint
        + ", penaltyPoint=" + penaltyPoint + ", reason=" + reason + "]";
  }

}
