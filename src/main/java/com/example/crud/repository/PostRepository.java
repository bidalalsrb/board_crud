package com.example.crud.repository;

import com.example.crud.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    // 제목과 내용을 기준으로 검색
    List<Post> findByTitleContainingOrContentContaining(String title, String content);
}
