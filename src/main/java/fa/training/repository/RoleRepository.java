package fa.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import fa.training.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
  Role findByRoleName(String roleName);

}
