package fa.training.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fa.training.entity.University;
import fa.training.repository.UniversityRepository;
import fa.training.service.UniversityService;

/**
 * 
 * @author Tung Linh.
 *
 */
@Service
public class UniversityServiceImpl implements UniversityService {

	@Autowired
	private UniversityRepository universityRepository;

	/**
	 * to find all university and save to a list.
	 */
	@Override
	public List<University> findAllUniversity() {
		return universityRepository.findAll().stream()
				.sorted((o1, o2) -> o1.getUniversityName().compareTo(o2.getUniversityName()))
				.collect(Collectors.toList());
	}

	/**
	 * to find a university by name, if it hasn't existed yet, insert a new one with
	 * that name.
	 */
	@Override
	public University findUniversityByName(String name) {
		University university = null;
		if (universityRepository.findByUniversityName(name) == null) {
			university = universityRepository.save(new University(name));
		} else {
			university = universityRepository.findByUniversityName(name);
		}
		return university;
	}
}
