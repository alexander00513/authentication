package com.gmail.bogatyr.alexander.dzen.lab.authentication.controller;

import com.gmail.bogatyr.alexander.dzen.lab.authentication.dto.AuthenticationDTO;
import com.gmail.bogatyr.alexander.dzen.lab.authentication.dto.LoginErrorDataDTO;
import com.gmail.bogatyr.alexander.dzen.lab.authentication.dto.LoginRequestDataDTO;
import com.gmail.bogatyr.alexander.dzen.lab.authentication.dto.LoginResponseDataDTO;
import com.gmail.bogatyr.alexander.dzen.lab.authentication.entity.Authentication;
import com.gmail.bogatyr.alexander.dzen.lab.authentication.service.AuthenticationService;
import com.gmail.bogatyr.alexander.dzen.lab.authentication.util.ErrorType;
import com.gmail.bogatyr.alexander.dzen.lab.authentication.util.MessageType;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import java.util.Optional;
import java.util.UUID;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by alexander on 14.05.16.
 */
@Controller
public class AuthenticationController {

    private static final Logger logger = getLogger(AuthenticationController.class);

    @Autowired
    private AuthenticationService authenticationService;

    @MessageMapping("/authenticate")
    @SendToUser("/topic/authenticate")
    public AuthenticationDTO authenticate(SimpMessageHeaderAccessor headerAccessor, AuthenticationDTO request) {
        LoginRequestDataDTO requestData = (LoginRequestDataDTO) request.getData();
        Optional<Authentication> authenticationOptional = authenticationService.authenticate(requestData.getEmail(), requestData.getPassword());

        AuthenticationDTO response = new AuthenticationDTO();

        if (authenticationOptional.isPresent()) {
            Authentication authentication = authenticationOptional.get();
            response.setSequenceId(UUID.randomUUID());
            response.setMessageType(MessageType.CUSTOMER_API_TOKEN.name());
            LoginResponseDataDTO responseData = new LoginResponseDataDTO();
            responseData.setApiToken(authentication.getToken());
            responseData.setApiTokenExpirationDate(authentication.getTokenExpire());
            response.setData(responseData);
        } else {
            response.setSequenceId(request.getSequenceId());
            response.setMessageType(MessageType.CUSTOMER_ERROR.name());
            LoginErrorDataDTO errorData = new LoginErrorDataDTO();
            errorData.setCode(ErrorType.CUSTOMER_ERROR.getCode());
            errorData.setDescription(ErrorType.CUSTOMER_ERROR.getDescription());
            response.setData(errorData);
        }

        logger.info("Send message for user with session {}", headerAccessor.getSessionId());
        return response;
    }
}
