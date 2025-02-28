package com.example.postsservice.web.repository;

import com.example.postsservice.web.entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PostsRepository extends JpaRepository<Posts, Long> {
    @Query(value = "SELECT p FROM Posts p WHERE p.userId = :userID")
    Set<Posts> getPostsByUserId(Long userID);
}
