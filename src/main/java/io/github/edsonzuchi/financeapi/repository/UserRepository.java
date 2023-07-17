package io.github.edsonzuchi.financeapi.repository;

import io.github.edsonzuchi.financeapi.orm.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
