package fa.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import fa.training.entity.Account;

/**
 * Interface repository to access Account table in database.
 * 
 * @author ThanhDT19
 *
 */
public interface AccountRepository extends JpaRepository<Account, Integer> {

  /**
   * Method to get.
   * 
   * @param username user name
   * @return A Account representing a row in Account table in database
   */
  Account findByUserName(String username);

}
