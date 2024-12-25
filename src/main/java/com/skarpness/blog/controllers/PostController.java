package com.skarpness.blog.controllers;

import com.skarpness.blog.domain.PostDto;
import com.skarpness.blog.domain.PostEntity;
import com.skarpness.blog.mappers.Mapper;
import com.skarpness.blog.services.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class PostController {

    private Mapper<PostEntity, PostDto> postMapper;

    private PostService postService;

    public PostController(Mapper<PostEntity, PostDto> postMapper, PostService postService) {
        this.postMapper = postMapper;
        this.postService = postService;
    }

    // Convert post DTO into an entity to be used by the repository. This layer
    // keeps the internal object usage hidden from external processes.
    // This method takes in a DTO, maps it to an entity, saves the entity in the
    // database via the service (which accesses the repository), then converts it
    // back into a DTO to be returned.
    @PostMapping(path = "/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
        PostEntity postEntity = postMapper.mapFrom(postDto);
        PostEntity savedPost = postService.save(postEntity);
        return new ResponseEntity<>(postMapper.mapTo(savedPost), HttpStatus.CREATED);
    }

    @GetMapping(path = "/posts/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("id") Long id) {
        Optional<PostEntity> post = postService.getPostById(id);

        if (post.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(postMapper.mapTo(post.get()), HttpStatus.OK);
    }

    // Returns status code 200 for both empty and populated lists.
    @GetMapping(path = "/posts")
    public List<PostDto> getAllPosts() {
        List<PostEntity> posts = postService.getAllPosts();
        return posts.stream().map(postMapper::mapTo).collect(Collectors.toList());
    }

    @PutMapping(path = "/posts/{id}")
    public ResponseEntity<PostDto> updatePostById(@RequestBody PostDto postDto, @PathVariable("id") Long id) {
        if (postService.getPostById(id).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        postDto.setId(id);
        PostEntity updatedPost = postService.save(postMapper.mapFrom(postDto));
        return new ResponseEntity<>(postMapper.mapTo(updatedPost), HttpStatus.OK);
    }

    @DeleteMapping(path = "/posts/{id}")
    public ResponseEntity<Void> deletePostById(@PathVariable("id") Long id) {
        if (postService.getPostById(id).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        postService.deletePostById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
