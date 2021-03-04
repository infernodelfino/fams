package fa.training.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * (C) Copyright 2020 Fresher Academy. All Rights Reserved.
 * 
 * @author G3
 * @date Apr 3, 2020
 * @version 1.0
 */
@Entity
public class Allowance {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private Double gpa;
  private String level;
  private String salaryPaid;
  private Double standardAllowance;
  private String remarks;
  private String milestoneName;
  // thieu milestone name r

  @ManyToOne
  @JoinColumn(name = "trainee_id")
  private Trainee trainee;

  public Allowance() {
    super();
  }

  public Allowance(int id, Double gpa, String level, String salaryPaid,
      Double standardAllowance, String remarks, String milestoneName,
      Trainee trainee) {
    super();
    this.id = id;
    this.gpa = gpa;
    this.level = level;
    this.salaryPaid = salaryPaid;
    this.standardAllowance = standardAllowance;
    this.remarks = remarks;
    this.milestoneName = milestoneName;
    this.trainee = trainee;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Double getGpa() {
    return gpa;
  }

  public void setGpa(Double gpa) {
    this.gpa = gpa;
  }

  public String getLevel() {
    return level;
  }

  public void setLevel(String level) {
    this.level = level;
  }

  public String getSalaryPaid() {
    return salaryPaid;
  }

  public void setSalaryPaid(String salaryPaid) {
    this.salaryPaid = salaryPaid;
  }

  public Double getStandardAllowance() {
    return standardAllowance;
  }

  public void setStandardAllowance(Double standardAllowance) {
    this.standardAllowance = standardAllowance;
  }

  public String getRemarks() {
    return remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
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

  @Override
  public String toString() {
    return "Allowance [id=" + id + ", gpa=" + gpa + ", level=" + level
        + ", salaryPaid=" + salaryPaid + ", standardAllowance="
        + standardAllowance + ", remarks=" + remarks + ", milestoneName="
        + milestoneName + ", trainee=" + trainee + "]";
  }
}
