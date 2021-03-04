package fa.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import fa.training.entity.Topic;

public interface TopicRepository extends JpaRepository<Topic, Integer> {

}
