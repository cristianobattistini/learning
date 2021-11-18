package it.cristiano.learning.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.cristiano.learning.dtos.UserDTO;
import it.cristiano.learning.services.RegistrationService;

@RestController
@RequestMapping(path="api/v1/registration")
public class RegistrationController {

    private RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService){
        this.registrationService = registrationService;
    }

    @PostMapping
    public String register(@RequestBody UserDTO request){
        return registrationService.register(request);
    }
    
    @GetMapping(path="confirmation")
    public String confirmToken(@RequestParam("token") String token) {
    	return registrationService.confirmToken(token);
    }
    
}
