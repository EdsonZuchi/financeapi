package io.github.edsonzuchi.financeapi.repository;

import io.github.edsonzuchi.financeapi.orm.Bead;
import io.github.edsonzuchi.financeapi.orm.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BeadRepository extends JpaRepository<Bead, Long> {

    @Query("select b from Bead b where b.user = :user and YEAR(b.referenceDate) = :year and MONTH(b.referenceDate) = :month")
    List<Bead> findByUserAndMonth(@Param("user") User user, @Param("year") Integer year, @Param("month") Integer month);
}
