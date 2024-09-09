package com.example.crud.service;

import com.example.crud.entity.Post;
import com.example.crud.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServices {

    private final PostRepository postRepository;

    public PostServices(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public List<Post> searchPosts(String keyword) {
        return postRepository.findByTitleContainingOrContentContaining(keyword, keyword);
    }

    public Post getPostById(Long id) {
        Optional<Post> post = postRepository.findById(id);
        return post.orElse(null);
    }

    public void createPost(Post post) {
        postRepository.save(post);
    }

    public void updatePost(Post post) {
        postRepository.save(post);  // JPA에서 save는 새로 추가하거나 업데이트를 처리
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}
