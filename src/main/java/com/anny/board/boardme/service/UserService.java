package com.anny.board.boardme.service;

import com.anny.board.boardme.domain.user.User;
import com.anny.board.boardme.domain.user.UserAccount;
import com.anny.board.boardme.dto.user.JoinRequest;
import com.anny.board.boardme.dto.user.UserGetDto;
import com.anny.board.boardme.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("email not found" + email));

        return new UserAccount(user);
    }

    @Transactional
    public Long joinUser(JoinRequest joinRequest) {
        User user = User.of()
                .name(joinRequest.getName())
                .email(joinRequest.getEmail())
                .password(passwordEncoder.encode(joinRequest.getPassword()))
                .build();

        UserServiceHelper.checkUserEmailNotDuplicate(userRepository, joinRequest.getEmail());
        User joinedUser = userRepository.save(user);
        return joinedUser.getId();
    }

    @Transactional(readOnly = true)
    public UserGetDto getUserInfoBy(Long userId) {
        User user = UserServiceHelper.findUserById(userRepository, userId);
        return new UserGetDto(user);
    }
}
