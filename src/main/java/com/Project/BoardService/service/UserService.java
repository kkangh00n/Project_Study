package com.Project.BoardService.service;

import com.Project.BoardService.domain.User;
import com.Project.BoardService.domain.dto.userDto.UserResponseDto;
import com.Project.BoardService.domain.dto.userDto.UserSaveRequestDto;
import com.Project.BoardService.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    //회원 가입
    @Transactional
    public UserResponseDto signIn(UserSaveRequestDto userSaveRequestDto){
        User saveUser = userRepository.save(userSaveRequestDto.toEntity());
        return UserResponseDto.of(saveUser);
    }
}
