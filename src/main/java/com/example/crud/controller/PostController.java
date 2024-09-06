package com.example.crud.controller;

import com.example.crud.entity.Post;
import com.example.crud.service.PostServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostController {
    private final PostServices postServices;

    public PostController(PostServices postServices) {
        this.postServices = postServices;
    }

    // 게시글 목록 조회 및 검색
    @GetMapping
    public String getPosts(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        List<Post> posts;
        if (keyword != null && !keyword.isEmpty()) {
            posts = postServices.searchPosts(keyword);
        } else {
            posts = postServices.getAllPosts();
        }
        model.addAttribute("posts", posts);
        model.addAttribute("keyword", keyword);  // 검색어를 뷰로 전달
        return "posts";
    }

    // 게시글 상세보기
    @GetMapping("/{id}")
    public String getPostDetails(@PathVariable Long id, Model model) {
        Post post = postServices.getPostById(id);
        if (post != null) {
            model.addAttribute("post", post);
            return "postDetail";  // 상세보기 페이지로 이동
        } else {
            return "redirect:/posts";  // 게시글이 없으면 목록으로 이동
        }
    }

    // 게시글 작성 폼 보여주기
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("post", new Post());
        return "createPost";
    }

    // 게시글 작성
    @PostMapping
    public String createPost(@ModelAttribute Post post) {
        postServices.createPost(post);
        return "redirect:/posts";
    }

    // 게시글 삭제
    @PostMapping("/delete/{id}")
    public String deletePost(@PathVariable Long id) {
        postServices.deletePost(id);
        return "redirect:/posts";
    }

    // 게시글 수정 폼 보여주기
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Post post = postServices.getPostById(id);
        if (post == null) {
            return "redirect:/posts";
        }
        model.addAttribute("post", post);
        return "editPost";
    }

    // 게시글 수정 처리
    @PostMapping("/edit/{id}")
    public String updatePost(@PathVariable Long id, @ModelAttribute Post updatedPost) {
        updatedPost.setId(id);
        postServices.updatePost(updatedPost);
        return "redirect:/posts";
    }
}
