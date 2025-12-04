package org.devops.learning.controller;

import org.devops.learning.controller.model.RegistrationRqst;
import org.devops.learning.controller.model.RegistrationRsp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRegistrationController {

    @PostMapping(path = "/user/registration")
    public ResponseEntity<RegistrationRsp> registerUser(@RequestBody RegistrationRqst registrationRqst) {
        RegistrationRsp registrationRsp = RegistrationRsp.builder()
                .rsp(registrationRqst.getName() + " as User, Registered with email id: " + registrationRqst.getEmail())
                .build();
        return new ResponseEntity<>(registrationRsp, HttpStatus.CREATED);
    }
}
