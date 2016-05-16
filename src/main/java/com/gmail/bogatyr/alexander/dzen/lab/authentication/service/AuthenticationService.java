package com.gmail.bogatyr.alexander.dzen.lab.authentication.service;

import com.gmail.bogatyr.alexander.dzen.lab.authentication.entity.Authentication;
import com.gmail.bogatyr.alexander.dzen.lab.authentication.repository.AuthenticationRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by alexander on 14.05.16.
 */
@Service
public class AuthenticationService {

    private static final Logger logger = getLogger(AuthenticationService.class);

    @Autowired
    private AuthenticationRepository authenticationRepository;

    @Transactional
    public Optional<Authentication> authenticate(String email, String password) {
        Optional<Authentication> authentication = authenticationRepository.findOneByEmailAndPassword(email, password);
        return authentication.map(a -> {
            a.setToken(UUID.randomUUID());

            ZonedDateTime tokenExpire = ZonedDateTime.now(ZoneId.of("UTC")).plusDays(30);
            a.setTokenExpire(tokenExpire);

            logger.info("Access token {} for {} expire after 30 days - {}", a.getToken(), a.getEmail(), tokenExpire);
            authenticationRepository.save(a);

            return a;
        });
    }
}
