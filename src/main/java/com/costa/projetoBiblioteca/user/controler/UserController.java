package com.costa.projetoBiblioteca.user.controler;

import com.costa.projetoBiblioteca.user.dto.UserEntityRequestDto;
import com.costa.projetoBiblioteca.user.dto.UserResponseDto;
import com.costa.projetoBiblioteca.user.entiys.UserEntity;
import com.costa.projetoBiblioteca.user.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody @Valid UserEntityRequestDto user) {
        userService.createUser(user);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponseDto> showAllUsers() {
      return  userService.showAllUsers();

    }

}
