package fa.training.service.impl;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fa.training.entity.LearningDetail;
import fa.training.exception.DeleteLearnDetailException;
import fa.training.repository.LearningDetailRepository;
import fa.training.repository.LearningPathRepository;
import fa.training.service.LearingDetailService;

@Service
public class LearningDetailServiceImpl implements LearingDetailService {
  @Autowired
  private LearningDetailRepository learningDetailRepository;
  @Autowired
  private LearningPathRepository learningPathRepository;

  /**.
   *This method to get all LearningDetail
   */
  @Override
  public List<LearningDetail> getAllLearningDetail() {
    return learningDetailRepository.findAll();
  }

  /**.
   *This method to get LearnCode distinct from LearningDetail table
   */
  @Override
  public List<String> getLearningCodeDistinct() {
    List<Object[]> objects = learningDetailRepository.getLearningCodeDistinct();
    List<String> learnCodes = new ArrayList<String>();
    for (Object[] o : objects) {
      learnCodes.add(o[0].toString());
    }
    return learnCodes;
  }

  /**.
   *This method to get list LearningDetail by LearnCode
   */
  @Override
  public List<LearningDetail> findByLearnCode(String learnCode) {
    return learningDetailRepository.findByLearnCode(learnCode);
  }

  /**.
   *This method to save LearningDetail
   */
  @Override
  public LearningDetail saveLearningDetail(LearningDetail detail) {
    LearningDetail learningDetailSaved = learningDetailRepository.save(detail);
    return learningDetailSaved;
  }

  /**.
   *This method to save, update or delete LearningDetail
   */
  @Override
  @Transactional(rollbackOn = { DeleteLearnDetailException.class })
  public boolean saveOrDeleteLearnDetail(List<LearningDetail> listOfLearnDetail)
      throws DeleteLearnDetailException {
    if (listOfLearnDetail == null || listOfLearnDetail.size() == 0) {
      return false;
    }
    List<LearningDetail> listLearnDetailDatabase = learningDetailRepository
        .findByLearnCode(listOfLearnDetail.get(0).getLearnCode());
    for (LearningDetail learningDetail : listLearnDetailDatabase) {
      if (!listOfLearnDetail.contains(learningDetail)) {
        deleteLearningDetail(learningDetail);
      }
    }
    listOfLearnDetail.forEach(x -> learningDetailRepository.save(x));
    return true;
  }

  /**.
   * This method to check LearningDetail before delete LearningDetail
   * @param learningDetail data need check
   * @return true if LearningDetail allow delete
   * @throws DeleteLearnDetailException when LearningDetail not allow delete
   */
  private boolean deleteLearningDetail(LearningDetail learningDetail)
      throws DeleteLearnDetailException {
    long countByLearningCode = learningPathRepository
        .countByLearningCode(learningDetail);
    if (countByLearningCode > 0) {
      throw new DeleteLearnDetailException("you can not delete topic "
          + learningDetail.getTopic().getTopicName());
    } else {
      learningDetailRepository.delete(learningDetail);
    }
    return true;
  }

}
