package fa.training.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fa.training.entity.vo.DataVo;
import fa.training.repository.CandidateRepository;
import fa.training.service.CandidateService;
import fa.training.utils.Log4J;

/**
 * This class to defines all method to handle business logic candidate.
 * 
 * @author ThanhDT19
 *
 */
@Service
public class CandidateServiceImpl implements CandidateService {
  @Autowired
  CandidateRepository candidateRepository;

  /**
   * This method to get all candidate.
   * 
   * @return list candidate
   */
  @Override
  public List<DataVo> countCandidateGroupBySkill(String type) {
    List<DataVo> dataVos = new ArrayList<DataVo>();
    String status = "inprogress";
    for (Object[] ele : candidateRepository.countCandidateGroupBySkill()) {
      if ("new".equalsIgnoreCase(ele[0].toString())) {
        status = "planning";
      }
      if ("transferred".equalsIgnoreCase(ele[0].toString())) {
        status = "planned";
      }
      dataVos = addDataVos(dataVos, ele, status);
      status = "inprogress";
    }
    return type.equalsIgnoreCase("chart") ? calculatePercent(dataVos, "all")
        : dataVos;
  }

  /**
   * This method to get list candidate with condition location.
   * 
   * @param location candidate's location from browser's input
   * @return list candidate with condition location
   */
  @Override
  public List<DataVo> countCandidateGroupBySkillByLocation(String location,
      String type) {
    List<DataVo> dataVos = new ArrayList<DataVo>();
    String status = "inprogress";
    for (Object[] ele : candidateRepository
        .countCandidateGroupBySkillByLocation(location)) {
      if ("new".equalsIgnoreCase(ele[0].toString())) {
        status = "planning";
      }
      if ("transferred".equalsIgnoreCase(ele[0].toString())) {
        status = "planned";
      }
      dataVos = addDataVos(dataVos, ele, status);
      status = "inprogress";
    }
    return type.equalsIgnoreCase("chart") ? calculatePercent(dataVos, "all")
        : dataVos;
  }

  /**
   * This method to get list candidate with condition status.
   * 
   * @param status candidate's status from browser's input
   * @return list candidate with condition status
   */
  @Override
  public List<DataVo> countCandidateGroupBySkillByStatus(String status,
      String type) {
    List<Object[]> objects = new ArrayList<>();
    List<DataVo> dataVos = new ArrayList<DataVo>();
    objects = getCandidatesByStatus(status);
    String statusSearch = "inprogress";
    for (Object[] ele : objects) {
      if ("new".equalsIgnoreCase(ele[0].toString())) {
        statusSearch = "planning";
      }
      if ("transferred".equalsIgnoreCase(ele[0].toString())) {
        statusSearch = "planned";
      }
      dataVos = addDataVos(dataVos, ele, statusSearch);
      statusSearch = "inprogress";
    }
    return type.equalsIgnoreCase("chart") ? calculatePercent(dataVos, status)
        : dataVos;
  }

  /**
   * This method to get list candidate with condition status.
   * 
   * @param status   candidate's status from browser's input
   * @param location candidate's location from browser's input
   * @return list candidate with condition status and location
   */
  @Override
  public List<DataVo> countCandidateGroupBySkillByStatusAndLocation(
      String status, String location, String type) {
    List<Object[]> objects = new ArrayList<>();
    List<DataVo> dataVos = new ArrayList<DataVo>();
    objects = getCandidatesByStatus(status);
    String statusSearch = "inprogress";
    for (Object[] ele : objects) {
      if ("new".equalsIgnoreCase(ele[0].toString())) {
        statusSearch = "planning";
      }
      if ("transferred".equalsIgnoreCase(ele[0].toString())) {
        statusSearch = "planned";
      }
      dataVos = addDataVos(dataVos, ele, statusSearch);
      statusSearch = "inprogress";
    }
    return type.equalsIgnoreCase("chart") ? calculatePercent(dataVos, status)
        : dataVos;
  }

  /**
   * This method to get list candidate's location.
   * 
   * @return list candidate's location
   */
  @Override
  public List<String> getLocations() {
    return candidateRepository.getLocations();
  }

  /**
   * This method to change data from database to instance of DataVo.
   * 
   * @param dataVos list data to return
   * @param ele     a Object[] was a row from database
   * @param status  candidate's status
   * @return list dataVos
   */
  private List<DataVo> addDataVos(List<DataVo> dataVos, Object[] ele,
      String status) {
    DataVo dataVo = new DataVo(status, ele[1].toString(),
        Long.valueOf(ele[2].toString()));
    if (dataVos.contains(dataVo)) {
      dataVo = dataVos.get(dataVos.indexOf(dataVo));
      dataVo.setCountData(dataVo.getCountData() + 1);
      dataVos.remove(dataVo);
    }
    dataVos.add(dataVo);
    return dataVos;
  }

  /**
   * This method to change candidate's status between database and instance of
   * DataVO.
   * 
   * @param status candidate's status to get data
   * @return list dataVos
   */
  public List<Object[]> getCandidatesByStatus(String status) {
    List<Object[]> objects = new ArrayList<>();
    String statusCandidate = null;
    if ("planning".equalsIgnoreCase(status)) {
      statusCandidate = "new";
      objects = candidateRepository
          .countCandidateGroupBySkillByStatus(statusCandidate);
    }
    if ("planned".equalsIgnoreCase(status)) {
      statusCandidate = "transferred";
      objects = candidateRepository
          .countCandidateGroupBySkillByStatus(statusCandidate);
    }
    if ("inprogress".equalsIgnoreCase(status)) {
      objects = candidateRepository.countCandidateGroupBySkillByStatusOr(
          "test-pass", "test-fail", "interview-pass", "interview-fail");
    }
    return objects;
  }

  /**
   * This method to canculate percent data.
   * 
   * @param dataVos list data to canculate
   * @param status  status to canculate
   * @return list after canculate
   * @throws Exception an exception
   */
  public List<DataVo> calculatePercent(List<DataVo> dataVos, String status) {
    if ("all".equalsIgnoreCase(status)) {
      calDetail(dataVos);
    } else {
      long total = dataVos.stream().mapToLong(x -> x.getCountData()).sum();
      Log4J.getLogger().info(total);
      for (DataVo ele : dataVos) {
        ele.setPercent(((double) ele.getCountData() / total) * 100);
      }
    }
    return dataVos;
  }

  /**
   * This method to calculate percent detail.
   * 
   * @param dataVos is object was calculated
   */
  private void calDetail(List<DataVo> dataVos) {
    long totalPlanning = dataVos.stream()
        .filter(x -> x.getStatus().equalsIgnoreCase("planning"))
        .mapToLong(x -> x.getCountData()).sum();
    long totalPlanned = dataVos.stream()
        .filter(x -> x.getStatus().equalsIgnoreCase("planned"))
        .mapToLong(x -> x.getCountData()).sum();
    long totalProgress = dataVos.stream()
        .filter(x -> x.getStatus().equalsIgnoreCase("inprogress"))
        .mapToLong(x -> x.getCountData()).sum();
    for (DataVo ele : dataVos) {
      if (ele.getStatus().equalsIgnoreCase("planning")) {
        ele.setPercent(((double) ele.getCountData() / totalPlanning) * 100);
      } else if (ele.getStatus().equalsIgnoreCase("planned")) {
        ele.setPercent(((double) ele.getCountData() / totalPlanned) * 100);
      } else if (ele.getStatus().equalsIgnoreCase("inprogress")) {
        ele.setPercent(((double) ele.getCountData() / totalProgress) * 100);
      }
    }
  }

}
