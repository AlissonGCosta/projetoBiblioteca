package com.costa.projetoBiblioteca.services;

import com.costa.projetoBiblioteca.user.UserEntityRepostiroy;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserEntityRepostiroy userEntityRepostiroy;
}
