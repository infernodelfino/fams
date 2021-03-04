package fa.training.entity.vo;

/**
 * This class store data for add model landing page.
 * 
 * @author Admin
 *
 */
public class DataVo {
  private String status;
  private String skill;
  private long countData;
  /**
   * calculate percent.
   */
  private double percent;

  public DataVo() {
    super();
  }

  public DataVo(String status, String skill, long countData) {
    super();
    this.status = status;
    this.skill = skill;
    this.countData = countData;
  }

  public DataVo(String status, String skill, long countData, double percent) {
    super();
    this.status = status;
    this.skill = skill;
    this.countData = countData;
    this.percent = percent;
  }

  public double getPercent() {
    return percent;
  }

  public void setPercent(double percent) {
    this.percent = percent;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getSkill() {
    return skill;
  }

  public void setSkill(String skill) {
    this.skill = skill;
  }

  public long getCountData() {
    return countData;
  }

  public void setCountData(long countData) {
    this.countData = countData;
  }

  @Override
  public String toString() {
    return "DataVo [status=" + status + ", skill=" + skill + ", countData="
        + countData + ", percent=" + percent + "]";
  }

  /**
   * To compare by skill and status.
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((skill == null) ? 0 : skill.hashCode());
    result = prime * result + ((status == null) ? 0 : status.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    DataVo other = (DataVo) obj;
    if (skill == null) {
      if (other.skill != null) {
        return false;
      }
    } else if (!skill.equals(other.skill)) {
      return false;
    }
    if (status == null) {
      if (other.status != null) {
        return false;
      }
    } else if (!status.equals(other.status)) {
      return false;
    }
    return true;
  }
}
