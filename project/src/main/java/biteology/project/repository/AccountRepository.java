package biteology.project.repository;

import biteology.project.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, String> {

    Optional<Account> findByUuid(String uuid);
    Optional<Account> findByEmail(String email);
}
