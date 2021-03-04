package fa.training.service.impl;

import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import fa.training.entity.Account;
import fa.training.entity.Role;
import fa.training.repository.AccountRepository;

/**
 * This class perform access Account table in databse.
 * 
 * @author ThanhDT19
 *
 */
@Service
public class AccountDetailsServiceImpl implements UserDetailsService {
  @Autowired
  AccountRepository accountRepository;

  /**
   * Get information account login.
   * 
   * @param username username input in login page
   * @return account logged in
   */
  @Override
  public UserDetails loadUserByUsername(String username)
      throws UsernameNotFoundException {
    Account account = accountRepository.findByUserName(username);
    if (account == null) {
      throw new UsernameNotFoundException("Account not found");
    }
    Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
    Set<Role> roles = account.getRoles();
    for (Role role : roles) {
      grantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleName()));
    }
    return new User(account.getUserName(), account.getPassword(),
        grantedAuthorities);
  }
  
  /**
   * To encode password.
   * 
   * @param password password input in login page
   * @return A bcrypt password encryptor
   */
  public static String encrytePassword(String password) {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    return encoder.encode(password);
  }
  
  /**
   * Get current user logged in.
   * 
   * @return username
   */
  public String getCurrentUsername() {
    User user = (User) SecurityContextHolder.getContext().getAuthentication()
        .getPrincipal();
    return user.getUsername();
  }

}
