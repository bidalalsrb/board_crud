package com.example.crud.mapper;

import com.example.crud.entity.Post;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PostMappers {

    @Select("SELECT * FROM posts")
    List<Post> getAllPosts();

    @Select("SELECT * FROM posts WHERE title LIKE CONCAT('%', #{keyword}, '%') OR content LIKE CONCAT('%', #{keyword}, '%')")
    List<Post> searchPosts(String keyword);

    @Select("SELECT * FROM posts WHERE id = #{id}")
    Post getPostById(Long id);

    @Insert("INSERT INTO posts (title, content) VALUES (#{title}, #{content})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertPost(Post post);

    @Update("UPDATE posts SET title = #{title}, content = #{content} WHERE id = #{id}")
    void updatePost(Post post);

    @Delete("DELETE FROM posts WHERE id = #{id}")
    void deletePost(Long id);

}

