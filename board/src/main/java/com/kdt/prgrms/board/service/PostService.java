package com.kdt.prgrms.board.service;

import com.kdt.prgrms.board.dto.PostAddRequest;
import com.kdt.prgrms.board.dto.PostResponse;
import com.kdt.prgrms.board.dto.PostUpdateRequest;
import com.kdt.prgrms.board.entity.post.Post;
import com.kdt.prgrms.board.entity.post.PostRepository;
import com.kdt.prgrms.board.entity.user.User;
import com.kdt.prgrms.board.entity.user.UserRepository;
import com.kdt.prgrms.board.exception.ErrorCode;
import com.kdt.prgrms.board.exception.custom.AccessDeniedException;
import com.kdt.prgrms.board.exception.custom.NotFoundException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository) {

        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void addPost(PostAddRequest postAddRequest) {

        if (postAddRequest == null) {
            throw new IllegalArgumentException();
        }

        long userId = postAddRequest.getUserId();

        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOT_FOUND));

        postRepository.save(postAddRequest.toEntity(user));
    }

    @Transactional(readOnly = true)

    public List<PostResponse> getPosts(Pageable pageable) {

        return postRepository.findWithPagination(pageable).stream()
                .map(PostResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)

    public PostResponse getPostById(long id) {

        Post post = postRepository.findById(id).orElseThrow(() -> new NotFoundException(ErrorCode.POST_NOT_FOUND));

        return PostResponse.from(post);
    }

    @Transactional
    public void updatePostById(long id, PostUpdateRequest postUpdateRequest) {

        if (postUpdateRequest == null) {
            throw new IllegalArgumentException();
        }

        long requestUserId = postUpdateRequest.getUserId();
        User user = userRepository.findById(requestUserId).orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOT_FOUND));
        Post post = postRepository.findById(id).orElseThrow(() -> new NotFoundException(ErrorCode.POST_NOT_FOUND));

        if (!post.isSameUser(user)) {
            throw new AccessDeniedException(ErrorCode.POST_ACCESS_DENIED);
        }

        post.update(postUpdateRequest.getTitle(), postUpdateRequest.getContent());
        postRepository.save(post);
    }
}
