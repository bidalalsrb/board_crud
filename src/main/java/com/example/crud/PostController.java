package com.example.crud;

import com.example.crud.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public String getPosts(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        List<Post> posts;
        if (keyword != null && !keyword.isEmpty()) {
            posts = postService.searchPosts(keyword);
        } else {
            posts = postService.getAllPosts();
        }
        model.addAttribute("posts", posts);
        model.addAttribute("keyword", keyword);  // 검색어를 뷰로 전달
        return "posts";
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
        postService.createPost(post);
        return "redirect:/posts";
    }

    // 게시글 삭제
    @PostMapping("/delete/{id}")
    public String deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return "redirect:/posts";
    }

    // 게시글 수정 폼 보여주기
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Post post = postService.getPostById(id);
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
        postService.updatePost(updatedPost);
        return "redirect:/posts";
    }
}
