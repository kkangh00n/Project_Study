package com.Project.BoardService.domain.user;

import com.Project.BoardService.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsUserByEmail(String email);

    Optional<User> findByEmailAndPassword(String email, String password);
}
