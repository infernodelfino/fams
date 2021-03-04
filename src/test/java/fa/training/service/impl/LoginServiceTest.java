package fa.training.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.User;
import fa.training.entity.Account;
import fa.training.entity.Role;
import fa.training.repository.AccountRepository;
import fa.training.service.impl.AccountDetailsServiceImpl;
import fa.training.utils.Log4J;

@RunWith(MockitoJUnitRunner.class)
public class LoginServiceTest {

  @InjectMocks
  AccountDetailsServiceImpl accountDetailsService;

  @Mock
  AccountRepository accountRepository;

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
  }

  @Before
  public void setUp() throws Exception {
  }

  /**
   * This method to test account log in success.
   */
  @Test
  public void testLoadUserByUsername() {
    Set<Role> roles = new HashSet<>();
    roles.add(new Role("MANAGER"));
    Account account = new Account("quyen123",
        "$2a$10$TtJnbm8V75F/DDC8cSVwjOoMNY5VfVIZefCLtuQIRWwsAXm.p7vA.", roles);
    when(accountRepository.findByUserName("quyen123")).thenReturn(account);
    User actual = (User) accountDetailsService.loadUserByUsername("quyen123");
    Log4J.getLogger().info(account);
    Log4J.getLogger().info(actual);
    assertEquals(account.getUserName(), actual.getUsername());
    assertEquals(account.getPassword(), actual.getPassword());
  }

  /**
   * This method to test account log in fail - incorrect password.
   */
  @Test
  public void testLoadUserByUsernameFailPassword() {
    Set<Role> roles = new HashSet<>();
    roles.add(new Role("MANAGER"));
    Account account = new Account("quyen123",
        "$2a$10$TtJnbm8V75F/DDC8cSVwjOoMNY5VfVIZefCLtuQIRWwsAXm.p7vA.", roles);
    Account actualFind = new Account("quyen123",
        "$9a$10$TtJnbm8V75F/DDC8cSVwjOoMNY5VfVIZefCLtuQIRWwsAXm.p7vA.", roles);
    when(accountRepository.findByUserName("quyen123")).thenReturn(actualFind);
    User actual = (User) accountDetailsService.loadUserByUsername("quyen123");
    Log4J.getLogger().info(account);
    Log4J.getLogger().info(actual);
    assertEquals(account.getUserName(), actual.getUsername());
    assertNotEquals(account.getPassword(), actual.getPassword());
  }

}
