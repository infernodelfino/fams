package fa.training.service;

import java.util.List;
import fa.training.entity.Faculty;

public interface FacultyService {

  //find all Faculty
  List<Faculty> findAllFaculty();

  //find Faculty by name
  Faculty findByFacultyName(String name);
}
