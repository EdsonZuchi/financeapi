package io.github.edsonzuchi.financeapi.repository;

import io.github.edsonzuchi.financeapi.orm.Bill;
import io.github.edsonzuchi.financeapi.orm.Launch;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LaunchRepository extends JpaRepository<Launch, Long> {

    @Transactional
    @Modifying
    @Query("delete from Launch l where l.bill = :bill")
    void deleteAllLaunchOfBill(@Param("bill") Bill bill);

    @Query("select l from Launch l where YEAR(l.referenceDate) = :year and MONTH(l.referenceDate) = :month")
    List<Launch> findByYearAndMonth(@Param("year") Integer year, @Param("month") Integer month);

    @Query("select SUM(l.value) from Launch l where YEAR(l.referenceDate) = :year and MONTH(l.referenceDate) = :month")
    Double findByYearAndMonthTotal(@Param("year") Integer year, @Param("month") Integer month);
}
