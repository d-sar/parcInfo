package ma.enset.infoparc.repository;

import ma.enset.infoparc.entities.Computer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComputerRepository  extends JpaRepository<Computer,Long> {
    Page<Computer>  findByNameContains(String name, Pageable pageable);
}
