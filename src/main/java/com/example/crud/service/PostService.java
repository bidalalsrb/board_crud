package com.example.crud.service;

import com.example.crud.mapper.PostMapper;
import com.example.crud.entity.Post;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostMapper postMapper;

    public PostService(PostMapper postMapper) {
        this.postMapper = postMapper;
    }

    public List<Post> getAllPosts() {
        return postMapper.getAllPosts();
    }

    public List<Post> searchPosts(String keyword) {
        return postMapper.searchPosts(keyword);
    }
    public Post getPostById(Long id) {
        return postMapper.getPostById(id);
    }

    public void createPost(Post post) {
        postMapper.insertPost(post);
    }

    public void updatePost(Post post) {
        postMapper.updatePost(post);
    }

    public void deletePost(Long id) {
        postMapper.deletePost(id);
    }
}
