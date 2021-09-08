package com.example.RecipesAPI.registration;

import com.example.RecipesAPI.chefs.Chefs;
import com.example.RecipesAPI.chefs.ChefsService;
import com.example.RecipesAPI.chefs.UserRole;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final ChefsService chefsService;

    public String register(RegistrationRequest request) {
        return chefsService.signUpCook(
                new Chefs(
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        request.getPassword(),
                        UserRole.USER
                )
        );
    }
}