package io.github.edsonzuchi.financeapi.repository;

import io.github.edsonzuchi.financeapi.orm.Bead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeadRepository extends JpaRepository<Bead, Long> {
}
