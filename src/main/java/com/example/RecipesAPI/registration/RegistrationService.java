package com.example.RecipesAPI.registration;

import com.example.RecipesAPI.cook.Cook;
import com.example.RecipesAPI.cook.CookService;
import com.example.RecipesAPI.cook.UserRole;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final CookService cookService;

    public String register(RegistrationRequest request) {
        return cookService.signUpCook(
                new Cook(
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        request.getPassword(),
                        UserRole.USER
                )
        );
    }
}