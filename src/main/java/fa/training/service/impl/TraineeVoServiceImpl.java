package fa.training.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fa.training.entity.vo.DataVo;
import fa.training.repository.TraineeVoRepository;
import fa.training.service.TraineeVoService;
import fa.training.utils.Log4J;

/**
 * This class to defines all method to handle business logic about trainee.
 * 
 * @author ThanhDT19
 *
 */
@Service
public class TraineeVoServiceImpl implements TraineeVoService {

  @Autowired
  TraineeVoRepository traineeVoRepository;

  /**
   * This method to get all trainee.
   * 
   * @return list trainee
   */
  @Override
  public List<DataVo> countTraineesGroupBySkill(String type) {
    List<DataVo> dataVos = new ArrayList<DataVo>();
    DataVo dataVo = null;
    for (Object[] ele : traineeVoRepository.countTraineesGroupBySkill()) {
      dataVo = new DataVo(ele[0].toString(), ele[1].toString(),
          Long.valueOf(ele[2].toString()));
      dataVos.add(dataVo);
    }
    return type.equalsIgnoreCase("chart") ? canculatePercent(dataVos, "all")
        : dataVos;
  }

  /**
   * This method to get list trainee with condition location.
   * 
   * @param location trainee's location from browser's input
   * @return list trainee with condition location
   */
  @Override
  public List<DataVo> countTraineesGroupBySkillByLocation(String location,
      String type) {
    List<DataVo> dataVos = new ArrayList<DataVo>();
    DataVo dataVo = null;
    for (Object[] ele : traineeVoRepository
        .countTraineesGroupBySkillByLocation(location)) {
      dataVo = new DataVo(ele[0].toString(), ele[1].toString(),
          Long.valueOf(ele[2].toString()));
      dataVos.add(dataVo);
    }
    return type.equalsIgnoreCase("chart") ? canculatePercent(dataVos, "all")
        : dataVos;
  }

  /**
   * This method to get list trainee with condition status.
   * 
   * @param status trainee's status from browser's input
   * @return list trainee with condition status
   */
  @Override
  public List<DataVo> countTraineesGroupBySkillByStatus(String status,
      String type) {
    List<DataVo> dataVos = new ArrayList<DataVo>();
    DataVo dataVo = null;
    for (Object[] ele : traineeVoRepository
        .countTraineesGroupBySkillByStatus(status)) {
      dataVo = new DataVo(ele[0].toString(), ele[1].toString(),
          Long.valueOf(ele[2].toString()));
      dataVos.add(dataVo);
    }
    return type.equalsIgnoreCase("chart") ? canculatePercent(dataVos, status)
        : dataVos;
  }

  /**
   * This method to get list trainee with condition status.
   * 
   * @param status   trainee's status from browser's input
   * @param location trainee's location from browser's input
   * @return list trainee with condition status and location
   */
  @Override
  public List<DataVo> countTraineesGroupBySkillByStatusAndLocation(
      String status, String location, String type) {
    List<DataVo> dataVos = new ArrayList<DataVo>();
    DataVo dataVo = null;
    for (Object[] ele : traineeVoRepository
        .countTraineesGroupBySkillByStatusAndLocation(status, location)) {
      dataVo = new DataVo(ele[0].toString(), ele[1].toString(),
          Long.valueOf(ele[2].toString()));
      dataVos.add(dataVo);
    }
    return type.equalsIgnoreCase("chart") ? canculatePercent(dataVos, status)
        : dataVos;
  }

  /**
   * This method to get list distinct location from class table inner join
   * trainee table.
   * 
   * @return list location
   */
  @Override
  public List<String> getLocations() {
    return traineeVoRepository.getLocations();
  }

  /**
   * This method to canculate percent data.
   * 
   * @param dataVos list data to canculate
   * @param status  status to canculate
   * @return list after canculate
   * @throws Exception an exception
   */
  public List<DataVo> canculatePercent(List<DataVo> dataVos, String status) {
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
