package com.example.RecipesAPI.chefs;

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
public class ChefsService implements UserDetailsService {

    private final ChefsRepository chefsRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return chefsRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Email: %s not found", email)));
    }

    public String signUpCook(Chefs chefs) throws ResponseStatusException {
        boolean cookExist = chefsRepository
                .findByEmail(chefs.getEmail())
                .isPresent();

        if (cookExist) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already taken");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(chefs.getPassword());

        chefs.setPassword(encodedPassword);

        chefsRepository.save(chefs);

        return "Successful registration";
    }
}
