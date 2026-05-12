package com.costa.projetoBiblioteca.user;

import com.costa.projetoBiblioteca.services.UserService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserEntityRepostiroy extends JpaRepository<UserEntity, UUID> {
}
