package com.tb.foodaway.service.core;

import com.tb.foodaway.model.enums.UserRole;
import com.tb.foodaway.model.persistance.User;
import com.tb.foodaway.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.NullAuthoritiesMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    // TODO: add company authority to the authorizarion principal

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Optional<User> userOptional = userRepository.findByEmail(username);
        if (!userOptional.isPresent()) {
            throw new UsernameNotFoundException("Not Found");
        }

        return map(userOptional.get());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private UserDetails map(User user) {
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getId())
                .password(user.getPassword())
                .disabled(!user.isEnabled())
                .roles(concatenateRoles(user))
                .build();
    }

    private String[] concatenateRoles(User user) {
        return this.getRoles(user).collect(Collectors.toList()).toArray(new String[]{});
    }

    private Stream<String> getRoles(User user) {
        return Stream.of(Optional.ofNullable(user.getRole()).orElse(UserRole.NONE)).map(role -> "" + role);
    }

    public UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken(UserDetails userDetails) {

        final GrantedAuthoritiesMapper grantedAuthoritiesMapper = new NullAuthoritiesMapper();
        final UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                grantedAuthoritiesMapper.mapAuthorities(userDetails.getAuthorities())
        );

        usernamePasswordAuthenticationToken.setDetails(null);
        usernamePasswordAuthenticationToken.eraseCredentials();

        return usernamePasswordAuthenticationToken;
    }
}
