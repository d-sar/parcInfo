package ma.enset.infoparc.repository;

import ma.enset.infoparc.entities.Component;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComponentRepository  extends JpaRepository<Component, Long> {

    Page<Component> findByNameContains(String name, Pageable pageable);
}
