package com.gmail.bogatyr.alexander.dzen.lab.authentication.service;

import com.gmail.bogatyr.alexander.dzen.lab.authentication.entity.Authentication;
import com.gmail.bogatyr.alexander.dzen.lab.authentication.repository.AuthenticationRepository;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by alexander on 14.05.16.
 */
@Service
public class AuthenticationService {

    private static final Logger logger = getLogger(AuthenticationService.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private AuthenticationRepository authenticationRepository;

    @Transactional
    public Optional<Authentication> authenticate(String email, String password) {
        Optional<Authentication> authentication = authenticationRepository.findOneByEmailAndPassword(email, password);
        return authentication.map(a -> {
            a.setToken(UUID.randomUUID());
            a.setTokenExpire(ZonedDateTime.now(ZoneId.of("UTC")).plusDays(30));

            authenticationRepository.save(a);
            logger.info("Access token {} for {} expire after 30 days - {}", a.getToken(), a.getEmail(), a.getTokenExpire());

            AuditReader auditReader = AuditReaderFactory.get(entityManager);

            List<Number> revisions = auditReader.getRevisions(Authentication.class, a.getId());
            int revisionsSize = revisions != null ? revisions.size() : 0;
            logger.info("Authentication record with id = {} has {} revisions", a.getId(), revisionsSize);

            Number lastRevision = revisionsSize > 0 ? revisions.get(revisionsSize - 1) : 1;
            Authentication authenticationRevision = auditReader.find(Authentication.class, a.getId(), lastRevision);
            logger.info("Authentication last revision is {}", authenticationRevision);

            return a;
        });
    }
}
