package fa.training.service;

import java.util.List;
import fa.training.entity.LearningDetail;
import fa.training.exception.DeleteLearnDetailException;

public interface LearingDetailService {

  List<LearningDetail> getAllLearningDetail();

  List<String> getLearningCodeDistinct();

  public List<LearningDetail> findByLearnCode(String learnCode);

  public LearningDetail saveLearningDetail(LearningDetail detail);

  public boolean saveOrDeleteLearnDetail(List<LearningDetail> listOfLearnDetail)
      throws DeleteLearnDetailException;

}