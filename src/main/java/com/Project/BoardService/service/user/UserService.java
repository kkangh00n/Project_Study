package com.Project.BoardService.service.user;

import com.Project.BoardService.jwt.JwtTokenProvider;
import com.Project.BoardService.jwt.LogInResponse;
import com.Project.BoardService.domain.dto.userDto.LogInRequestDto;
import com.Project.BoardService.domain.user.User;
import com.Project.BoardService.domain.dto.userDto.UserResponseDto;
import com.Project.BoardService.domain.dto.userDto.SignInRequestDto;
import com.Project.BoardService.exception.advice.userAdvice.DuplicationEmailException;
import com.Project.BoardService.exception.advice.userAdvice.InvalidPasswordException;
import com.Project.BoardService.domain.user.UserRepository;
import com.Project.BoardService.exception.advice.userAdvice.NotFoundUserException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    //회원 가입
    @Transactional
    public UserResponseDto signIn(SignInRequestDto signInRequestDto){

        //중복 이메일 확인
        if(userRepository.existsUserByEmail(signInRequestDto.getEmail())){
            throw new DuplicationEmailException();
        }

        //이메일 & 비밀번호 공백 확인
        if(signInRequestDto.getPassword().contains(" ")){
            throw new InvalidPasswordException();
        }

        User saveUser = userRepository.save(signInRequestDto.toEntity());
        return UserResponseDto.of(saveUser);
    }

    public LogInResponse logIn(LogInRequestDto logInRequestDto){
        User LogInUser = userRepository.findByEmailAndPassword(logInRequestDto.getEmail(), logInRequestDto.getPassword())
                .orElseThrow(NotFoundUserException::new);

        return jwtTokenProvider.createToken(LogInUser.getId());
    }
}
