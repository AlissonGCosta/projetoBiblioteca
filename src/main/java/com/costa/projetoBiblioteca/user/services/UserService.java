package com.costa.projetoBiblioteca.user.services;

import com.costa.projetoBiblioteca.user.dto.UserEntityRequestDto;
import com.costa.projetoBiblioteca.user.dto.UserResponseDto;
import com.costa.projetoBiblioteca.user.entiys.UserEntity;
import com.costa.projetoBiblioteca.user.entiys.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserEntityRepository userEntityRepository;

    public void createUser(UserEntityRequestDto dto) throws RuntimeException {

        if (userEntityRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Usuario ja cadastrado");
        }

        userEntityRepository.save(UserEntity.builder()
                .nome(dto.getNome())
                .email(dto.getEmail())
                .build());

    }

    public List<UserResponseDto> showAllUsers() {

      return userEntityRepository.findAll().stream()
                .map(user -> UserResponseDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .nome(user.getNome())
                .build())
                .toList();

    }
}
