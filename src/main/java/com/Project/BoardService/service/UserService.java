package com.Project.BoardService.service;

import com.Project.BoardService.domain.User;
import com.Project.BoardService.domain.dto.userDto.UserResponseDto;
import com.Project.BoardService.domain.dto.userDto.UserSaveRequestDto;
import com.Project.BoardService.exception.advice.userAdvice.DuplicationEmailException;
import com.Project.BoardService.exception.advice.userAdvice.InvalidPasswordException;
import com.Project.BoardService.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    //회원 가입
    @Transactional
    public UserResponseDto signIn(UserSaveRequestDto userSaveRequestDto){

        String password = userSaveRequestDto.getPassword();

        //중복 이메일 확인
        if(userRepository.existsUserByEmail(userSaveRequestDto.getEmail())){
            throw new DuplicationEmailException();
        }

        //이메일 & 비밀번호 공백 확인
        if(password.contains(" ")){
            throw new InvalidPasswordException();
        }

        User saveUser = userRepository.save(userSaveRequestDto.toEntity());
        return UserResponseDto.of(saveUser);
    }
}
