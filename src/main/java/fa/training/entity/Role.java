package fa.training.entity;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 * Represents an Role for the Role table in database.
 * 
 * @author ThanhDT19
 *
 */
@Entity
public class Role {
  /**
   * The @Id annotation marks a field as a primary key field.
   * The @GeneratedValue annotation specifies that the primary key is
   * automatically allocatedB.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String roleName;

  /**
   * Relate to multiple instances of Account entity.
   */
  @ManyToMany(mappedBy = "roles")
  private List<Account> accounts;

  public Role() {
    super();
  }

  public Role(String roleName) {
    super();
    this.roleName = roleName;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }

  @Override
  public String toString() {
    return "Role [id=" + id + ", roleName=" + roleName + "]";
  }

}
