package it.cristiano.learning.services;

import org.springframework.stereotype.Service;

import it.cristiano.learning.entities.ConfirmationToken;
import it.cristiano.learning.repositories.ConfirmationTokenRepository;

@Service
public class ConfirmationTokenService {

    private ConfirmationTokenRepository confirmationTokenRepository;

    public ConfirmationTokenService(ConfirmationTokenRepository confirmationTokenRepository){
        this.confirmationTokenRepository = confirmationTokenRepository;
    }
    
    public void saveConfirmationToken(ConfirmationToken confirmationToken ) {
    	confirmationTokenRepository.save(confirmationToken);	
    }
    
}
