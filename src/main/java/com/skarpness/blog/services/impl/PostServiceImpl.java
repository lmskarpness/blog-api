package com.skarpness.blog.services.impl;

import com.skarpness.blog.domain.PostEntity;
import com.skarpness.blog.repositories.PostRepository;
import com.skarpness.blog.services.PostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class  PostServiceImpl implements PostService {

    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostEntity save(PostEntity postEntity) {
        // Spring Data JPA returns an entity
        return postRepository.save(postEntity);
    }

    @Override
    public Optional<PostEntity> getPostById(Long id) {
        return postRepository.findById(id);
    }

    @Override
    public List<PostEntity> getAllPosts() {
        return StreamSupport.stream(postRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public void deletePostById(Long id) {
        postRepository.deleteById(id);
    }
}
