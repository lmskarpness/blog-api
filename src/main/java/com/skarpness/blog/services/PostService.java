package com.skarpness.blog.services;

import com.skarpness.blog.domain.PostEntity;

import java.util.List;
import java.util.Optional;

public interface PostService {

    PostEntity save(PostEntity postEntity);

    Optional<PostEntity> getPostById(Long id);

    List<PostEntity> getAllPosts();

    void deletePostById(Long id);
}
