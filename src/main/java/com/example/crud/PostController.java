package com.example.crud;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostController {

    private List<Post> posts = new ArrayList<>();
    private Long postIdCounter = 1L;

    // 게시글 목록 조회
    @GetMapping
    public String getPosts(Model model) {
        model.addAttribute("posts", posts);
        return "posts";  // 타임리프 템플릿 "posts.html"을 반환
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
        post.setId(postIdCounter++);
        posts.add(post);
        return "redirect:/posts";
    }

    // 게시글 삭제
    @PostMapping("/delete/{id}")
    public String deletePost(@PathVariable Long id) {
        posts.removeIf(post -> post.getId().equals(id));
        return "redirect:/posts";
    }

    // 게시글 수정 폼 보여주기
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Post post = posts.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (post == null) {
            return "redirect:/posts";  // 게시글이 없으면 목록으로 리다이렉트
        }

        model.addAttribute("post", post);
        return "editPost";  // 수정할 게시글을 보여줄 타임리프 템플릿 "editPost.html"
    }

    // 게시글 수정 처리
    @PostMapping("/edit/{id}")
    public String updatePost(@PathVariable Long id, @ModelAttribute Post updatedPost) {
        posts.stream()
                .filter(post -> post.getId().equals(id))
                .forEach(post -> {
                    post.setTitle(updatedPost.getTitle());
                    post.setContent(updatedPost.getContent());
                });

        return "redirect:/posts";  // 수정 후 게시글 목록으로 리다이렉트
    }
}
