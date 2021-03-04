package fa.training.entity.vo;

import java.io.Serializable;
import java.time.LocalDate;

public class Milestone implements Serializable {
  
  private static final long serialVersionUID = 438820624260596472L;
  private String milestoneName;
  private String salaryPaid;
  private LocalDate startDate;
  private LocalDate endDate;

  public String getMilestoneName() {
    return milestoneName;
  }

  public void setMilestoneName(String milestoneName) {
    this.milestoneName = milestoneName;
  }

  public String getSalaryPaid() {
    return salaryPaid;
  }

  public void setSalaryPaid(String salaryPaid) {
    this.salaryPaid = salaryPaid;
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

  public Milestone(String milestoneName, String salaryPaid, LocalDate startDate,
      LocalDate endDate) {
    super();
    this.milestoneName = milestoneName;
    this.salaryPaid = salaryPaid;
    this.startDate = startDate;
    this.endDate = endDate;
  }

  public Milestone() {
    super();
  }

  @Override
  public String toString() {
    return "Milestone [milestoneName=" + milestoneName + ", salaryPaid="
        + salaryPaid + ", startDate=" + startDate + ", endDate=" + endDate
        + "]";
  }

}
