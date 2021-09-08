package com.example.RecipesAPI.cook;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class CookService implements UserDetailsService {

    private final CookRepository cookRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return cookRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Email: %s not found", email)));
    }

    public String signUpCook(Cook cook) throws ResponseStatusException {
        boolean cookExist = cookRepository
                .findByEmail(cook.getEmail())
                .isPresent();

        if (cookExist) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already taken");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(cook.getPassword());

        cook.setPassword(encodedPassword);

        cookRepository.save(cook);

        return "Successful registration";
    }
}
