package com.costa.projetoBiblioteca.auth;

import com.costa.projetoBiblioteca.user.entiys.UserEntity;
import com.costa.projetoBiblioteca.user.entiys.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

        private final UserEntityRepository userEntityRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userEntityRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario não encontradc"));

        return new User(
              userEntity.getEmail(),
              userEntity.getSenha(),
              List.of(new SimpleGrantedAuthority(userEntity.getRole().name()))
        );
    }
}
