package fa.training.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Topic {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String topicName;

  public Topic(int id, String topicName) {
    super();
    this.id = id;
    this.topicName = topicName;
  }

  public Topic(String topicName) {
    super();
    this.topicName = topicName;
  }

  public Topic(int id) {
    super();
    this.id = id;
  }

  public Topic() {
    super();
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTopicName() {
    return topicName;
  }

  public void setTopicName(String topicName) {
    this.topicName = topicName;
  }

  @Override
  public String toString() {
    return "Topic [id=" + id + ", topicName=" + topicName + "]";
  }

}
