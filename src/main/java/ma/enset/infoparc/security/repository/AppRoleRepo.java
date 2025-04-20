package ma.enset.infoparc.security.repository;

import ma.enset.infoparc.security.entitites.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepo extends JpaRepository<AppRole, String> {
}
