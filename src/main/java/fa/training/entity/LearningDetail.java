package fa.training.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class LearningDetail implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @Column(name = "learnCode")
  private String learnCode;

  @ManyToOne
  @JoinColumn(name = "topic")
  private Topic topic;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public LearningDetail(int id) {
    super();
    this.id = id;
  }

  public LearningDetail(int id, String learnCode, Topic topic) {
    super();
    this.id = id;
    this.learnCode = learnCode;
    this.topic = topic;
  }

  public LearningDetail(String learnCode, Topic topic) {
    super();
    this.learnCode = learnCode;
    this.topic = topic;
  }

  public LearningDetail() {
    super();
  }

  public String getLearnCode() {
    return learnCode;
  }

  public void setLearnCode(String learnCode) {
    this.learnCode = learnCode;
  }

  public Topic getTopic() {
    return topic;
  }

  public void setTopic(Topic topic) {
    this.topic = topic;
  }

  @Override
  public String toString() {
    return "LearningDetail [id=" + id + ", learnCode=" + learnCode + ", topic="
        + topic + "]";
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof LearningDetail) {
      LearningDetail detail = (LearningDetail) o;
      return this.id == detail.id;
    }
    return super.equals(o);
  }

}
