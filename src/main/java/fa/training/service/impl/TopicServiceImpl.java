package fa.training.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fa.training.entity.Topic;
import fa.training.repository.TopicRepository;
import fa.training.service.TopicService;

@Service
public class TopicServiceImpl implements TopicService {
  @Autowired
  private TopicRepository topicRepository;

  @Override
  public List<Topic> getAllTopics() {
    List<Topic> listOfTopic = topicRepository.findAll();
    return listOfTopic;
  }
}
