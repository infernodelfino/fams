package fa.training.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fa.training.entity.vo.DataVo;
import fa.training.repository.ClassRepository;
import fa.training.service.ClassesService;
import fa.training.utils.Log4J;

/**
 * This class to defines all method to handle business logic about class.
 * 
 * @author ThanhDT19
 *
 */
@Service
public class ClassesServiceImpl implements ClassesService {
  @Autowired
  private ClassRepository classRepository;

  /**
   * This method to get all class.
   * 
   * @return list class
   */
  @Override
  public List<DataVo> countClassesGroupBySkill(String type) {
    List<DataVo> dataVos = new ArrayList<DataVo>();
    dataVos = classRepository.countClassesGroupBySkill();
    return type.equalsIgnoreCase("chart") ? calculatePercent(dataVos, "all")
        : dataVos;
  }

  /**
   * This method to get list class with condition location.
   * 
   * @param location class's location from browser's input
   * @return list class with condition location
   */
  @Override
  public List<DataVo> countClassesGroupBySkillByLocation(String location,
      String type) {
    List<DataVo> dataVos = new ArrayList<DataVo>();
    dataVos = classRepository.countClassesGroupBySkillByLocation(location);
    return type.equalsIgnoreCase("chart") ? calculatePercent(dataVos, "all")
        : dataVos;
  }

  /**
   * This method to get list class with condition status.
   * 
   * @param status class's status from browser's input
   * @return list class with condition status
   */
  @Override
  public List<DataVo> countClassesGroupBySkillByStatus(String status,
      String type) {
    List<DataVo> dataVos = new ArrayList<DataVo>();
    dataVos = classRepository.countClassesGroupBySkillByStatus(status);
    return type.equalsIgnoreCase("chart") ? calculatePercent(dataVos, status)
        : dataVos;
  }

  /**
   * This method to get list class with condition status.
   * 
   * @param status   class's status from browser's input
   * @param location class's location from browser's input
   * @return list class with condition status and location
   */
  @Override
  public List<DataVo> countClassesGroupBySkillByStatusAndLocation(String status,
      String location, String type) {
    List<DataVo> dataVos = new ArrayList<DataVo>();
    dataVos = classRepository
        .countClassesGroupBySkillByStatusAndLocation(status, location);
    return type.equalsIgnoreCase("chart") ? calculatePercent(dataVos, status)
        : dataVos;
  }

  /**
   * This method to get list distinct location from class table.
   * 
   * @return list location
   */
  @Override
  public List<String> getLocations() {
    return classRepository.getLocations();
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
