package com.Project.BoardService;

import com.Project.BoardService.domain.entity.comment.Comment;
import com.Project.BoardService.domain.entity.post.Post;
import com.Project.BoardService.domain.entity.user.User;
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

            User user1 = User.builder()
                    .email("abc1234@google.com")
                    .password("rlarkdgns222")
                    .build();
            em.persist(user1);

            for (int i = 0; i < 10; i++) {
                Post post = Post.builder()
                        .title("제목"+i)
                        .content("내용"+i)
                        .user(user1)
                        .commentCount(0)
                        .likeCount(0)
                        .build();
                em.persist(post);
                Thread.sleep(1);
            }

            User user2 = User.builder()
                    .email("zxcv1234@google.com")
                    .password("rlarkdgns222")
                    .build();
            em.persist(user2);

            User user3 = User.builder()
                    .email("qwer1234@google.com")
                    .password("rlarkdgns222")
                    .build();
            em.persist(user3);

            User user4 = User.builder()
                    .email("asdf1234@google.com")
                    .password("rlarkdgns222")
                    .build();
            em.persist(user4);

            Post post2 = Post.builder()
                    .title("깍두기")
                    .content("ㅎㅎ")
                    .user(user1)
                    .commentCount(0)
                    .likeCount(0)
                    .build();
            em.persist(post2);

            Comment comment1 = Comment.builder()
                    .comment("댓글"+1)
                    .user(user1)
                    .post(post2)
                    .build();
            em.persist(comment1);
            post2.increaseComment();

            Comment comment2 = Comment.builder()
                    .comment("댓글"+2)
                    .user(user2)
                    .post(post2)
                    .build();
            em.persist(comment2);
            post2.increaseComment();

            Comment comment3 = Comment.builder()
                    .comment("댓글"+3)
                    .user(user3)
                    .post(post2)
                    .build();
            em.persist(comment3);
            post2.increaseComment();

            Comment comment4 = Comment.builder()
                    .comment("댓글"+4)
                    .user(user4)
                    .post(post2)
                    .build();
            em.persist(comment4);
            post2.increaseComment();

        }

    }
}
