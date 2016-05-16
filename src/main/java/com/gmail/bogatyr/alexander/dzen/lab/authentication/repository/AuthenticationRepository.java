package com.gmail.bogatyr.alexander.dzen.lab.authentication.repository;

import com.gmail.bogatyr.alexander.dzen.lab.authentication.entity.Authentication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by alexander on 16.05.16.
 */
public interface AuthenticationRepository extends JpaRepository<Authentication, Long> {

    Optional<Authentication> findOneByEmailAndPassword(String email, String password);
}
