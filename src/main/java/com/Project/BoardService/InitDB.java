package com.Project.BoardService;

import com.Project.BoardService.domain.post.Post;
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
    public void init() throws Exception {
        initService.dbInit();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService{

        private final EntityManager em;

        public void dbInit() throws Exception {
            for (int i = 0; i < 200; i++) {
                Post post = Post.builder()
                        .title("제목"+i)
                        .content("내용"+i)
                        .build();
                em.persist(post);
                Thread.sleep(1);
            }
        }

    }
}
