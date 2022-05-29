package com.kdt.prgrms.board.dto;

import com.kdt.prgrms.board.entity.post.Post;
import com.kdt.prgrms.board.entity.user.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class PostAddRequest {

    @Positive(message = "Wrong User Id .. Please Check")
    private final long userId;

    @NotBlank(message = "Title Not to be Blank")
    private final String title;

    @NotBlank(message = "Content Not to be Blank")
    private final String content;

    public PostAddRequest(long userId, String title, String content) {

        this.userId = userId;
        this.title = title;
        this.content = content;
    }

    public Post toEntity(User user) {

        return Post.builder()
                .title(this.title)
                .content(this.content)
                .user(user)
                .build();
    }

    public long getUserId() {

        return userId;
    }

    public String getTitle() {

        return title;
    }

    public String getContent() {

        return content;
    }
}
