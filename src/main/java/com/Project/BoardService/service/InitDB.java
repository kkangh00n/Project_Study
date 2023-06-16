package com.Project.BoardService.service;

import com.Project.BoardService.domain.Post;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class InitDB {

    private final InitService initService;

    @PostConstruct
    public void init(){
        initService.dbInit();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService{

        private final EntityManager em;

        public void dbInit(){
            for (int i = 0; i < 20; i++) {
                Post post = Post.builder()
                        .title("제목"+i)
                        .content("내용"+i)
                        .build();
                em.persist(post);
            }
        }

    }
}
