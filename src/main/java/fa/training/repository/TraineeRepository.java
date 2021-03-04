package fa.training.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fa.training.entity.Trainee;

public interface TraineeRepository extends JpaRepository<Trainee, Integer> {

  // find all by active = 1
  List<Trainee> findByActive(boolean active);

  // find by traineeID
  Trainee findById(int id);

  // find account
  @Query(value = "SELECT count(*) FROM trainee WHERE account LIKE ?1%", 
      nativeQuery = true)
  int countAccount(String accountName);

  // find by name, dateOfBirth, phone, email
  Trainee findByNameAndDateOfBirthAndPhoneAndEmail(String name,
      LocalDate dateOfBirth, String phone, String email);

}
