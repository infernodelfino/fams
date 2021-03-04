package fa.training.entity;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 * The persistent Account for the Account table in database.
 * 
 * @author ThanhDT19
 *
 */
@Entity
public class Account {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String userName;
  private String password;
  private String fullName;

  /**
   * Relate to multiple instances of Role entity.
   */
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "account_role", 
      joinColumns = @JoinColumn(name = "account_id"), 
      inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles;

  public Account() {
    super();
  }

  public Account(String userName, String password, Set<Role> roles) {
    super();
    this.userName = userName;
    this.password = password;
    this.roles = roles;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public Account(int id, String userName, String password, String fullName,
      Set<Role> roles) {
    super();
    this.id = id;
    this.userName = userName;
    this.password = password;
    this.fullName = fullName;
    this.roles = roles;
  }

  @Override
  public String toString() {
    return "Account [id=" + id + ", userName=" + userName + ", password="
        + password + ", fullName=" + fullName + ", roles=" + roles + "]";
  }


}
