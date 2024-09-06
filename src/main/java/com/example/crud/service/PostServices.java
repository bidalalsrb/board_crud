package com.example.crud.service;

import com.example.crud.entity.Post;
import com.example.crud.mapper.PostMappers;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServices {

    private final PostMappers postMappers;

    public PostServices(PostMappers postMappers) {
        this.postMappers = postMappers;
    }

    public List<Post> getAllPosts() {
        return postMappers.getAllPosts();
    }

    public List<Post> searchPosts(String keyword) {
        return postMappers.searchPosts(keyword);
    }

    public Post getPostById(Long id) {
        return postMappers.getPostById(id);
    }

    public void createPost(Post post) {
        postMappers.insertPost(post);
    }

    public void updatePost(Post post) {
        postMappers.updatePost(post);
    }

    public void deletePost(Long id) {
        postMappers.deletePost(id);
    }
}
