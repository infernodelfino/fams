package fa.training.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import fa.training.entity.Faculty;
import fa.training.repository.FacultyRepository;
import fa.training.utils.Log4J;

/**
 * 
 * @author LinhNT70.
 *
 */
@RunWith(SpringRunner.class)
public class FacultyServiceImplTestTest {

	@Mock
	private FacultyRepository facultyRepository;

	@InjectMocks
	private FacultyServiceImpl facultyService;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	/**
	 * find all.
	 */
	@Test
	public void testFindAllFaculty() {
		List<Faculty> facultyList = new ArrayList<>();
		Faculty faculty1 = new Faculty(1, "CNTT");
		Faculty faculty2 = new Faculty(2, "DTVT");
		Faculty faculty3 = new Faculty(3, "DDT");
		facultyList.add(faculty1);
		facultyList.add(faculty2);
		facultyList.add(faculty3);

		when(facultyRepository.findAll()).thenReturn(facultyList);
		Log4J.getLogger().info(facultyRepository);
		List<Faculty> actual = facultyService.findAllFaculty();

		assertEquals(3, actual.size());
	}

	/**
	 * find all fail.
	 */
	@Test(expected = NullPointerException.class)
	public void testFindAllFaculty2() {
		List<Faculty> facultyList = new ArrayList<>();
		Faculty faculty1 = new Faculty(1, "CNTT");
		Faculty faculty2 = new Faculty(2, "DTVT");
		Faculty faculty3 = new Faculty(3, "DDT");
		facultyList.add(faculty1);
		facultyList.add(faculty2);
		facultyList.add(faculty3);

		when(facultyRepository.findAll()).thenReturn(null);
		Log4J.getLogger().info(facultyRepository);
		List<Faculty> actual = facultyService.findAllFaculty();

		assertEquals(3, actual.size());
	}

	/**
	 * find by facultyName.
	 */
	@Test
	public void testFindByFacultyName() {
		Faculty faculty = new Faculty(1, "FPT");

		when(facultyRepository.findByFacultyName(faculty.getFacultyName())).thenReturn(faculty);

		Faculty actual = facultyService.findByFacultyName(faculty.getFacultyName());

		assertEquals(faculty.getFacultyName(), actual.getFacultyName());
	}

	/**
	 * find by facultyName fail => save a new object.
	 */
	@Test()
	public void testFindByFacultyName2() {
		Faculty faculty = new Faculty(1, "FPT");

		when(facultyRepository.findByFacultyName(faculty.getFacultyName())).thenReturn(null);
		when(facultyRepository.save(ArgumentMatchers.any(Faculty.class))).thenReturn(faculty);

		Faculty actual = facultyService.findByFacultyName(faculty.getFacultyName());

		Log4J.getLogger().info(faculty);
		Log4J.getLogger().info(actual);

		assertEquals(faculty.getFacultyName(), actual.getFacultyName());
	}

}
