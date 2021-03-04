package fa.training.entity;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * (C) Copyright 2020 Fresher Academy. All Rights Reserved.
 * 
 * @author G3
 * @date Apr 3, 2020
 * @version 1.0
 */
@Entity
public class Allocation {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String allocatedFsu;
  private Double salary;
  private LocalDate startDate;
  private String allocationStatus;
  private String remarks;

  @OneToOne
  @JoinColumn(name = "trainee_id")
  private Trainee trainee;

  public Allocation() {
    super();
  }

  public Allocation(String allocatedFsu, Double salary, LocalDate startDate,
      String allocationStatus, String remarks, Trainee trainee) {
    super();
    this.allocatedFsu = allocatedFsu;
    this.salary = salary;
    this.startDate = startDate;
    this.allocationStatus = allocationStatus;
    this.remarks = remarks;
    this.trainee = trainee;
  }

  public Allocation(int id, String allocatedFsu, Double salary,
      LocalDate startDate, String allocationStatus, String remarks,
      Trainee trainee) {
    super();
    this.id = id;
    this.allocatedFsu = allocatedFsu;
    this.salary = salary;
    this.startDate = startDate;
    this.allocationStatus = allocationStatus;
    this.remarks = remarks;
    this.trainee = trainee;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getAllocatedFsu() {
    return allocatedFsu;
  }

  public void setAllocatedFsu(String allocatedFsu) {
    this.allocatedFsu = allocatedFsu;
  }

  public Double getSalary() {
    return salary;
  }

  public void setSalary(Double salary) {
    this.salary = salary;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public String getAllocationStatus() {
    return allocationStatus;
  }

  public void setAllocationStatus(String allocationStatus) {
    this.allocationStatus = allocationStatus;
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

  @Override
  public String toString() {
    return "Allocation [id=" + id + ", allocatedFSU=" + allocatedFsu
        + ", salary=" + salary + ", startDate=" + startDate
        + ", allocationStatus=" + allocationStatus + ", remarks=" + remarks
        + "]";
  }

}
