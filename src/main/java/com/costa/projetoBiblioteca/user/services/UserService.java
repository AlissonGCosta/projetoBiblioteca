package com.costa.projetoBiblioteca.user.services;


import com.costa.projetoBiblioteca.exception.ConflictEcxecption;
import com.costa.projetoBiblioteca.exception.ResourceNotFoundException;
import com.costa.projetoBiblioteca.user.dto.UserEntityRequestDto;
import com.costa.projetoBiblioteca.user.dto.UserResponseDto;
import com.costa.projetoBiblioteca.user.entiys.Role;
import com.costa.projetoBiblioteca.user.entiys.UserEntity;
import com.costa.projetoBiblioteca.user.entiys.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserEntityRepository userEntityRepository;
    private final PasswordEncoder passwordEncoder;


    //criando os usuarios
    public void createUser(UserEntityRequestDto dto) throws RuntimeException {

        if (userEntityRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new ConflictEcxecption("Usuario ja cadastrado");
        }

            String senhaCriptografada = passwordEncoder.encode(dto.getSenha());

            UserEntity userEntity = UserEntity.builder()
                    .nome(dto.getNome())
                    .email(dto.getEmail())
                    .senha(senhaCriptografada)
                    .role(Role.ROLE_USER)
                    .build();


            userEntityRepository.save(userEntity);


    }


    //lista todos os usuarios/adminers
    public List<UserResponseDto> showAllUsers() {

      return userEntityRepository.findAll().stream()
                .map(user -> UserResponseDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .nome(user.getNome())
                .build())
                .toList();

    }

    // lista usuario por id
    public UserResponseDto listarUsuariosById(UUID id) {

        UserEntity user = userEntityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhum usuario encontrado"));

        return new UserResponseDto(user.getId(),
                user.getEmail(),
                user.getNome());
    }

    //delete simples para testes e admins
    public void deleteUser(UUID id) {
        userEntityRepository.deleteById(id);
    }

}
