package fa.training.service;

import java.util.List;
import fa.training.entity.University;

public interface UniversityService {

  //find all university
  List<University> findAllUniversity();

  //find University by name
  University findUniversityByName(String name);
}
