package it.cristiano.learning.services;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.cristiano.learning.entities.ConfirmationToken;
import it.cristiano.learning.entities.User;
import it.cristiano.learning.repositories.ConfirmationTokenRepository;
import it.cristiano.learning.repositories.UserRepository;

@Service
@Transactional
public class UserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG = "user with username %s not found";
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private ConfirmationTokenService confirmationTokenService;

    public UserService(UserRepository userRepository,
                    BCryptPasswordEncoder bCryptPasswordEncoder,
                    ConfirmationTokenService confirmationTokenService){
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.confirmationTokenService = confirmationTokenService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
              .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, username)));
    }


    public String signUpUser(User user){
        boolean userExists = userRepository.findByUsername(user.getUsername()).isPresent();
        if(userExists){
            throw new IllegalStateException("Username already chosen");
        }
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);

        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken();
        confirmationToken.setToken(token);
        confirmationToken.setCreatedAt(LocalDateTime.now());
        confirmationToken.setExpiredAt(LocalDateTime.now().plusMinutes(15));
        confirmationToken.setUser(user);
        confirmationTokenService.saveConfirmationToken(confirmationToken);
        
        return token;
    }


	public void enableUser(String username) {
		Optional<User> optionalUser = userRepository.findByUsername(username);
		User user = new User();
		if(optionalUser.isPresent()) {
			user = optionalUser.get();
		}
		user.setEnabled(true);
		userRepository.save(user);
	}
    
}
