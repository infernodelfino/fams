package fa.training.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fa.training.entity.Faculty;
import fa.training.repository.FacultyRepository;
import fa.training.service.FacultyService;

/**
 * 
 * @author Tung Linh.
 *
 */
@Service
public class FacultyServiceImpl implements FacultyService {

  @Autowired
  private FacultyRepository facultyRepository;

  /**
   * to find all faculty and save to a list.
   */
  @Override
  public List<Faculty> findAllFaculty() {
     return facultyRepository.findAll().stream().sorted((o1, o2) -> 
		o1.getFacultyName().compareTo(o2.getFacultyName())).collect(Collectors.toList());
  }

  /**
   * to find a faculty by name, if it hasn't existed yet, insert a new one with
   * that name.
   */
  @Override
  public Faculty findByFacultyName(String name) {
    Faculty faculty = null;
    if (facultyRepository.findByFacultyName(name) == null) {
      faculty = facultyRepository.save(new Faculty(name));
    } else {
      faculty = facultyRepository.findByFacultyName(name);
    }
    return faculty;
  }

}
