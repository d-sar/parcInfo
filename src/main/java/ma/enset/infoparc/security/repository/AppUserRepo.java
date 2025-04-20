package ma.enset.infoparc.security.repository;

import ma.enset.infoparc.security.entitites.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepo extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String username);
}
